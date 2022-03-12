# 短信与邮件发送

短信发送通用的类库

## Maven库

maven库地址: http://60.205.180.13:8081/nexus

用户名: admin

密码: bzfar

## 短信网关信息
### 内网
#### 武汉中院
> 网关已做光闸穿透，可以从内网发送短信

管理平台地址: `https://zx.ums86.com`  
企业编号: `210145`  
账号: `wh_zj`  
密码: `rmfy2001`

### 外网
#### 云南法院
管理平台地址: `https://c.ipyy.net/?DisplayNotice=True`  
账号: `yunnanyungui`  
密码: `123456`  
登录后的手机验证码: `15927585812` *手机号码接收人为: 胡智慧*

#### 通用
管理平台地址: `https://bh.ums86.com/`  
用户名: `wh_bzcy`  
密码: `Bzcy2019`  
企业编号: `264268`


## 短信发送通用类库使用方法

```java
@Autowired
private SmsService smsService;
```

```java
smsService.sendMqSms(phone,context);
```



## 配置文件添加mq配置

```yaml
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
```