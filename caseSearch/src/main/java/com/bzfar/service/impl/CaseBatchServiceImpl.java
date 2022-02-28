package com.bzfar.service.impl;

import com.bzfar.config.WaitInfoConfig;
import com.bzfar.dto.CaseBatchDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.CaseBatchService;
import com.bzfar.utils.HandleFile;
import com.bzfar.utils.MockFileUtils;
import com.bzfar.utils.MockJarPathUtils;
import com.bzfar.vo.CaseBatchVo;
import com.bzfar.vo.thrid.ThirdCaseBatchDataVo;
import com.bzfar.vo.thrid.ThirdCaseBatchInfoVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CaseBatchServiceImpl implements CaseBatchService {

    @Autowired
    private WaitInfoConfig waitInfoConfig;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<CaseBatchVo> queryCaseBatchByThird(CaseBatchDto dto) {
        try{
            List<CaseBatchVo> result = new ArrayList<>();
            if(waitInfoConfig.isWaitInfoDebug()){
                HandleFile handleFile = new MockJarPathUtils();
                InputStream mockData = handleFile.handleMockData("CaseBatchMock.json");
                ThirdCaseBatchDataVo thirdCaseBatchDataVo = objectMapper.readValue(mockData, ThirdCaseBatchDataVo.class);
                List<ThirdCaseBatchInfoVo> batchInfoVoList = thirdCaseBatchDataVo.getBatchInfoVoList();
                result = batchInfoVoList.stream().map(item -> {
                    CaseBatchVo caseBatchVo = new CaseBatchVo();
                    BeanUtils.copyProperties(item, caseBatchVo);
                    return caseBatchVo;
                }).collect(Collectors.toList());
            }
            return result;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }
}
