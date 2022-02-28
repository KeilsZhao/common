package com.bzfar.service.stand.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bzfar.service.stand.StandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StandServiceImpl implements StandService {
    @Autowired
    private ObjectMapper objectMapper;

    private String tokenUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/uaa/open/login";

    private String tokenClientId = "jyytj";

    private String tokenClientSecret = "jyfyytj923";

    private String ayUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/baseCode/getTsAyByLb";

    private String bzdmUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/baseCode/getTsBzdmByKind";

    private String laUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/lawCase/saveLawCase";

    private String imgUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/file/fileUpload";

    @Value("${api.filePath}")
    private String filePath;

    @Value("${api.PathUrl}")
    private String pathUrl;

    private String aesKey = "TDHLAWSUITCASE@!";

    private String fydm = "511427";


    @Override
    public List<Object> queryAy(String lb) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getToken());
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("lb", lb);
        String params = "";
        try {
            params = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("【queryAy change json has an error】 = {}", e.getMessage());
        }
        String body = tempJsonPost(ayUrl, params, headers);
        log.info("【通达海获取案由信息】 = {}", body);
        JSONObject res = JSON.parseObject(body);
        List<Object> ay = (List) res.get("data");
        return ay;
    }

    @Override
    public List<Object> queryBzdm(String kind) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getToken());
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("kind", kind);
        String params = "";
        try {
            params = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("【queryBzdm change json has an error】 = {}", e.getMessage());
        }
        String body = tempJsonPost(bzdmUrl, params, headers);
        log.info("【通达海获取法标数据信息】 = {}", body);
        JSONObject res = JSON.parseObject(body);
        List<Object> ay = (List) res.get("data");
        return ay;
    }

    /**
     * 获取token信息
     *
     * @return token
     */
    public String getToken() {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> map1 = new HashMap<String, Object>(2);
        map1.put("clientId", tokenClientId);
        map1.put("clientSecret", tokenClientSecret);
        String params = "";
        try {
            params = objectMapper.writeValueAsString(map1);
        } catch (JsonProcessingException e) {
            log.error("【get token change json has an error】 = {}", e.getMessage());
        }
        String body = tempJsonPost(tokenUrl, params, headers);
        JSONObject res = JSON.parseObject(body);
        String token = (String) res.get("data");
        log.info("【通达海获取token】 = {}", token);
        return token;
    }

    /**
     * 模板post  json请求
     *
     * @param url   路径
     * @param param 参数
     * @return 返回结果
     */
    private String tempJsonPost(String url, String param, HttpHeaders headers) {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        RestTemplate restTemplate = new RestTemplate();
        //构造实体对象
        HttpEntity<String> entity = new HttpEntity<String>(param, headers);
        //发起请求,服务地址，请求参数，返回消息体的数据类型
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        //body
        String body = response.getBody();
        return body;
    }
}
