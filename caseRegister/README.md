# caseRegister

立案相关的接口



# 功能点

使用代理方式，提供不同不同实现类，根据传入枚举值不同，调用不同的实现方案



# 枚举值

```java
QICHUN("QiChun","蕲春立案");
```



# 使用

## 1.蕲春法院使用

#### 1.1 配置第三方提供接口地址

```yml
qichun:
  resisterUrl: http://ip:port/dwjk/httpservices/ytjla/yyxx
```

####	1.2 引入立案公共jar包

```java
<dependency>
    <groupId>org.bzfar</groupId>
    <artifactId>caseRegister</artifactId>
    <version>1.0</version>
</dependency>
```

####	1.3 调用接口使用详见maven使用说明文档

```java
@Autowired
private RegisterCaseService proxyService;

 @PostMapping("/getAllSource")
 public HttpResult<RegisterCaseVo> caseRegister(@Validated @RequestBody QiChunRegisterDto qiChunRegisterDto){
     RegisterCaseVo registerCaseVo = proxyService.caseRegister(CaseRegisterEnum.QICHUN.getCode(), qiChunRegisterDto);
     return HttpResult.ok(registerCaseVo);
}
```
