package com.bzfar.service;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * 责任链处理短信
 *
 */
@Slf4j
public abstract class HandleSms {

    /** 下一个发送短信的方式 */
    private HandleSms nextHandleSms;

    /** 发送短信服务器的ip */
    private String ip;

    /** 设置ping ip时的超时时间 */
    private static final int timeOut = 1000;

    public HandleSms(String ip) {
        this.ip = ip;
    }

    /** 是否可以用此ip是否可用
     * 假如可用，则用此方式发送短信
     * 如果不可用，则采用nextHandleSms中的方式发送短信
     * */
    public static boolean ping(String ipAddress){
        boolean result = false;
        try{
            return InetAddress.getByName(ipAddress).isReachable(timeOut);
        }catch (Exception e){
            log.info("【ping ip出错】msg = {}" , e.getMessage());
        }
        return result;
    }

    public final String handleSms(String phone , String context){
        if(ping(this.ip)){
           return sendSms(phone , context);
        }else{
            if(this.nextHandleSms != null){
               return this.nextHandleSms.handleSms(phone , context);
            }else{
                return "已经没有后续的短信发送方式了";
            }
        }
    }

    /** 设置下一个处理请求 */
    public void setNextHandle(HandleSms nextHandle) {
        this.nextHandleSms = nextHandle;
    }


    /** 发送短信 */
    protected abstract String sendSms(String phone , String context);
}
