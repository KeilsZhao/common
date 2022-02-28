package com.bzfar.service;

import com.bzfar.entity.DataInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface PhpBeanService {

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
}
