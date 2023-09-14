package com.example.demo.config.auth.loginHandler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.config.auth.jwt.JwtProperties;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.jwt.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements  AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		//JWT Token
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
		TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
		Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenInfo.getAccessToken()); //session에 담기에는 서버과부하가 크니까 cookie로 받기
		cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
		cookie.setPath("/");
		response.addCookie(cookie);
		//로컬유저가 로그인을 성공했을 때 쿠키를 받아서 쿠키값으로 처리할 수 있게 됨




		System.out.println("CustomLoginSuccessHandler's onAuthenticationSuccess! ");
		Collection<? extends GrantedAuthority> collection =   authentication.getAuthorities();
		
		
			collection.forEach((role)->{	
				try {	
					System.out.println("role : " + role.getAuthority());
					String role_str = role.getAuthority();
					if(role_str.equals("ROLE_USER")) {
						
						System.out.println("USER 페이지로 이동!");
						response.sendRedirect(request.getContextPath()+"/user");
						return ;
					}else if(role_str.equals("ROLE_MEMBER")){
						System.out.println("MEMBER 페이지로 이동!");
						response.sendRedirect(request.getContextPath()+"/member");
						return ;
					}
					else if(role_str.equals("ROLE_ADMIN")) {
						System.out.println("ADMIN 페이지로 이동!");	
						response.sendRedirect(request.getContextPath()+"/admin");
						return ;
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			
			} );
		
		
		
	}

}
