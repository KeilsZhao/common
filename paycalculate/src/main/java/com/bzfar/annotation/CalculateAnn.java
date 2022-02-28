package com.bzfar.annotation;

import com.bzfar.exception.DataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 获取所有已经存在的方法,在项目启动时
 */
@Component
@Slf4j
public class CalculateAnn implements CommandLineRunner {

    public static Map<String,String> methodMap = new HashMap<String,String>();

    @Override
    public void run(String... args) throws Exception {
        try {
            Class<?> aClass = Class.forName("com.bzfar.service.impl.JiaofeiServiceImpl");
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                //过滤不含自定义注解AnnotationInfo的方法
                boolean isHasAnnotation = method.isAnnotationPresent(CalculateMethod.class);
                if (isHasAnnotation) {
                    method.setAccessible(true);
                    //获取方法上的注解
                    CalculateMethod aInfo = method.getAnnotation(CalculateMethod.class);
                    if (aInfo == null) {continue;};
                    //解析注解上对应的信息
                    String type = aInfo.type();
                    String methodName = method.getName();
                    methodMap.put(type,methodName);
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new DataException("计算错误");
        }
    }
}
