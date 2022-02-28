package com.bzfar.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

/***
 * 把对象中的每个属性值进行Base64解密密序列化
 * @param <T>
 */
@Slf4j
public class Base64Deserializer<T extends Serializable> extends StdDeserializer<Object> {


    protected Base64Deserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        char[] textCharacters = p.getTextCharacters();
        String s = String.valueOf(textCharacters);
        byte[] decode = Base64.getDecoder().decode(s);
        String decodeStr = new String(decode);
        return decodeStr;
    }


    public static void addSerializers(SimpleModule module, Class... classes) {
        Arrays.stream(classes).forEach(c -> module.addDeserializer(c ,new Base64Deserializer(c)));
    }
}
