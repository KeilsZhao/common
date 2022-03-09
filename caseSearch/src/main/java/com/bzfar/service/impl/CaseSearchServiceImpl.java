package com.bzfar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bzfar.config.WaitInfoConfig;
import com.bzfar.dto.CaseSearchDto;
import com.bzfar.dto.CaseSearchInfoDto;
import com.bzfar.enums.ResponseCode;
import com.bzfar.exception.DataException;
import com.bzfar.service.CaseSearchService;
import com.bzfar.util.AssertUtil;
import com.bzfar.util.DateUtils;
import com.bzfar.utils.HandleFile;
import com.bzfar.utils.MockJarPathUtils;
import com.bzfar.utils.MockUtil;
import com.bzfar.vo.*;
import com.bzfar.vo.caseSearch.ThirdCaseResultVo;
import com.bzfar.vo.caseSearch.ThirdCaseVo;
import com.bzfar.vo.caseSearch.newCaseInfo.*;
import com.bzfar.vo.caseSearch.newCaseInfo.mock.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案件查询
 */
@Service
@Slf4j
public class CaseSearchServiceImpl implements CaseSearchService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WaitInfoConfig waitInfoConfig;

    @Override
    public CaseListVo searchCase(CaseSearchDto caseSearchDto) {
//        if (caseSearchDto == null || (StringUtils.isBlank(caseSearchDto.getAh()) && StringUtils.isBlank(caseSearchDto.getSfzh()) && StringUtils.isBlank(caseSearchDto.getZjh()))) {
//            throw new DataException("查询条件为空");
//        }
        InputStream mockData = null;
        try {
            List<CaseVo> caseVos = new ArrayList<>();
            if (waitInfoConfig.isCaseSearchDebug()) {
                if (StringUtils.isNotBlank(caseSearchDto.getSfzh()) && caseSearchDto.getSfzh().length() != 18) {
                    throw new DataException(ResponseCode.BAD_REQUEST);
                }
                if (StringUtils.isNotBlank(caseSearchDto.getZjh()) && caseSearchDto.getZjh().length() != 17 && caseSearchDto.getZjh().length() != 18) {
                    throw new DataException(ResponseCode.BAD_REQUEST);
                }
                HandleFile handleFile = new MockJarPathUtils();
                mockData = handleFile.handleMockData("CaseSearchMock.json");
                ThirdCaseResultVo thirdCaseResultVo = objectMapper.readValue(mockData, ThirdCaseResultVo.class);
                List<ThirdCaseVo> data = thirdCaseResultVo.getData();
                // 案号
                if (StringUtils.isNotBlank(caseSearchDto.getAh())) {
                    data = data.stream().filter(item -> item.getAh().equals(caseSearchDto.getAh())).collect(Collectors.toList());
                    if (data.size() == 0) {
                        throw new DataException("案号 暂无信息");
                    }
                }
                // 身份证号
                if (StringUtils.isNotBlank(caseSearchDto.getSfzh())) {
                    data = data.stream().filter(item -> item.getSfzh().equals(caseSearchDto.getSfzh())).collect(Collectors.toList());
                    if (data.size() == 0) {
                        throw new DataException("身份证 暂无信息");
                    }
                }
                // 律师资格证
                if (StringUtils.isNotBlank(caseSearchDto.getZjh()) && caseSearchDto.getZjh().length() == 17) {
                    data = data.stream().filter(item -> item.getLszgzh().equals(caseSearchDto.getZjh())).collect(Collectors.toList());
                }
                // 社会信用代码
                if (StringUtils.isNotBlank(caseSearchDto.getZjh()) && caseSearchDto.getZjh().length() == 18) {
                    data = data.stream().filter(item -> item.getShxydm().equals(caseSearchDto.getZjh())).collect(Collectors.toList());
                }
                if (StringUtils.isNotBlank(caseSearchDto.getZjh()) && data.size() <= 0) {
                    throw new DataException("证件号 暂无信息");
                }
                // 关键字：案号，承办人，当事人
                String key = caseSearchDto.getKey();
                if (StringUtils.isNotBlank(key)) {
                    data = data.stream().filter(item -> item.getAh().contains(key) || item.getCbrmc().contains(key) || item.getDsr().contains(key)).collect(Collectors.toList());
                }
                caseVos = data.stream().map(thirdCaseVo -> {
                    return CaseVo.builder()
                            .ah(thirdCaseVo.getAh())
                            .type(thirdCaseVo.getAjlb())
                            .status(thirdCaseVo.getAjzt())
                            .ay(thirdCaseVo.getAyms())
                            .larq(StringUtils.isBlank(thirdCaseVo.getLarq()) ? "—" : DateUtils.formatStringData(thirdCaseVo.getLarq()))
                            .jarq(StringUtils.isBlank(thirdCaseVo.getJarq()) ? "—" : DateUtils.formatStringData(thirdCaseVo.getJarq()))
                            .cbbm(thirdCaseVo.getCbbm())
                            .cbr(thirdCaseVo.getCbrmc())
                            .sycx("普通程序")
                            .yg(getYgName(thirdCaseVo.getDsr()))
                            .bg(getBgName(thirdCaseVo.getDsr()))
                            .ahdm(thirdCaseVo.getAhdm())
                            .build();
                }).collect(Collectors.toList());
            }
            return CaseListVo.builder().caseVos(caseVos).build();
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        } finally {
            try {
                if (null != mockData) {
                    mockData.close();
                }
            } catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
    }

    @Override
    public CaseListVo searchSc(CaseSearchDto caseSearchDto) {
        log.info("[案件查询请求参数] = {}", caseSearchDto);
        String url = "http://www.scssfw.gov.cn:7083/ssfw/ssfw_app/app/wajjbxx";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
        //接口参数
        map1.add("cid", "C_JYYJT");
        map1.add("fydm", caseSearchDto.getFydm());
        map1.add("slfy", caseSearchDto.getFydm());
        map1.add("nd", caseSearchDto.getNd());
        map1.add("dz", caseSearchDto.getDz());
        map1.add("xh", caseSearchDto.getXh());
        map1.add("zjhm", caseSearchDto.getSfzh());
        map1.add("postion", "1");
        map1.add("pagerows", "100");
        //头部类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //构造实体对象
        HttpEntity<MultiValueMap<String, Object>> param = new HttpEntity<>(map1, headers);
        //发起请求,服务地址，请求参数，返回消息体的数据类型
        ResponseEntity<String> response = restTemplate.postForEntity(url, param, String.class);
        //body
        String body = response.getBody();
        JSONObject res = JSONObject.parseObject(body);
        if (!res.get("code").equals("0")) {
            throw new DataException("暂无信息");
        }
        List<CaseVo> caseVos = new ArrayList<>();
        JSONArray data = (JSONArray) res.get("data");
        System.out.println(data);
        for (int j = 0; j < data.size(); j++) {
            com.alibaba.fastjson.JSONObject aj = JSON.parseObject(JSON.toJSONString(data.get(j)));

            caseVos.add(CaseVo.builder()
                    .ah(aj.getString("ah"))
                    .type(aj.getString("dz"))
                    .status(aj.getString("zt"))
                    .ay(aj.getString("ayms"))
                    .larq(aj.getString("larq"))
                    .jarq(aj.getString("jarq"))
                    .cbbm(aj.getString("cbbmmc"))
                    .cbr(aj.getString("cbrrmc"))
                    .sycx(aj.getString("sycx"))
                    .yg(getYgName(aj.getString("dsrc")))
                    .bg(getBgName(aj.getString("dsrc")))
                    .build());
        }
        return CaseListVo.builder().caseVos(caseVos).build();
    }

    /**
     * 获取原告名称
     *
     * @param dsr 当事人信息
     * @return
     */
    private String getYgName(String dsr) {
        AssertUtil.assertNull(dsr, "第三方当事人信息为空");
        String[] split = dsr.split(";");
        String yg = "-";
        try {
            if (StringUtils.isNotBlank(split[0])) {
                String[] ygs = split[0].split(":");
                yg = ygs[1];
            }
        } catch (Exception e) {
            if (split.length > 1 && StringUtils.isNotBlank(split[1])) {
                yg = split[0];

            }
        }
        return yg;
    }


    /**
     * 获取原告名称
     *
     * @param dsr 当事人信息
     * @return
     */
    private String getBgName(String dsr) {
        AssertUtil.assertNull(dsr, "第三方当事人信息为空");
        String[] split = dsr.split(";");
        String bg = "-";
        try {
            if (StringUtils.isNotBlank(split[1])) {
                String[] bgs = split[1].split(":");
                bg = bgs[1];
            }
        } catch (Exception e) {
            if (split.length > 1 && StringUtils.isNotBlank(split[1])) {
                bg = split[1];
            }
        }
        return bg;
    }


    @Override
    public List<CaseProfileInfoVo> partySearchCaseList(CaseSearchInfoDto caseSearchInfoDto) {
        List<CaseProfileInfoVo> caseProfileInfoVos = new ArrayList<>();
        try {
            String idCard = caseSearchInfoDto.getIdCard();
            AssertUtil.assertNull(idCard,"身份证号不能为空");
            InputStream dsrInfo = MockUtil.getFileIo("DsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> mockDsrDetailList = mockDsrInfo.getData().stream().filter(item -> item.getZjh().equals(idCard)).collect(Collectors.toList());
            AssertUtil.assertNull(mockDsrDetailList,"身份证号暂无案件信息");
            mockDsrDetailList.stream().forEach(item->{
                CaseInfoVo caseInfoVo = this.caseInfoSearch(item.getLsh());
                CaseDetailInfoVo detailInfoVo = caseInfoVo.getCaseDetailInfoVo();
                CaseProfileInfoVo caseProfileInfoVo = CaseProfileInfoVo.builder()
                        .lsh(item.getLsh())
                        .dz(detailInfoVo.getDz())
                        .ah(detailInfoVo.getAh())
                        .larq(detailInfoVo.getLarq())
                        .jarq(detailInfoVo.getJarq())
                        .cbbm(detailInfoVo.getCbbm())
                        .status(detailInfoVo.getStatus())
                        .ay(detailInfoVo.getAy())
                        .cbr(detailInfoVo.getCbr())
                        .sycx(detailInfoVo.getSycx()).build();
                List<String> yg = detailInfoVo.getYg().stream().map(item1 -> {
                    return item1.getName();
                }).collect(Collectors.toList());
                List<String> bg = detailInfoVo.getBg().stream().map(item1 -> {
                    return item1.getName();
                }).collect(Collectors.toList());
                caseProfileInfoVo.setYg(yg);
                caseProfileInfoVo.setBg(bg);
                caseProfileInfoVos.add(caseProfileInfoVo);
            });
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
        return caseProfileInfoVos;
    }

    @Override
    public CaseInfoVo partySearchCaseInfo(CaseSearchInfoDto caseSearchInfoDto) {
        try {
            InputStream caseInfo = MockUtil.getFileIo("CaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            String ah = caseSearchInfoDto.getAh();
            AssertUtil.assertNull(ah, "案号信息不能为空");
            List<String> collect = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            String lsh = collect.get(0);
            String idCard = caseSearchInfoDto.getIdCard();
            AssertUtil.assertNull(idCard, "身份证号不能为空");
            AssertUtil.assertNull(lsh, "流水号信息错误");
            InputStream dsrInfo = MockUtil.getFileIo("DsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> data = mockDsrInfo.getData();
            AssertUtil.assertNull(data, "当事人信息不存在");
            List<MockDsrDetail> collect1 = data.stream().filter(item -> item.getZjh().equals(idCard)).collect(Collectors.toList());
            if (ObjectUtils.isEmpty(collect1)) {
                throw new DataException("证件信息不存在");
            } else {
                // 校验流水号是否匹配
                boolean contains = collect1.stream().map(item -> {
                    return item.getLsh();
                }).collect(Collectors.toList()).contains(lsh);
                if (!contains) {
                    throw new DataException("证件信息与案号不匹配");
                }
            }
            CaseInfoVo caseInfoVo = this.caseInfoSearch(lsh);
            return caseInfoVo;
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public LawyerCaseInfoVo lawyerCheck(CaseSearchInfoDto caseSearchInfoDto) {
        LawyerCaseInfoVo lawyerCaseInfoVo = new LawyerCaseInfoVo();
        try {
            String lawyerCard = caseSearchInfoDto.getLawyerCard();
            AssertUtil.assertNull(lawyerCard, "律师资格证号不能为空");
            String ah = caseSearchInfoDto.getAh();
            AssertUtil.assertNull(ah, "案号不能为空");
            InputStream dlrInfo = MockUtil.getFileIo("DlrInfo.json");
            MockDlrInfo mockDlrInfo = objectMapper.readValue(dlrInfo, MockDlrInfo.class);
            List<MockDlrDetail> data = mockDlrInfo.getData();
            AssertUtil.assertNull(data, "律师信息有误");
            List<String> collect = data.stream().filter(item -> item.getZjh().equals(lawyerCard)).map(item->{return item.getLsh();}).collect(Collectors.toList());
            AssertUtil.assertNull(collect, "律师信息不存在");
            InputStream caseInfo = MockUtil.getFileIo("CaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            List<MockCaseDetail> collect1 = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).collect(Collectors.toList());
            AssertUtil.assertNull(collect1,"证件号 暂无信息");
            String lsh = collect1.get(0).getLsh();
            if(collect.contains(lsh)){
                lawyerCaseInfoVo.setDetail(true);
                CaseInfoVo caseInfoVo = this.caseInfoSearch(lsh);
                lawyerCaseInfoVo.setCaseInfoVo(caseInfoVo);
            } else {
                lawyerCaseInfoVo.setDetail(false);
                CaseInfoVo caseInfoVo = this.caseInfoSearch(lsh);
                CaseDetailInfoVo detailInfoVo = caseInfoVo.getCaseDetailInfoVo();
                CaseProfileInfoVo caseProfileInfoVo = CaseProfileInfoVo.builder()
                        .dz(detailInfoVo.getDz())
                        .ah(detailInfoVo.getAh())
                        .larq(detailInfoVo.getLarq())
                        .jarq(detailInfoVo.getJarq())
                        .cbbm(detailInfoVo.getCbbm())
                        .status(detailInfoVo.getStatus())
                        .ay(detailInfoVo.getAy())
                        .cbr(detailInfoVo.getCbr())
                        .sycx(detailInfoVo.getSycx()).build();
                List<String> yg = detailInfoVo.getYg().stream().map(item -> {
                    return item.getName();
                }).collect(Collectors.toList());
                List<String> bg = detailInfoVo.getBg().stream().map(item -> {
                    return item.getName();
                }).collect(Collectors.toList());
                caseProfileInfoVo.setYg(yg);
                caseProfileInfoVo.setBg(bg);
                lawyerCaseInfoVo.setCaseProfileInfoVo(caseProfileInfoVo);
            }
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return lawyerCaseInfoVo;
    }


    @Override
    public CaseInfoVo lawyerCaseInfo(CaseSearchInfoDto caseSearchInfoDto) {
        String ah = caseSearchInfoDto.getAh();
        AssertUtil.assertNull(ah, "案号信息不能为空");
        String zjh = caseSearchInfoDto.getZjh();
        AssertUtil.assertNull(zjh, "当事人证件信息不能为空");
        CaseInfoVo caseInfoVo = new CaseInfoVo();
        try {
            InputStream caseInfo = MockUtil.getFileIo("CaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            List<String> collect = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            String lsh = collect.get(0);
            InputStream dsrInfo = MockUtil.getFileIo("DsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> data = mockDsrInfo.getData();
            AssertUtil.assertNull(data, "当事人信息不存在");
            List<String> collect1 = data.stream().filter(item -> item.getZjh().equals(zjh)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            if (!collect1.contains(lsh)) {
                throw new DataException("当事人身份信息不匹配");
            }
            caseInfoVo = this.caseInfoSearch(lsh);
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return caseInfoVo;
    }

    @Override
    public CaseInfoVo caseInfoSearch(String lsh) {
        CaseInfoVo caseInfoVo = new CaseInfoVo();
        try {
            // 案件列表
            InputStream caseInfo = MockUtil.getFileIo("CaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            List<MockCaseDetail> mockCaseDetailList = mockCaseInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            if (mockCaseDetailList.size() > 1) {
                throw new DataException("流水号：" + lsh + "案件信息错误，请检查mock数据");
            }
            // 案件当事人
            InputStream dsrInfo = MockUtil.getFileIo("DsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> mockDsrDetailList = mockDsrInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            // 代理人
            InputStream dlrInfo = MockUtil.getFileIo("DlrInfo.json");
            MockDlrInfo mockDlrInfo = objectMapper.readValue(dlrInfo, MockDlrInfo.class);
            List<MockDlrDetail> mockDlrDetailList = mockDlrInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            // 处理原告被告及代理人之间的对应关系
            CaseDetailInfoVo caseDetailInfoVo = personInfo(mockDsrDetailList, mockDlrDetailList, mockCaseDetailList.get(0));
            caseInfoVo.setCaseDetailInfoVo(caseDetailInfoVo);
            // 材料信息
            InputStream mockFile = MockUtil.getFileIo("File.json");
            MockFileInfo mockFileInfo = objectMapper.readValue(mockFile, MockFileInfo.class);
            List<MockFileDetail> mockFileInfoData = mockFileInfo.getData();
            if (mockFileInfoData.size() > 0) {
                List<String> qsz = mockFileInfoData.stream().filter(item -> item.getType().equals("1")).map(item -> {
                    return item.getPath();
                }).collect(Collectors.toList());
                List<String> qtcl = mockFileInfoData.stream().filter(item -> item.getType().equals("2")).map(item -> {
                    return item.getPath();
                }).collect(Collectors.toList());
                CaseFileInfoVo build = CaseFileInfoVo.builder().plaintiff(qsz).otherFile(qtcl).build();
                caseInfoVo.setCaseFileInfoVo(build);
            }
        } catch (IOException e) {
            throw new DataException(e.getMessage());
        }
        System.out.print(caseInfoVo);
        return caseInfoVo;
    }

    private CaseDetailInfoVo personInfo(List<MockDsrDetail> mockDsrDetailList, List<MockDlrDetail> mockDlrDetailList, MockCaseDetail detail) {
        AssertUtil.assertNull(mockDsrDetailList, "当事人信息缺失");
        List<CaseDsrInfoVo> caseDsrInfoVos = mockDsrDetailList.stream().map(item -> {
            CaseDsrInfoVo caseDsrInfoVo = CaseDsrInfoVo.builder()
                    .type(item.getType())
                    .dw(item.getDw())
                    .name(item.getName())
                    .sex(item.getSex())
                    .birthday(item.getBirthday())
                    .mz(item.getMz())
                    .address(item.getAddress())
                    .zjh(item.getZjh())
                    .sjh(item.getSjh()).build();
            List<CaseAgentInfoVo> caseAgentInfoVos = mockDlrDetailList.stream().filter(mockDlr -> mockDlr.getDsrxh().equals(item.getXh())).map(dlr -> {
                return CaseAgentInfoVo.builder()
                        .name(dlr.getName())
                        .company(dlr.getCompany())
                        .zjh(dlr.getZjh())
                        .phone(dlr.getZjh()).build();
            }).collect(Collectors.toList());
            caseDsrInfoVo.setDlr(caseAgentInfoVos);
            CaseDsrInfoVo caseDsrInfoVo1 = caseDsrInfoVo;
            return caseDsrInfoVo1;
        }).collect(Collectors.toList());
        // 原告
        List<CaseDsrInfoVo> ygList = caseDsrInfoVos.stream().filter(yg -> yg.getDw().equals("1")).collect(Collectors.toList());
        // 被告
        List<CaseDsrInfoVo> bgList = caseDsrInfoVos.stream().filter(bg -> bg.getDw().equals("2")).collect(Collectors.toList());
        // 组装
        CaseDetailInfoVo detailInfoVo = CaseDetailInfoVo.builder()
                .dz(detail.getDz())
                .ah(detail.getAh())
                .status(detail.getStatus())
                .ay(detail.getAy())
                .sarq(detail.getSarq())
                .larq(detail.getLarq())
                .cbbm(detail.getCbbm())
                .cbr(detail.getCbr())
                .spz(detail.getSpz())
                .ajly(detail.getAjly())
                .bdje(detail.getBdje())
                .slf(detail.getSlf())
                .isPay(detail.getSfjn())
                .sycx(detail.getSycx())
                .ktrq(detail.getKtrq())
                .ktdd(detail.getKtdd())
                .jarq(detail.getJarq())
                .jafs(detail.getJafs())
                .yg(ygList)
                .bg(bgList).build();
        return detailInfoVo;
    }
}
