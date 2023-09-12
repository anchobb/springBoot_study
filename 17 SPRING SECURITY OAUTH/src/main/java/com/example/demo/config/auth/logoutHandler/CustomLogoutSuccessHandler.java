package com.example.demo.config.auth.logoutHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.config.auth.PrincipalDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
	private String kakaoClientId = "a8eb71737b050265eaa778caba7f2bbb";
	private String LOGOUT_REDIRECT_URI = "http://localhost:8080/login";


	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("CustomLogoutSuccessHandler's onLogoutSuccess!");

		//로그아웃 성공시..!
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		String provider = (String) principalDetails.getUser().getProvider();

		if(StringUtils.contains(provider,"kakao")){


			System.out.println("GET /th/kakao/logoutWithKakao");

			//URL(access토큰 받기)
			String url = "https://kauth.kakao.com/oauth/logout?client_id="+kakaoClientId+"&logout_redirect_uri="+LOGOUT_REDIRECT_URI;

			response.sendRedirect(url);
			return ;

		}else if(StringUtils.contains(provider,"google"))
		{
			System.out.println("authentication getPrincipal()  : "  + (PrincipalDetails)authentication.getPrincipal());
			System.out.println("getAccessToken()  : "  + ((PrincipalDetails) authentication.getPrincipal()).getAccessToken());
			String accessToken = ((PrincipalDetails) authentication.getPrincipal()).getAccessToken();

			//구글 LOGIN APP API 와 연결끊기
			String revokeUrl = "https://accounts.google.com/o/oauth2/revoke?token=" + accessToken;
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(revokeUrl, HttpMethod.GET, entity, String.class);

			System.out.println("GOOGLE LOGOUT Success!");

			//기존 세션 제거
			HttpSession session = request.getSession(false);
			if(session!=null)
				session.invalidate();



		}
		else if(StringUtils.contains(provider,"naver"))
		{
			String url = "http://nid.naver.com/nidlogin.logout";
			//RestTemplate restTemplate = new RestTemplate();
			//restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			response.sendRedirect(url);
			return ;
		}

		response.sendRedirect("/login");

	}

}
