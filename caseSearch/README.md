# **caseSearch**

案件查询通用的类库

# **Maven库**

maven库地址: http://60.205.180.13:8081/nexus

用户名: admin

密码: bzfar

maven

```xml
<dependency>
  <groupId>org.bzfar</groupId>
  <artifactId>caseSearch</artifactId>
  <version>1.0</version>
</dependency>
```



# 说明

短信发送通用类库使用方法

```java
@Resource
private CaseSearchService caseSearchService;
```

```java
caseListVo = caseSearchService.searchCase(caseSearchDto);
```



请求参数在引入maven后直接实例即可

```java
public class CaseSearchDto {

    @ApiModelProperty("案件编号")
    private String ah;

    @ApiModelProperty("证件号")
    private String zjh;

    @ApiModelProperty("身份证号")
    private String sfzh;

}
```