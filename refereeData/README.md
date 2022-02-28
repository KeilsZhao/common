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
  <artifactId>refereeData</artifactId>
  <version>1.0</version>
</dependency>
```

# 说明

短信发送通用类库使用方法

```java
@Resource
private PhpBeanService  phpBeanService;
```

方法提供：

```java
/**
 * 查询类似案由
 * @param ay
 * @param max
 * @return
 */
@Async("doSomethingExecutor")
CompletableFuture<List<DataInfo>> appInfo(String ay , Integer max);


/**
 * 案由模糊搜索
 * @param ay 案由
 * @param max 最大条数
 * @return
 */
@Async("doSomethingExecutor")
CompletableFuture<List<DataInfo>> causeFuzzySearch(String ay , Integer max);

/**
 * 法院名称模糊搜索
 * @param courtName 法院名称
 * @param max 最大条数
 * @return
 */
@Async("doSomethingExecutor")
CompletableFuture<List<DataInfo>> courtNameFuzzySearch(String courtName , Integer max);

/**
 * 查询案件详细信息
 * @param id
 * @return
 */
@Async("doSomethingExecutor")
CompletableFuture<DataInfo> queryAppInfoById(String id);
```