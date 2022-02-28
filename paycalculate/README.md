# **payCalculate**

缴费计算通用的类库

# **Maven库**

maven库地址: http://60.205.180.13:8081/nexus

用户名: admin

密码: bzfar

maven：

```xml
<dependency>
  <groupId>org.bzfar</groupId>
  <artifactId>payCalculate</artifactId>
  <version>1.0</version>
</dependency>
```



# **说明**

缴费计算通用类库，使用方法

```
@Autowired
private PayCalculateService payCalculateService;
```



```
payCalculateService.calculate(calculateDto);
```



传入参数（引入jar包后，直接实例化即可）

```
public class CalculateDto {

    @ApiModelProperty("计算类型")
    @NotBlank(message = "计算类型不能为空")
    private String type;

    @ApiModelProperty("费用")
    @NotBlank(message = "计算费用不能为空")
    private String fee;

}
```