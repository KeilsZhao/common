package com.bzfar.service.impl;

import com.bzfar.config.WaitInfoConfig;
import com.bzfar.dto.CaseBatchDto;
import com.bzfar.dto.WaitInfoDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.CaseBatchService;
import com.bzfar.service.WaitInfoService;
import com.bzfar.util.AssertUtil;
import com.bzfar.util.DateUtils;
import com.bzfar.utils.HandleFile;
import com.bzfar.utils.MockFileUtils;
import com.bzfar.utils.MockJarPathUtils;
import com.bzfar.vo.CaseBatchVo;
import com.bzfar.vo.WaitInfoVo;
import com.bzfar.vo.thrid.ThirdWaitInfoDataVo;
import com.bzfar.vo.thrid.ThirdWaitInfoListVo;
import com.bzfar.vo.thrid.ThirdWaitInfoVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WaitInfoServiceImpl implements WaitInfoService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private XmlMapper xmlMapper;

    @Autowired
    private CaseBatchService caseBatchService;

    @Autowired
    private WaitInfoConfig waitInfoConfig;

    @Override
    public List<WaitInfoVo> queryCourtAnnouncement(WaitInfoDto dto) {
        try{
            List<ThirdWaitInfoListVo> plKt = getPlKt(dto);
            List<WaitInfoVo> collect = plKt.stream().map(item -> {
                List<CaseBatchVo> caseBatchVos = queryCaseBatchByAhdm(item.getAhdm());
                AssertUtil.assertNull(caseBatchVos , "第三方案号代码查询信息为空");
                CaseBatchVo caseInfo = caseBatchVos.get(0);
                WaitInfoVo waitInfoVo = WaitInfoVo.builder()
                        .ay(caseInfo.getAyms())
                        .ah(item.getAh())
                        .courtAddress(item.getFtmc())
                        .courtTime(getDateTime(item.getKtrq() , item.getKtsj()))
                        .cbr(item.getCbrms())
                        .yg(getPlaintiffNames(caseInfo.getDsr()))
                        .bg(getDefendantNames(caseInfo.getDsr()))
                        .build();
                return waitInfoVo;
            }).collect(Collectors.toList());
            return collect;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }

    /**
     * 获取原告名称
     * @param nameStr
     * @return
     */
    private String getPlaintiffNames(String nameStr){
        AssertUtil.assertEmpty(nameStr , "第三方接口请求信息中被告信息为空");
        String plaintiffName = nameStr.substring(nameStr.indexOf("原告:") + 3 , nameStr.indexOf(";"));
        return plaintiffName;
    }

    /**
     * 获取被告名称
     * @param nameStr
     * @return
     */
    private String getDefendantNames(String nameStr){
        AssertUtil.assertEmpty(nameStr , "第三方接口请求信息中被告信息为空");
        String defendantNames = nameStr.substring(nameStr.indexOf("被告:") + 3 , nameStr.length());
        return defendantNames;
    }

    /**
     * 格式化时间
     * @param date 日期 : 20191217
     * @param time 时间: 09:00
     * @return
     */
    private Date getDateTime(String date , String time){
        try{
            if(date.length() > 8){
                throw new DataException("第三方数据格式变动，不为: 20191217类型");
            }
            if(ObjectUtils.isEmpty(date)){
                return null;
            }
            date = date.substring(0 , 4).concat("-").concat(date.substring(4 , 6).concat("-").concat(date.substring(6 , 8)));
            date = date.concat(" ").concat(time);
            Date dateTime = DateUtils.parseDate(date);
            return dateTime;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    };

    /**
     * 根据案号代码查询案件详情
     * @param ahdm
     * @return
     */
    private List<CaseBatchVo> queryCaseBatchByAhdm(String ahdm){
        try{
            CaseBatchDto caseBatchDto = CaseBatchDto.builder()
                    .ahdm(ahdm)
                    .zt("0")
                    .build();
            List<CaseBatchVo> caseBatchVos = caseBatchService.queryCaseBatchByThird(caseBatchDto);
            return caseBatchVos;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }

    /**
     * 第三方接口请求信息
     * @param dto
     * @return
     */
    private List<ThirdWaitInfoListVo> getPlKt(WaitInfoDto dto) {
        InputStream mockData = null;
        try{
//            String str = xmlMapper.writeValueAsString(dto);
//            String requestBaseStr = Base64.getEncoder().encodeToString(str.getBytes());
            List<ThirdWaitInfoListVo> waitInfoListVoList = new ArrayList<>();
            if(waitInfoConfig.isWaitInfoDebug()){
                HandleFile handleFile = new MockJarPathUtils();
                mockData = handleFile.handleMockData("WaitInfoMock.json");
                ThirdWaitInfoDataVo thirdWaitInfoDataVo = objectMapper.readValue(mockData, ThirdWaitInfoDataVo.class);
                waitInfoListVoList = thirdWaitInfoDataVo.getData().getThirdWaitInfoVo().getWaitInfoListVoList();
            }
            return waitInfoListVoList;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        } finally {
            try {
                if (mockData != null) {
                    mockData.close();
                }
            } catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
    }
}
