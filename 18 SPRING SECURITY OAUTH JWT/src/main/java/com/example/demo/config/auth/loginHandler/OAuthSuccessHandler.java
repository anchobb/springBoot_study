package com.example.demo.config.auth.loginHandler;

import com.example.demo.config.auth.jwt.JwtProperties;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.jwt.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //JWT Token
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenInfo.getAccessToken()); //session에 담기에는 서버과부하가 크니까 cookie로 받기
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
        cookie.setPath("/");
        response.addCookie(cookie);
        //로컬유저가 로그인을 성공했을 때 쿠키를 받아서 쿠키값으로 처리할 수 있게 됨
        //리다이렉트 경로 잡아줘야 함!
    }
}
