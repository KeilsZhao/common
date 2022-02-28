package com.bzfar.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Base64;

/**
 * @author Ethons
 * @date 2021-6-11 14:43
 */
public class StringSerializer extends JsonSerializer<String> {


    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(!ObjectUtils.isEmpty(value)){
            String s = Base64.getEncoder().encodeToString(value.getBytes());
            gen.writeString(s);
        }
    }
}
