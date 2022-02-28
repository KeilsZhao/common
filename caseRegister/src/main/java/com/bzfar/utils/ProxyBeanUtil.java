package com.bzfar.utils;

import com.bzfar.enums.CaseRegisterEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 根据法院代码来获取动态代理的实现类
 * 挺操蛋的一点是 文件夹名字就必须和法院代码的首字母一样
 * 半成品，后面想优化成一个动态代理类的子容器注入，然后通过容器来获取
 * @param <T>
 */
@Slf4j
@Component
public class ProxyBeanUtil<T> {

    public T target(Class proxyClass, CaseRegisterEnum type){
        Class<?> aClass = null;
        String packageName = proxyClass.getPackage().getName();
        String proxyClazzName = proxyClass.getSimpleName().replaceAll("Proxy" , type.getCode());
        String proxyPackageName = packageName.concat(".").concat(proxyClazzName);
        try {
            aClass = Class.forName(proxyPackageName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        T proxy = (T) SpringContextUtil.getBean(aClass);
        return proxy;
    }
}
