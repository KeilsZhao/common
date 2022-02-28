# refereeData

裁判文书

# **Maven库**

maven库地址: http://60.205.180.13:8081/nexus

用户名: admin

密码: bzfar

maven

```
<dependency>
  <groupId>org.bzfar</groupId>
  <artifactId>judicialData</artifactId>
  <version>1.0</version>
</dependency>
```

# 说明

裁判文书elasticsearch

```java
@Resource
private CpwsService  cpwsService;
```

方法提供：

```java
/**
 * 根据案由或法院名称进行模糊查询，随机获取10条
 * @param cpwsDto 案由或法院名称+显示条数
 * @return
 */
    List<CpwsVo> getCpwsList(CpwsDto cpwsDto);


        /**
         * 根据ID获取文书详情
         * @param id 文书ID
         * @return 文书详情
         */
        CpwsInfoVo getCpwsById(String id);
```