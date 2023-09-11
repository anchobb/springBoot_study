package com.example.demo.C04NaverAPI;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/th/naver")
public class NaverSearchAPIController {

    private final String NAVER_CLIENT_ID = "UpgXfxXYcvRhS88NTl4F";
    private final String NAVER_CLIENT_SECRET = "oMfVdIYdWm";
    @GetMapping("/local/{page}/{keyword}") //외부로부터 keyword받을 예정. @PathVariable로 String 잡기.
    public @ResponseBody String local(@PathVariable String page, @PathVariable String keyword){
        //ResponseBody로 return할 경우, 웹페이지로 데이터 리턴 가능!
        log.info("GET /th/naver/local");

        //URL(access토큰 받기)
        String url = "https://openapi.naver.com/v1/search/local.json?query="+keyword+"&display=5&start="+page;
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", NAVER_CLIENT_ID);
        headers.add("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);

        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

        //REQUEST_01
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, entity, String.class);
        //JSON->Class 변환
        System.out.println(response);
        System.out.println(response.getBody());

        //REQUEST_02
//        RestTemplate rt = new RestTemplate();
//        KakaoProfile response = rt.postForObject(url, entity, KakaoProfile.class);
//        System.out.println(response);
//        this.kakaoProfile = response;

        return response.getBody();

    }

    @GetMapping("/image/{page}/{keyword}") //외부로부터 keyword받을 예정. @PathVariable로 String 잡기.
    public @ResponseBody String image(@PathVariable String page, @PathVariable String keyword) {
        //ResponseBody로 return할 경우, 웹페이지로 데이터 리턴 가능!
        log.info("GET /th/naver/image");

        //URL(access토큰 받기)
        String url = "https://openapi.naver.com/v1/search/image.json?query=" + keyword + "&display=10&start=" + page;
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", NAVER_CLIENT_ID);
        headers.add("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);

        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

        //REQUEST_01
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, entity, String.class);
        //JSON->Class 변환
        System.out.println(response);
        System.out.println(response.getBody());

        //REQUEST_02
//        RestTemplate rt = new RestTemplate();
//        KakaoProfile response = rt.postForObject(url, entity, KakaoProfile.class);
//        System.out.println(response);
//        this.kakaoProfile = response;

        return response.getBody();
    }

    }
