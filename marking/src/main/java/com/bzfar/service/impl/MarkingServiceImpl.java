package com.bzfar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bzfar.dto.CaseSearchInfoDto;
import com.bzfar.dto.FileDto;
import com.bzfar.dto.MuluDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.MarkingService;
import com.bzfar.util.AssertUtil;
import com.bzfar.utils.*;
import com.bzfar.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 案件查询
 */
@Service
@Slf4j
public class MarkingServiceImpl implements MarkingService {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public List<CaseProfileInfoVo> partySearchCaseList(CaseSearchInfoDto caseSearchInfoDto) {
        List<CaseProfileInfoVo> caseProfileInfoVos = new ArrayList<>();
        try {
            String idCard = caseSearchInfoDto.getIdCard();
            AssertUtil.assertNull(idCard, "身份证号不能为空");
            InputStream dsrInfo = MarkingMockUtil.getFileIo("markingDsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> mockDsrDetailList = mockDsrInfo.getData().stream().filter(item -> item.getZjh().equals(idCard)).collect(Collectors.toList());
            AssertUtil.assertNull(mockDsrDetailList, "身份证号暂无案件信息");
            mockDsrDetailList.stream().forEach(item -> {
                CaseDetailInfoVo detailInfoVo = this.caseInfoSearch(item.getLsh());
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
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return caseProfileInfoVos;
    }


    @Override
    public LawyerCaseInfoVo lawyerCheck(CaseSearchInfoDto caseSearchInfoDto) {
        LawyerCaseInfoVo lawyerCaseInfoVo = new LawyerCaseInfoVo();
        try {
            String lawyerCard = caseSearchInfoDto.getLawyerCard();
            AssertUtil.assertNull(lawyerCard, "律师资格证号不能为空");
            String ah = caseSearchInfoDto.getAh();
            AssertUtil.assertNull(ah, "案号不能为空");
            InputStream dlrInfo = MarkingMockUtil.getFileIo("markingDlrInfo.json");
            MockDlrInfo mockDlrInfo = objectMapper.readValue(dlrInfo, MockDlrInfo.class);
            List<MockDlrDetail> data = mockDlrInfo.getData();
            AssertUtil.assertNull(data, "律师信息有误");
            List<String> collect = data.stream().filter(item -> item.getZjh().equals(lawyerCard)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            AssertUtil.assertNull(collect, "律师信息不存在");
            InputStream caseInfo = MarkingMockUtil.getFileIo("markingCaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            List<MockCaseDetail> collect1 = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).collect(Collectors.toList());
            AssertUtil.assertNull(collect1, "证件号 暂无信息");
            String lsh = collect1.get(0).getLsh();
            if (collect.contains(lsh)) {
                lawyerCaseInfoVo.setDetail(true);
            } else {
                lawyerCaseInfoVo.setDetail(false);
                CaseDetailInfoVo detailInfoVo = this.caseInfoSearch(lsh);
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
    public Boolean lawyerCaseInfo(CaseSearchInfoDto caseSearchInfoDto) {
        String ah = caseSearchInfoDto.getAh();
        AssertUtil.assertNull(ah, "案号信息不能为空");
        String zjh = caseSearchInfoDto.getZjh();
        AssertUtil.assertNull(zjh, "当事人证件信息不能为空");
        try {
            InputStream caseInfo = MarkingMockUtil.getFileIo("markingCaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            List<String> collect = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            String lsh = collect.get(0);
            InputStream dsrInfo = MarkingMockUtil.getFileIo("markingDsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> data = mockDsrInfo.getData();
            AssertUtil.assertNull(data, "当事人信息不存在");
            List<String> collect1 = data.stream().filter(item -> item.getZjh().equals(zjh)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            if (!collect1.contains(lsh)) {
                throw new DataException("当事人身份信息不匹配");
            }
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return false;
    }

    @Override
    public CaseDetailInfoVo caseInfoSearch(String lsh) {
        CaseDetailInfoVo caseDetailInfoVo = new CaseDetailInfoVo();
        try {
            // 案件列表
            InputStream caseInfo = MarkingMockUtil.getFileIo("markingCaseInfo.json");
            MockCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockCaseInfo.class);
            List<MockCaseDetail> mockCaseDetailList = mockCaseInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            if (mockCaseDetailList.size() > 1) {
                throw new DataException("流水号：" + lsh + "案件信息错误，请检查mock数据");
            }
            // 案件当事人
            InputStream dsrInfo = MarkingMockUtil.getFileIo("markingDsrInfo.json");
            MockDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockDsrInfo.class);
            List<MockDsrDetail> mockDsrDetailList = mockDsrInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            // 代理人
            InputStream dlrInfo = MarkingMockUtil.getFileIo("markingDlrInfo.json");
            MockDlrInfo mockDlrInfo = objectMapper.readValue(dlrInfo, MockDlrInfo.class);
            List<MockDlrDetail> mockDlrDetailList = mockDlrInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            // 处理原告被告及代理人之间的对应关系
            caseDetailInfoVo = personInfo(mockDsrDetailList, mockDlrDetailList, mockCaseDetailList.get(0));
        } catch (IOException e) {
            throw new DataException(e.getMessage());
        }
        return caseDetailInfoVo;
    }

    private CaseDetailInfoVo personInfo(List<MockDsrDetail> mockDsrDetailList, List<MockDlrDetail> mockDlrDetailList, MockCaseDetail detail) {
        AssertUtil.assertNull(mockDsrDetailList, "当事人信息缺失");
        List<CaseDsrInfoVo> caseDsrInfoVos = mockDsrDetailList.stream().map(item -> {
            CaseDsrInfoVo caseDsrInfoVo = CaseDsrInfoVo.builder()
                    .type(item.getType())
                    .dw(item.getDw())
                    .name(item.getName())
                    .zjh(item.getZjh()).build();
            List<CaseAgentInfoVo> caseAgentInfoVos = mockDlrDetailList.stream().filter(mockDlr -> mockDlr.getDsrxh().equals(item.getXh())).map(dlr -> {
                return CaseAgentInfoVo.builder()
                        .name(dlr.getName())
                        .zjh(dlr.getZjh()).build();
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
                .larq(detail.getLarq())
                .cbbm(detail.getCbbm())
                .cbr(detail.getCbr())
                .sycx(detail.getSycx())
                .jarq(detail.getJarq())
                .yg(ygList)
                .bg(bgList).build();
        return detailInfoVo;
    }


    @Override
    public MuluListsVo getMulu(MuluDto muluDto) {
        String result = Base64Util.decode("PD94bWwgdmVyc2lvbj0nMS4wJyBlbmNvZGluZz0nVVRGLTgnPz48UmVzcG9uc2U+PFJlc3VsdD48Q29kZT5NQT09PC9Db2RlPjwvUmVzdWx0PjxEQV9TU0pDWFhfTElTVD48REFfU1NKQ1hYPjxESERNPk5ESXdOREExV2pBd01EQTBPREk0PC9ESERNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48Q05ZUz5NelU9PC9DTllTPjwvREFfU1NKQ1hYPjxEQV9TU0pDWFg+PERIRE0+TkRJd05EQTFXakF3TURBME9ESTQ8L0RIRE0+PEpaTEI+NVltdjVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxDTllTPk9RPT08L0NOWVM+PC9EQV9TU0pDWFg+PC9EQV9TU0pDWFhfTElTVD48REFfU1NKQ0JNX0xJU1Q+PERBX1NTSkNCTT48SlpMQj41WW12NVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFhIPk1RPT08L1hIPjxDTEJUPjVvbS81WXFlNUxxNjU1cUU1YTZoNXArbDVvcWw1WkdLPC9DTEJUPjxQMT5NUT09PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVZbXY1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TWc9PTwvWEg+PENMQlQ+NVpDSTZLNnU1YnF0NksrRTZLNnU1cUdJNUx1MjU2eVU1YjJWPC9DTEJUPjxQMT5NZz09PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVZbXY1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TXc9PTwvWEg+PENMQlQ+NXJPVjViNkw1cGFINUxtbTVZNmY1cGFIPC9DTEJUPjxQMT5Ndz09PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TVE9PTwvWEg+PENMQlQ+NkxXMzZLK0o1NHEyPC9DTEJUPjxQMT5NUT09PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TWc9PTwvWEg+PENMQlQ+NTZ1TDVxR0k1YTZoNW9tNTZLR288L0NMQlQ+PFAxPk13PT08L1AxPjwvREFfU1NKQ0JNPjxEQV9TU0pDQk0+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxYSD5Ndz09PC9YSD48Q0xCVD41WStYNTVDRzVxR0k1THUyNllDYTU1K2w1TG1tNDRDQjVMaSs2SytCNllDYTU1K2w8L0NMQlQ+PFAxPk5BPT08L1AxPjwvREFfU1NKQ0JNPjxEQV9TU0pDQk0+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxYSD5OQT09PC9YSD48Q0xCVD41N3kwNTdxejZLK0o2SzY4NkxTNTVvT0Y1WWExPC9DTEJUPjxQMT5OUT09PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TlE9PTwvWEg+PENMQlQ+NWJxVTZLK0o2WUNhNTUrbDVMbW00NENCNUxpKzZLK0I2WUNhNTUrbDVMbW08L0NMQlQ+PFAxPk5nPT08L1AxPjwvREFfU1NKQ0JNPjxEQV9TU0pDQk0+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxYSD5OZz09PC9YSD48Q0xCVD41YjJUNUxxTDVMcTY2THFyNUx1OTZLK0I1cGlPNkxXRTVwYVo8L0NMQlQ+PFAxPk9BPT08L1AxPjwvREFfU1NKQ0JNPjxEQV9TU0pDQk0+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxYSD5Odz09PC9YSD48Q0xCVD41YjJUNUxxTDVMcTY1Wnl3NVoyQTU2R3U2SzZrNUxtbTwvQ0xCVD48UDE+T1E9PTwvUDE+PC9EQV9TU0pDQk0+PERBX1NTSkNCTT48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFhIPk9BPT08L1hIPjxDTEJUPjVZNmY0NENCNktLcjVaR0s1TGkrNksrQjVwMlE1cGFaPC9DTEJUPjxQMT5NVEE9PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+T1E9PTwvWEg+PENMQlQ+NksraTZaZXU0NENCNkxDRDVwK2w1WStXNksrQjVwMlE1cGFaPC9DTEJUPjxQMT5NakE9PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TVRBPTwvWEg+PENMQlQ+NWJ5QTVicXQ2WUNhNTUrbDQ0Q0I1THlnNTZXbzQ0Q0I1WVdzNVpHSzwvQ0xCVD48UDE+TWpJPTwvUDE+PC9EQV9TU0pDQk0+PERBX1NTSkNCTT48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFhIPk1URT08L1hIPjxDTEJUPjVicXQ1YTZoNTZ5VTViMlY8L0NMQlQ+PFAxPk1qTT08L1AxPjwvREFfU1NKQ0JNPjxEQV9TU0pDQk0+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxYSD5NVEk9PC9YSD48Q0xCVD41WWlrNVlhejVMbW00NENCNkxDRDZLZWo1TG1tNDRDQjZLT0I1YTZhNUxtbTVxMmo1cHlzPC9DTEJUPjxQMT5Nams9PC9QMT48L0RBX1NTSkNCTT48REFfU1NKQ0JNPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48WEg+TVRNPTwvWEg+PENMQlQ+NllDQjZMNis1WnVlNksrQjwvQ0xCVD48UDE+TXpBPTwvUDE+PC9EQV9TU0pDQk0+PC9EQV9TU0pDQk1fTElTVD48REFfU1NKQ1lYX0xJU1Q+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41YkNCNloyaTwvUEdUWVBFPjxZRU1BPk1RPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpFdU5UQXpYMmx0WVdkbFgxcGZNVjh3TURBd01TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjU1dXU1YjJWPC9QR1RZUEU+PFlFTUE+TVE9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakV1TlRFNVgybHRZV2RsWDFwZk1WOHdNREF3TWk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NUT09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qRXVOVFV3WDJsdFlXZGxYMXBmTVY4d01EQXdNeTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41YkNCNWJxVjwvUEdUWVBFPjxZRU1BPk1RPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU5qRXpYMmx0WVdkbFgxcGZNVjh3TURBek9DNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TWc9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakV1TlRZMlgybHRZV2RsWDFwZk1WOHdNREF3TkM1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5Ndz09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qRXVOVGd4WDJsdFlXZGxYMXBmTVY4d01EQXdOUzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk5BPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpFdU5UazNYMmx0WVdkbFgxcGZNVjh3TURBd05pNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TlE9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakV1TmpFelgybHRZV2RsWDFwZk1WOHdNREF3Tnk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5OZz09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qRXVOakk0WDJsdFlXZGxYMXBmTVY4d01EQXdPQzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk53PT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpFdU9UVTJYMmx0WVdkbFgxcGZNVjh3TURBd09TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+T0E9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TURFNVgybHRZV2RsWDFwZk1WOHdNREF4TUM1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5PUT09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVNRE0wWDJsdFlXZGxYMXBmTVY4d01EQXhNUzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1UQT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU1EVXdYMmx0WVdkbFgxcGZNVjh3TURBeE1pNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TVRFPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TURneFgybHRZV2RsWDFwZk1WOHdNREF4TXk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NVEk9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVNRGszWDJsdFlXZGxYMXBmTVY4d01EQXhOQzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1UTT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU1USTRYMmx0WVdkbFgxcGZNVjh3TURBeE5TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TVRRPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TVRRMFgybHRZV2RsWDFwZk1WOHdNREF4Tmk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NVFU9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVNVFU1WDJsdFlXZGxYMXBmTVY4d01EQXhOeTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1UWT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU1UYzFYMmx0WVdkbFgxcGZNVjh3TURBeE9DNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TVRjPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TVRreFgybHRZV2RsWDFwZk1WOHdNREF4T1M1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NVGc9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVNakEyWDJsdFlXZGxYMXBmTVY4d01EQXlNQzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1Uaz08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU1qSXlYMmx0WVdkbFgxcGZNVjh3TURBeU1TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TWpBPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TWpNNFgybHRZV2RsWDFwZk1WOHdNREF5TWk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NakU9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVNalV6WDJsdFlXZGxYMXBmTVY4d01EQXlNeTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1qST08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU1qZzBYMmx0WVdkbFgxcGZNVjh3TURBeU5DNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TWpNPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TXpBd1gybHRZV2RsWDFwZk1WOHdNREF5TlM1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NalE9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVNelEzWDJsdFlXZGxYMXBmTVY4d01EQXlOaTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1qVT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU16WXpYMmx0WVdkbFgxcGZNVjh3TURBeU55NTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TWpZPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TXpjNFgybHRZV2RsWDFwZk1WOHdNREF5T0M1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NamM9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVOREE1WDJsdFlXZGxYMXBmTVY4d01EQXlPUzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1qZz08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU5ESTFYMmx0WVdkbFgxcGZNVjh3TURBek1DNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TWprPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TkRReFgybHRZV2RsWDFwZk1WOHdNREF6TVM1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5Namt0TVE9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TkRVMlgybHRZV2RsWDFwZk1WOHdNREF6TWk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NekE9PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVORGc0WDJsdFlXZGxYMXBmTVY4d01EQXpNeTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41cTJqNVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk16RT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU5UQXpYMmx0WVdkbFgxcGZNVjh3TURBek5DNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TXpFdE1RPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDFwZk1TOHlNREl3TURFeE55NHdPVFUxTWpJdU5URTVYMmx0WVdkbFgxcGZNVjh3TURBek5TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVxMmo1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TXpJPTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMXBmTVM4eU1ESXdNREV4Tnk0d09UVTFNakl1TlRNMFgybHRZV2RsWDFwZk1WOHdNREF6Tmk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NXEyajVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5Nek09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwxcGZNUzh5TURJd01ERXhOeTR3T1RVMU1qSXVOVGszWDJsdFlXZGxYMXBmTVY4d01EQXpOeTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41WW12NVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41YkNCNloyaTwvUEdUWVBFPjxZRU1BPk1RPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDBaZk1TOHlNREl3TURFeE55NHdPVFUxTWpndU1qSXlYMmx0WVdkbFgwWmZNVjh3TURBd01TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVZbXY1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjU1dXU1YjJWPC9QR1RZUEU+PFlFTUE+TVE9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMFpmTVM4eU1ESXdNREV4Tnk0d09UVTFNamd1TWpNNFgybHRZV2RsWDBaZk1WOHdNREF3TWk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NVltdjVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NUT09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwwWmZNUzh5TURJd01ERXhOeTR3T1RVMU1qZ3VNalV6WDJsdFlXZGxYMFpmTVY4d01EQXdNeTUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41WW12NVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41YkNCNWJxVjwvUEdUWVBFPjxZRU1BPk1RPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDBaZk1TOHlNREl3TURFeE55NHdPVFUxTWpndU5ESTFYMmx0WVdkbFgwWmZNVjh3TURBeE1pNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVZbXY1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TVMweDwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMFpmTVM4eU1ESXdNREV4Tnk0d09UVTFNamd1TWpnMFgybHRZV2RsWDBaZk1WOHdNREF3TkM1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NVltdjVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NZz09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwwWmZNUzh5TURJd01ERXhOeTR3T1RVMU1qZ3VNekF3WDJsdFlXZGxYMFpmTVY4d01EQXdOUzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41WW12NVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk1pMHg8L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDBaZk1TOHlNREl3TURFeE55NHdPVFUxTWpndU16RTJYMmx0WVdkbFgwWmZNVjh3TURBd05pNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVZbXY1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TXc9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMFpmTVM4eU1ESXdNREV4Tnk0d09UVTFNamd1TXpNeFgybHRZV2RsWDBaZk1WOHdNREF3Tnk1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NVltdjVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5NeTB4PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwwWmZNUzh5TURJd01ERXhOeTR3T1RVMU1qZ3VNell6WDJsdFlXZGxYMFpmTVY4d01EQXdPQzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PERBX1NTSkNZWD48SlpMQj41WW12NVkyMzwvSlpMQj48Q0g+TVE9PTwvQ0g+PFBHVFlQRT41cTJqNXBhSDwvUEdUWVBFPjxZRU1BPk5BPT08L1lFTUE+PEZUUFNFUlZFUj5ablJ3PC9GVFBTRVJWRVI+PE9TU05BTUU+PC9PU1NOQU1FPjxGSUxFTkFNRT5iblZzYkY4eU1ERTRMekl4TURFdk5ESXdOREExV2pBd01EQTBPREk0TDBaZk1TOHlNREl3TURFeE55NHdPVFUxTWpndU16YzRYMmx0WVdkbFgwWmZNVjh3TURBd09TNTBhV1k9PC9GSUxFTkFNRT48T1NTRklMRU5BTUU+PC9PU1NGSUxFTkFNRT48L0RBX1NTSkNZWD48REFfU1NKQ1lYPjxKWkxCPjVZbXY1WTIzPC9KWkxCPjxDSD5NUT09PC9DSD48UEdUWVBFPjVxMmo1cGFIPC9QR1RZUEU+PFlFTUE+TlE9PTwvWUVNQT48RlRQU0VSVkVSPlpuUnc8L0ZUUFNFUlZFUj48T1NTTkFNRT48L09TU05BTUU+PEZJTEVOQU1FPmJuVnNiRjh5TURFNEx6SXhNREV2TkRJd05EQTFXakF3TURBME9ESTRMMFpmTVM4eU1ESXdNREV4Tnk0d09UVTFNamd1TXprMFgybHRZV2RsWDBaZk1WOHdNREF4TUM1MGFXWT08L0ZJTEVOQU1FPjxPU1NGSUxFTkFNRT48L09TU0ZJTEVOQU1FPjwvREFfU1NKQ1lYPjxEQV9TU0pDWVg+PEpaTEI+NVltdjVZMjM8L0paTEI+PENIPk1RPT08L0NIPjxQR1RZUEU+NXEyajVwYUg8L1BHVFlQRT48WUVNQT5OZz09PC9ZRU1BPjxGVFBTRVJWRVI+Wm5SdzwvRlRQU0VSVkVSPjxPU1NOQU1FPjwvT1NTTkFNRT48RklMRU5BTUU+Ym5Wc2JGOHlNREU0THpJeE1ERXZOREl3TkRBMVdqQXdNREEwT0RJNEwwWmZNUzh5TURJd01ERXhOeTR3T1RVMU1qZ3VOREE1WDJsdFlXZGxYMFpmTVY4d01EQXhNUzUwYVdZPTwvRklMRU5BTUU+PE9TU0ZJTEVOQU1FPjwvT1NTRklMRU5BTUU+PC9EQV9TU0pDWVg+PC9EQV9TU0pDWVhfTElTVD48REFfRlRQU0VSVkVSX0xJU1Q+PERBX0ZUUFNFUlZFUj48RlRQTkFNRT5aR0ZuYWc9PTwvRlRQTkFNRT48SVBBRERSPk1UUTBMakUyTGpFdU1qZz08L0lQQUREUj48RlRQUE9SVD5NakU9PC9GVFBQT1JUPjxVU1JOQU1FPlpHRm5hZz09PC9VU1JOQU1FPjxQQVNTV0Q+TVRJek5EVTI8L1BBU1NXRD48L0RBX0ZUUFNFUlZFUj48REFfRlRQU0VSVkVSPjxGVFBOQU1FPlpuUnc8L0ZUUE5BTUU+PElQQUREUj5NVFEwTGpFMkxqRXVNamc9PC9JUEFERFI+PEZUUFBPUlQ+TWpFPTwvRlRQUE9SVD48VVNSTkFNRT5aV052ZFhKMDwvVVNSTkFNRT48UEFTU1dEPlpXTnZkWEowYzJFPTwvUEFTU1dEPjwvREFfRlRQU0VSVkVSPjwvREFfRlRQU0VSVkVSX0xJU1Q+PC9SZXNwb25zZT4=");
        JSONObject res = JSON.parseObject(AnalysisUtil.analysisJson(XmlUtil.xmlToJson(result).toString()));
        com.alibaba.fastjson.JSONObject codedata = JSON.parseObject(JSON.toJSONString(res.get("Result")));
        String code = codedata.getString("Code");
        if (code.equals("0")) {
            JSONObject DA_SSJCBM_LIST = JSON.parseObject(JSON.toJSONString(res.get("DA_SSJCBM_LIST")));
            JSONObject DA_SSJCYX_LIST = JSON.parseObject(JSON.toJSONString(res.get("DA_SSJCYX_LIST")));
            JSONArray DA_SSJCBM = (JSONArray) DA_SSJCBM_LIST.getJSONArray("DA_SSJCBM");
            JSONArray DA_SSJCYX = (JSONArray) DA_SSJCYX_LIST.getJSONArray("DA_SSJCYX");
            ArrayList list = new ArrayList();
            for (int i = 0; i < DA_SSJCBM.size(); i++) {
                com.alibaba.fastjson.JSONObject SSJCBM = JSON.parseObject(JSON.toJSONString(DA_SSJCBM.get(i)));
                ArrayList arr = new ArrayList();
                String CLBT = SSJCBM.getString("CLBT");
                if (CLBT.contains("起诉状") || CLBT.contains("庭审笔录") || CLBT.contains("送达回证") || CLBT.contains("邮寄回执") || CLBT.contains("判决书")
                        || CLBT.contains("裁定书") || CLBT.contains("调解书") || CLBT.contains("法律文书") || CLBT.contains("宣判笔录")) {
                    SSJCBM.put("status", 1);
                } else {
                    SSJCBM.put("status", 0);
                }
                if (SSJCBM.getString("JZLB").contains("副卷") || SSJCBM.getString("JZLB").contains("公安卷")) {
                    continue;
                }
                Iterator<Object> it = DA_SSJCYX.iterator();
                while (it.hasNext()) {
//                for(int j = 0; j< DA_SSJCYX.size(); j++){
                    com.alibaba.fastjson.JSONObject SSJCYX = JSON.parseObject(JSON.toJSONString(it.next()));
                    if (CLBT.contains("封面") && SSJCYX.getString("PGTYPE").contains("封面")) {
                        MuluVo ob = MuluVo.builder()
                                .jzlb(SSJCYX.getString("JZLB"))
                                .ossfimename(SSJCYX.getString("OSSFILENAME"))
                                .ossname(SSJCYX.getString("OSSNAME"))
                                .pgtype(SSJCYX.getString("PGTYPE"))
                                .filename(SSJCYX.getString("FILENAME"))
                                .ftpserver(SSJCYX.getString("FTPSERVER"))
                                .build();
                        arr.add(ob);
//                        DA_SSJCYX.iterator();
                        it.remove();
                    }
                    if (CLBT.contains("目录") && SSJCYX.getString("PGTYPE").contains("目录")) {
                        MuluVo ob = MuluVo.builder()
                                .jzlb(SSJCYX.getString("JZLB"))
                                .ossfimename(SSJCYX.getString("OSSFILENAME"))
                                .ossname(SSJCYX.getString("OSSNAME"))
                                .pgtype(SSJCYX.getString("PGTYPE"))
                                .filename(SSJCYX.getString("FILENAME"))
                                .ftpserver(SSJCYX.getString("FTPSERVER"))
                                .build();
                        arr.add(ob);
//                        DA_SSJCYX.remove(j);
                        it.remove();
                    }
                    int yema = 0;
                    try {
                        yema = Integer.valueOf(SSJCYX.getString("YEMA"));
                    } catch (Exception e) {
                        yema = -1;
                    }

                    if (!SSJCBM.getString("P1").equals("0") && Integer.valueOf(SSJCBM.getString("P1")) > yema
                            && SSJCYX.getString("PGTYPE").equals("正文") && SSJCBM.getString("JZLB").equals(SSJCYX.getString("JZLB"))) {
                        MuluVo ob = MuluVo.builder()
                                .jzlb(SSJCYX.getString("JZLB"))
                                .ossfimename(SSJCYX.getString("OSSFILENAME"))
                                .ossname(SSJCYX.getString("OSSNAME"))
                                .pgtype(SSJCYX.getString("PGTYPE"))
                                .yema(SSJCYX.getString("YEMA"))
                                .filename(SSJCYX.getString("FILENAME"))
                                .ftpserver(SSJCYX.getString("FTPSERVER"))
                                .build();
                        arr.add(ob);
                        it.remove();
                    }

                    if (DA_SSJCBM.size() - 1 == i && Integer.valueOf(SSJCBM.getString("P1")) <= yema
                            && SSJCYX.getString("PGTYPE").equals("正文") && SSJCBM.getString("JZLB").equals(SSJCYX.getString("JZLB"))) {
                        MuluVo ob = MuluVo.builder()
                                .jzlb(SSJCYX.getString("JZLB"))
                                .ossfimename(SSJCYX.getString("OSSFILENAME"))
                                .ossname(SSJCYX.getString("OSSNAME"))
                                .pgtype(SSJCYX.getString("PGTYPE"))
                                .yema(SSJCYX.getString("YEMA"))
                                .filename(SSJCYX.getString("FILENAME"))
                                .ftpserver(SSJCYX.getString("FTPSERVER"))
                                .build();
                        arr.add(ob);
                        it.remove();
                    }
                }
                MuluListVo mulu = MuluListVo.builder()
                        .jzlb(SSJCBM.getString("JZLB"))
                        .clbt(SSJCBM.getString("CLBT"))
                        .p1(SSJCBM.getString("P1"))
                        .status(SSJCBM.getString("status"))
                        .xh(SSJCBM.getString("XH"))
                        .muluVo(arr)
                        .build();
                list.add(mulu);
            }
            MuluListsVo lists = MuluListsVo.builder()
                    .list(list)
                    .build();
            return lists;
        }

        return null;
    }

    @Override
    public MarkingFileVo getFile(FileDto fileDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("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");
        sb.append("/hQA1hnvRznmgQenak75pgGeOKX8KQARzjsabgkYz1oAT056dqXk8Y60wEyQD+me1IRnrxQAgz24p6nJNABnjvTqQDR1+tKvf8qAF9KOvQ0AKAc80mSD6UAJ2ppGCRk+tMBp9c9O9JnHHT8aYDhzgjmnrj7tIAPYZxSZG3r+VIYckc4qNu9MRl+JhKfCmrC3OJjZTeWcZ+bYcfrXklrBLbrDBMYyFjVDn5gMUpaLQ7cJe7NZINyPEZHyi/KyjIY/jVMrOWVnwSh4yev+QamzT7HqOLa2LUm24RysQj3DCnr0+vvVKZprS1Ml0rh0GQo4PPTPX2NU4R0fUwm3GxFHqLtcszPJ++ODiXJA9PfpV2ZIHkUeZKqYUEvwRxzSshU1N3K13IltK6RqGRyVGcZH41Qm1AyPwv3Ozn0qbPdGXM1bm7kf2uR0cMVxJwQfrmk2RSSKEDENwvPP41fK7XuRfmkoirYyOmVjzt5JU9q6L4X3n/ABci4tIsiMaU0jKe7eZGOv4mnKLi7GNVcqPXD79KOfXnFByCng+1ITkjOfzoGIDjjHFL16du9ADZeErnNY6Mc4B4ByeevvSOvD/EclcsGc7QVGegz/n/APVVOVWxnacEA4I+nbP+eazPYFhG9xuw2fQ9a2LQEj72RkdBVop7Ggu4KChwem0nHepsblDYAA7qP8/5NMzBc/e4PJwfehm+b5RnPvz/AJ61QupZiHQc4PT15rVtvUn6VJy19jSTGM9fYinEj+tM8p7ijg0dxjkUhC56A4wazvEcqQeG78ySLGpgdQzHAywwBn1JIA9yKBrVnl/2acWZadkAkfO7ys4z94gdelIloPsD7mZGQkwHpgdetDva61se06bun");
        String baseimage = sb.toString();
        String num = System.currentTimeMillis() + "_" + new Random().nextInt(1000000);
        String pName = num + ".jpg";
        String fileName = pName;
        Base64ToImg base64to = new Base64ToImg();
        try {
            String random = "";
            String[] doc = {"http://60.205.180.13:81/img/1626246386383_550070.jpg", "http://60.205.180.13:81/img/1.jpg", "http://60.205.180.13:81/img/2.jpg", "http://60.205.180.13:81/img/3.jpg", "http://60.205.180.13:81/img/4.jpg"
                    , "http://60.205.180.13:81/img/5.jpg", "http://60.205.180.13:81/img/6.jpg"};
            int index = (int) (Math.random() * doc.length);
            random = doc[index];
            String urlString = random;
            return MarkingFileVo.builder()
                    .image(urlString)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
