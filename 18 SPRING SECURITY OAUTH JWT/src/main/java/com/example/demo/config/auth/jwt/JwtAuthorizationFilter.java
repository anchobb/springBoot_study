package com.example.demo.config.auth.jwt;


import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * JWT를 이용한 인증
 * 클라이언트에게 받은 토큰을 기반으로 authentication 만드는 역할
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(
            UserRepository memberRepository,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException, IOException {
        System.out.println("[JWTAUTHORIZATIONFILTER] doFilterInternal...");

        String token = null;
        try {
            // cookie 에서 JWT token을 가져옵니다. 적절한 사용자인지 확인하기 위해서!
            token = Arrays.stream(request.getCookies())
                    //쿠키 중에 특정쿠키 필터하기 (JwtProperties의 JWT-AU~~~~ 필터)
                    .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME)).findFirst()
                    .map(cookie -> cookie.getValue())
                    .orElse(null);
        } catch (Exception ignored) {

        }
        if (token != null) {
            try {
                if(jwtTokenProvider.validateToken(token)) { //토큰유효성체크 후 문제가 없다면 토큰을 받아서 authentication만들어짐.
                    Authentication authentication = getUsernamePasswordAuthenticationToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("[JWTAUTHORIZATIONFILTER] : " + authentication);
                }
            } catch (ExpiredJwtException e)     //토큰만료시 예외처리(쿠키 제거)
            {

                System.out.println("[JWTAUTHORIZATIONFILTER] : ...ExpiredJwtException ...."+e.getMessage());

                //토큰 만료시 처리(Refresh-token으로 갱신처리는 안함-쿠키에서 제거)
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);

            }catch(Exception e2){

            }
        }
        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
     * User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        Optional<User> user = memberRepository.findById(authentication.getName()); // 유저를 유저명으로 찾습니다.
        System.out.println("JwtAuthorizationFilter.getUsernamePasswordAuthenticationToken...authenticationToken : " +authentication );
        if(user!=null)
        {
            return authentication;
        }
        return null; // 유저가 없으면 NULL
    }

}