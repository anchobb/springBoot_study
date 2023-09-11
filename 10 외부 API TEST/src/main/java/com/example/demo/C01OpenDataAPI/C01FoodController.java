package com.example.demo.C01OpenDataAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/openData")
@Slf4j
public class C01FoodController {

    @GetMapping(value =  "/food/{addr}", produces = MediaType.APPLICATION_JSON_VALUE)
    private Object getFood(@PathVariable String addr) throws Exception {
        log.info("GET /openData/food/" + addr);
        Map<String, String> returnMap = new HashMap();


        //URL
        String url = "https://www.daegufood.go.kr/kor/api/tasty.html?mode=json&addr="+addr;
        //HEADER

        //PARAMETER

        //REQUEST
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, null, String.class);
                                            //exchange를 쓸 때 url, 요청방식, 헤더파라미터정보엔티티, 응답받을 자료형
        System.out.println(response);
        System.out.println(response.getBody());

        //String -> JSON 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject result =  objectMapper.readValue(response.getBody(), JSONObject.class);
        //String으로 되어져있는 바디부분을 다시 JSON형태로 파싱
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(String.valueOf(response.getBody()));

        JSONArray data_value = (JSONArray) data.get("data"); //[{},{},{}]
        data_value.forEach(element -> {
            JSONObject data_object = (JSONObject) element;
            returnMap.put(data_object.get("BZ_NM").toString(), data_object.get("GNG_CS").toString());
            System.out.println(data_object.get("BZ_NM")+" - " + data_object.get("GNG_CS"));
        });

        return returnMap;
    }
}
