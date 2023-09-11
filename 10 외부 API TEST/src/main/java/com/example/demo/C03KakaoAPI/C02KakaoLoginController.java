package com.example.demo.C03KakaoAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/th/kakao")
public class C02KakaoLoginController {

    private final String CLIENT_ID = "a8eb71737b050265eaa778caba7f2bbb";
    private final String REDIRECT_URI = "http://localhost:8080/th/kakao/callback";
    private final String LOGOUT_REDIRECT_URI = "http://localhost:8080/th/kakao/login";


    //access-token/refresh-token/expires-in..
    private KakaoTokenResponse kakaoTokenResponse;
    private KakaoProfile kakaoProfile;

    //인가코드 요청 URL
    //https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a8eb71737b050265eaa778caba7f2bbb&redirect_uri=http://localhost:8080/th/kakao/callback

    @GetMapping("/getCode")
    public void authorize(HttpServletResponse response) throws Exception {
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI;
        response.sendRedirect(url);
    }

    @GetMapping("/callback")
    public String redirectFunc(String code) {

        log.info("GET /th/kakao/callback CODE : " + code);

        //URL(access토큰 받기)
        String url = "https://kauth.kakao.com/oauth/token";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        //PARAMETER
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);

        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers); //첫번째가 파라미터, 두번째가 헤더
        //REQUEST_01
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        //JSON->Class 변환
//        System.out.println(response);
//        System.out.println(response.getBody());

        //REQUEST_02
        RestTemplate rt = new RestTemplate();
        KakaoTokenResponse response = rt.postForObject(url, entity, KakaoTokenResponse.class);
        System.out.println(response);
        this.kakaoTokenResponse = response;
        return "redirect:/th/kakao/main";


    }

    @GetMapping("/main")
    public void main() {
        log.info("GET /th/kakao/main");
    }

    @GetMapping("/login")
    public void login() {
        log.info("GET /th/kakao/login");
    }

    @GetMapping("/profile")
    public @ResponseBody KakaoProfile profile() {
        log.info("GET /th/kakao/profile");
        //URL(access토큰 받기)
        String url = "https://kapi.kakao.com/v2/user/me";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+kakaoTokenResponse.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

        //REQUEST_01
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
//        //JSON->Class 변환
//        System.out.println(response);
//        System.out.println(response.getBody());

        //REQUEST_02
        RestTemplate rt = new RestTemplate();
        KakaoProfile response = rt.postForObject(url, entity, KakaoProfile.class);
        System.out.println(response);
        this.kakaoProfile = response;
        return response;
    }

    //나에게 메시지 보내기 기능 구현
    //01 Scope=talk_message 권한 부여받기
    @GetMapping("/getCodeMsg")
    public void authorize_Msg(HttpServletResponse response) throws Exception {
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&scope=talk_message";
        response.sendRedirect(url);
    }

    @GetMapping("/message/me/{message}")
    public Object sendMessageMe(@PathVariable("message") String message) {

        //URL
        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+kakaoTokenResponse.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        //PARAMETER
        JSONObject template_object = new JSONObject();
        template_object.put("object_type", "text");
        template_object.put("text", message);
        template_object.put("link", new JSONObject());
        template_object.put("button_title", "바로 확인");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("template_object", template_object.toString());


        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers); //첫번째가 파라미터, 두번째가 헤더
        //REQUEST_01
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        //JSON->Class 변환
        System.out.println(response);
        System.out.println(response.getBody());
        return null;
    }

    //로그아웃(토큰 만료)
    @GetMapping("/logout")
    public String logout() {
        System.out.println("GET /th/kakao/logout");

        //URL(access토큰 받기)
        String url = "https://kapi.kakao.com/v1/user/logout";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+kakaoTokenResponse.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        //REQUEST_01
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        //JSON->Class 변환
        System.out.println(response);
        System.out.println(response.getBody());

        return "redirect:/th/kakao/logoutWithKakao";

    }

    @GetMapping("/logoutWithKakao")
    public @ResponseBody void logoutWithKakao(HttpServletResponse response) throws IOException {
        System.out.println("GET /th/kakao/logoutWithKakao");

        //URL(access토큰 받기)
        String url = "https://kauth.kakao.com/oauth/logout?client_id="+CLIENT_ID+"&logout_redirect_uri="+LOGOUT_REDIRECT_URI;

        response.sendRedirect(url);
    }



}

////////////////////////////////////////////////////////////////
@Data
class KakaoTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
    private long refresh_token_expires_in;
}
/////////////////////////////////////////////////////////////
@Data
class KakaoProfile {
    @JsonProperty("id")
    private long id;

    @JsonProperty("connected_at")
    private String connected_at;
    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("kakao_account")
    private Kakao_account kakao_account;

    @Data
    @NoArgsConstructor
    public static class Kakao_account {
        private boolean profile_nickname_needs_agreement;
        private boolean profile_image_needs_agreement;
        private Profile profile;
        private boolean has_email;
        private boolean email_needs_agreement;
        private String is_email_valid;
        private String is_email_verified;
        private String email;
        private boolean has_age_range;
        private boolean age_range_needs_agreement;
        private String age_range;
    }

    @Data
    @NoArgsConstructor
    public static class Profile {
        private String nickname;
        private String thumbnail_image_url;
        private String profile_image_url;
        private String is_default_image;

    }

    @Data
    @NoArgsConstructor
    public static class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }
}