package com.bzfar.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

/***
 * 把对象中的每个属性值进行Base64加密序列化
 * @param <T>
 */
@Slf4j
public class Base64Serializer<T extends Serializable> extends StdSerializer<T> {

    private static final long serialVersionUID = 1L;

    protected Base64Serializer(Class<?> t, boolean f) {
        super(t, f);
    }


    @Override
    public void serialize(T value, JsonGenerator jsonGenerator, SerializerProvider arg2) throws IOException {
        if(value instanceof Collection){
            try{
                ((Collection<?>) value).forEach(item -> {
                    Class<?> clazz = item.getClass();
                    try {
                        Field[] declaredFields = clazz.getDeclaredFields();
                        for (Field field : declaredFields){
                            String filedName = field.getName();
                            if(filedName.equals("serialVersionUID")){
                                continue;
                            }
                            field.setAccessible(true);
                            field.set(item , "1233123");
                        }
                    }catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }

            jsonGenerator.writeString(value.toString());
        }else{
            String encodedOutput = Base64.getEncoder().encodeToString(serialize(value));
            jsonGenerator.writeString(encodedOutput);
        }
    }


    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static void addSerializers(SimpleModule module, Class... classes) {
        Arrays.stream(classes).forEach(c -> module.addSerializer(new Base64Serializer(c, false)));
    }
}
