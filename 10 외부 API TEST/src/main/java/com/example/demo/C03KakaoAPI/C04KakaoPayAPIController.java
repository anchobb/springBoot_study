package com.example.demo.C03KakaoAPI;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/th/kakao/pay")
public class C04KakaoPayAPIController {

    private final String ADMIN_KEY = "ae39e2b27d5011a69f526b76f277ed62";
    @GetMapping("/index")
    public void payIndex() {
        log.info("GET /th/kakao/pay/index");
    }

    @GetMapping("/request")
    public @ResponseBody PaymentResponse pay() {

        //URL(access토큰 받기)
        String url = "https://kapi.kakao.com/v1/payment/ready";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK "+ ADMIN_KEY);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        //PARAMETER
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "partner_order_id");
        params.add("partner_user_id", "partner_user_id");
        params.add("item_name","초코파이");
        params.add("quantity","1");
        params.add("total_amount", "2200");
        params.add("vat_amount", "200");
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/th/kakao/pay/success");
        params.add("fail_url", "http://localhost:8080/th/kakao/pay/fail");
        params.add("cancel_url", "http://localhost:8080/th/kakao/pay/cancel");

        //HEADER + PARAMETER
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);

        //REQUEST_01
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
//        //JSON->Class 변환
//        System.out.println(response);
//        System.out.println(response.getBody());

        //REQUEST_02
        RestTemplate rt = new RestTemplate();
        PaymentResponse response = rt.postForObject(url, entity, PaymentResponse.class);
        System.out.println(response);

        return response;
    }

    @GetMapping("/success")
    public @ResponseBody String success() {
        log.info("GET /th/kakao/pay/success");
        return "결제 성공";
    }
    @GetMapping("/cancel")
    public @ResponseBody String cancel() {
        log.info("GET /th/kakao/pay/cancel");
        return "결제 취소";
    }
    @GetMapping("/fail")
    public @ResponseBody String fail() {
        log.info("GET /th/kakao/pay/fail");
        return "결제 실패";
    }

}

//////////////////////////////////////////
@Data
class PaymentResponse {
    private String tid;
    private boolean tms_result;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private String created_at;

}
