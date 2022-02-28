package com.bzfar.handle;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.bzfar.HeadContext;
import com.bzfar.table.TenantLineInner;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.axis.utils.StringUtils;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
@Order(2)
public class TenantLineInnerHandler implements TenantHandler {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Expression getTenantId(boolean select) {
        String deviceNum = HeadContext.getDeviceNum();
        if(StringUtils.isEmpty(deviceNum)){
            deviceNum = request.getHeader("x-device");
        }
        if(null == deviceNum){
            return new NullValue();
        }
   //     return new BzcyStringValue(deviceNum);
        return new StringValue("'" + deviceNum + "'");
    }

    @Override
    public String getTenantIdColumn() {
        return "device";
    }

    @Override
    public boolean doTableFilter(String tableName) {
        List<String> tables = new ArrayList();
        Reflections reflections = new Reflections("com.bzfar.entity", new Scanner[0]);
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(TenantLineInner.class);
        Iterator var4 = classesList.iterator();
        while(var4.hasNext()) {
            Class clazz = (Class)var4.next();
            TableName annotation = (TableName) clazz.getAnnotation(TableName.class);
            if(null == annotation){
                String name = clazz.getSimpleName();
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
                name = name.replaceAll("([A-Z]+)", "_$1").toLowerCase();
                tables.add(name);
            }else{
                String name = annotation.value();
                tables.add(name);
            }
        }
        boolean filter = true;
        if(tables.size() > 0){
            if(tables.contains(tableName)){
                filter = false;
            }
//            filter = tables.stream().anyMatch((e) -> e.equals(tableName));
        }
        return filter;
    }
}
