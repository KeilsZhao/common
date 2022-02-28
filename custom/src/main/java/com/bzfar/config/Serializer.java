package com.bzfar.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author 刘成
 * @date 2021-6-3
 */
public class Serializer extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> list = new ArrayList<>();
        for(BeanPropertyWriter writer : beanProperties){
            JsonSerializer<Object> serializer = new JsonSerializer() {
                @Override
                public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    String str = value.toString();
                    Class<?> aClass = value.getClass();
                    String baseStr = Base64.getEncoder().encodeToString(str.getBytes());
                    jsonGenerator.writeString(baseStr);
                }
            };
            writer.assignSerializer(serializer);
            list.add(writer);
        }
        return list;
    }
}
