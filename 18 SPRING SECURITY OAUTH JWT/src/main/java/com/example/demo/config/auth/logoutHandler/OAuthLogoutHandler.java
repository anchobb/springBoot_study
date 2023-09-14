package com.example.demo.config.auth.logoutHandler;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.config.auth.jwt.JwtProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OAuthLogoutHandler implements LogoutHandler { //logout handler 구현

    private String naverClientId ="UpgXfxXYcvRhS88NTl4F";

    private String naverClientSecret="oMfVdIYdWm";
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //JWT 쿠키 만료하는 작업!
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);


        System.out.println("authentication: " + authentication);
        System.out.println("authentication.getPrincipal(): " + authentication.getPrincipal());
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("provider: " + principalDetails.getUser().getProvider());

        String provider = (String) principalDetails.getUser().getProvider();
        String accessToken = ((PrincipalDetails)authentication.getPrincipal()).getAccessToken();
        if(provider!=null && StringUtils.contains(provider,"kakao")){
            System.out.println("GET /th/kakao/logout");

            //URL(access토큰 받기)
            String url = "https://kapi.kakao.com/v1/user/logout";
            //HEADER
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer "+accessToken);
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            //HEADER + PARAMETER
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
            //REQUEST_01
            RestTemplate rt = new RestTemplate();
            ResponseEntity<String> rs = rt.exchange(url, HttpMethod.POST, entity, String.class);
            //JSON->Class 변환
            System.out.println(rs);
            System.out.println(rs.getBody());

        }else if(provider!=null && StringUtils.contains(provider,"google")){
            String url ="https://accounts.google.com/o/oauth2/revoke?token="+accessToken;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(url,HttpMethod.GET,null,String.class);

            HttpSession session = request.getSession(false);
            if(session!=null)
                session.invalidate();

        }
        else if(provider!=null&&StringUtils.contains(provider,"naver"))
        {

            //https://developers.naver.com/docs/login/devguide/devguide.md#5-3-1-%EB%84%A4%EC%9D%B4%EB%B2%84-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%97%B0%EB%8F%99-%ED%95%B4%EC%A0%9C%EA%B0%80-%ED%95%84%EC%9A%94%ED%95%9C-%EA%B2%BD%EC%9A%B0

            System.out.println("[LOGOUT_HANDLER] naverClientId : " + naverClientId);
            System.out.println("[LOGOUT_HANDLER] naverClientSecret : " + naverClientSecret);

            System.out.println("[LOGOUT_HANDLER] authentication  : "  + authentication);
            System.out.println("[LOGOUT_HANDLER] authentication getPrincipal()  : "  + (PrincipalDetails)authentication.getPrincipal());
            System.out.println("[LOGOUT_HANDLER] getAccessToken()  : "  + ((PrincipalDetails) authentication.getPrincipal()).getAccessToken());



            // 네이버 API 로그아웃을 위한 URL 생성
            String logoutUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id="
                    + naverClientId + "&client_secret=" + naverClientSecret + "&access_token=" + accessToken+"&service_provider=NAVER";



            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(logoutUrl, HttpMethod.GET, entity, String.class);

            System.out.println("NAVER LOGOUT Success!");
            //기존 세션 제거
            HttpSession session = request.getSession(false);
            if(session!=null)
                session.invalidate();

        }
    }
}
