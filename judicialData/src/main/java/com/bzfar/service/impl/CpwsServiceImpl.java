package com.bzfar.service.impl;

import com.bzfar.dao.DataDao;
import com.bzfar.dto.CpwsDto;
import com.bzfar.po.DataInfo;
import com.bzfar.service.CpwsService;
import com.bzfar.util.AssertUtil;
import com.bzfar.vo.CpwsInfoVo;
import com.bzfar.vo.CpwsVo;
import org.apache.commons.lang3.RandomUtils;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
@Service
public class CpwsServiceImpl implements CpwsService {

    @Autowired
    private DataDao dataDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<CpwsVo> getCpwsList(CpwsDto cpwsDto) {
        String condition = cpwsDto.getCondition();
        AssertUtil.assertNull(condition, "没有查询条件");
        Integer num = cpwsDto.getNum();
        if (num == null || num == 0) {
            num = 10;
        }

        // 案由
        PageRequest pageRequest = PageRequest.of(0, 10);
        MatchQueryBuilder ay = QueryBuilders.matchQuery("ay", condition);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(ay)
                .build();
        int page = RandomUtils.nextInt(0, 100);
        pageRequest = PageRequest.of(page, num);
        searchQuery.setPageable(pageRequest);
        List<DataInfo> ayInfos = elasticsearchTemplate.queryForList(searchQuery, DataInfo.class);
        // 法院名称
        MatchQueryBuilder countName = QueryBuilders.matchQuery("countName", condition);
        NativeSearchQuery countNameQuery = new NativeSearchQueryBuilder()
                .withQuery(countName)
                .build();
        countNameQuery.setPageable(pageRequest);
        List<DataInfo> countNamePageInfos = elasticsearchTemplate.queryForList(countNameQuery, DataInfo.class);

        List<DataInfo> dataInfos = new ArrayList<DataInfo>();
        dataInfos.addAll(ayInfos);
        dataInfos.addAll(countNamePageInfos);
        AssertUtil.assertNull(dataInfos, "没有查询到数据");

        if (dataInfos.size() > num) {
            Collections.shuffle(dataInfos);
            dataInfos = dataInfos.subList(0, num);
        }

        List<CpwsVo> all = dataInfos.stream().map(item -> {
            return CpwsVo.builder()
                    .ah(item.getAh())
                    .time(item.getRefereeTime())
                    .title(item.getTitle())
                    .id(item.getId())
                    .type(item.getRefereeType()).build();
        }).collect(Collectors.toList());
        return all;
    }


    @Override
    public CpwsInfoVo getCpwsById(String id) {
        DataInfo daoById = dataDao.findById(id);
        CpwsInfoVo cpwsInfoVo = CpwsInfoVo.builder()
                .ah(daoById.getAh())
                .content(daoById.getContent())
                .title(daoById.getTitle())
                .build();
        return cpwsInfoVo;
    }
}
