# **sms**

短信发送通用的类库

# **Maven库**

maven库地址: http://60.205.180.13:8081/nexus

用户名: admin

密码: bzfar

maven

```xml
<dependency>
  <groupId>org.bzfar</groupId>
  <artifactId>sms</artifactId>
  <version>1.0</version>
</dependency>
```



# 说明

短信发送通用类库使用方法

```java
@Autowired
private SmsService smsService;
```

```java
smsService.sendMqSms(phone,context);
```



配置文件添加mq配置

```yaml
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
```