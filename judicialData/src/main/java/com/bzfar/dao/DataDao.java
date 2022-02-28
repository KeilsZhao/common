package com.bzfar.dao;


import com.bzfar.po.DataInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘成
 * @date 2021-4-29
 */
@Component
public interface DataDao extends ElasticsearchRepository<DataInfo, Integer> {

    /**
     * 根据文书id获取详情
     *
     * @param id 文书ID
     * @return 文书详情
     */
    DataInfo findById(String id);
}
