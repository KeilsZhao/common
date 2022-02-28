# 文件管理

通用文件管理项目



## 功能

##### 20210610添加：材料补交接口

```java
 /**
     * 材料补交时获取流水码
     *
     * @param fileBackDto 流水码请求参数
     * @return
     */
    String getSerialNumber(FileBackSerialDto fileBackDto);


    /**
     * 文件补交提交文件
     *
     * @param fileBackDto 提交文件信息
     * @return
     */
    String putFileInfo(FileBackDto fileBackDto);
```

