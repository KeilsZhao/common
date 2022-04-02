package com.bzfar.http;


import com.bzfar.exception.DataException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.Map;

/**
 * @author Ethons
 * @date 2022-3-29 15:00
 */
@Component
public class HttpPost {

    @Autowired
    private RestTemplate restTemplate;

    private Object json(String url , HttpHeaders httpHeads , Map<String , Object> mapParam , ParameterizedTypeReference result){
        httpHeads.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity entity = new HttpEntity(mapParam, httpHeads);
        Object exchange = exchange(url, HttpMethod.POST, entity, result);
        return exchange;
    }


    public <T> Object exchange(String url, HttpMethod method, HttpEntity entity, ParameterizedTypeReference<T> result){
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, result);
        if(!exchange.getStatusCode().equals(HttpStatus.OK)){
            throw new DataException("网络请求失败" + exchange.getBody());
        }
        T body = exchange.getBody();
        return body;
    }
}
