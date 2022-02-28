package com.bzfar.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

@Component
public class MetaHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date time = calendar.getTime();
        this.setFieldValByName("createTime" , date , metaObject);
        this.setFieldValByName("yearTime" , Year.now(), metaObject);
        this.setFieldValByName("monthTime" , time , metaObject);
        this.setFieldValByName("updateTime" , date , metaObject);
        /** 预约过期时间12小时为过期 11小时的时候出现提示 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 11);
        this.setFieldValByName("outTime" , calendar.getTime() , metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime" , new Date() , metaObject);
    }
}
