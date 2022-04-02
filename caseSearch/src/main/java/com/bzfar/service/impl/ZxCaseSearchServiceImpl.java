package com.bzfar.service.impl;

import com.bzfar.dto.ZxCaseSearchInfoDto;
import com.bzfar.exception.DataException;
import com.bzfar.service.ZxCaseSearchService;
import com.bzfar.util.AssertUtil;
import com.bzfar.utils.MockUtil;
import com.bzfar.vo.caseSearch.zxNewCaseInfo.*;
import com.bzfar.vo.caseSearch.zxNewCaseInfo.mock.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ZxCaseSearchServiceImpl implements ZxCaseSearchService {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public List<ZxCaseProfileInfoVo> partySearchCaseList(ZxCaseSearchInfoDto zxCaseSearchInfoDto) {
        List<ZxCaseProfileInfoVo> caseProfileInfoVos = new ArrayList<>();
        try {
            String idCard = zxCaseSearchInfoDto.getIdCard();
            AssertUtil.assertNull(idCard, "身份证号不能为空");
            InputStream dsrInfo = MockUtil.getFileIo("ZxDsrInfo.json");
            MockZxDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockZxDsrInfo.class);
            List<MockZxDsrDetail> mockDsrDetailList = mockDsrInfo.getData().stream().filter(item -> item.getZjh().equals(idCard)).collect(Collectors.toList());
            AssertUtil.assertNull(mockDsrDetailList, "身份证号暂无案件信息");
            mockDsrDetailList.stream().forEach(item -> {
                ZxCaseInfoVo caseInfoVo = this.caseInfoSearch(item.getLsh());
                ZxCaseDetailInfoVo detailInfoVo = caseInfoVo.getZxCaseDetailInfoVo();
                ZxCaseProfileInfoVo caseProfileInfoVo = ZxCaseProfileInfoVo.builder()
                        .lsh(item.getLsh())
                        .dz(detailInfoVo.getDz())
                        .ah(detailInfoVo.getAh())
                        .status(detailInfoVo.getStatus())
                        .ay(detailInfoVo.getAy())
                        .larq(detailInfoVo.getLarq())
                        .jarq(detailInfoVo.getJarq())
                        .cbbm(detailInfoVo.getCbbm())
                        .cbr(detailInfoVo.getCbr())
                        .ajlx(detailInfoVo.getAjlx()).build();
                caseProfileInfoVos.add(caseProfileInfoVo);
            });
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return caseProfileInfoVos;
    }

    @Override
    public ZxLawyerCaseInfoVo lawyerCheck(ZxCaseSearchInfoDto zxCaseSearchInfoDto) {
        ZxLawyerCaseInfoVo lawyerCaseInfoVo = new ZxLawyerCaseInfoVo();
        try {
            String lawyerCard = zxCaseSearchInfoDto.getLawyerCard();
            AssertUtil.assertNull(lawyerCard, "律师资格证号不能为空");
            String ah = zxCaseSearchInfoDto.getAh();
            AssertUtil.assertNull(ah, "案号不能为空");
            InputStream dlrInfo = MockUtil.getFileIo("ZxDlrInfo.json");
            MockZxDlrInfo mockDlrInfo = objectMapper.readValue(dlrInfo, MockZxDlrInfo.class);
            List<MockZxDlrDetail> data = mockDlrInfo.getData();
            AssertUtil.assertNull(data, "律师信息有误");
            List<String> collect = data.stream().filter(item -> item.getZjh().equals(lawyerCard)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            AssertUtil.assertNull(collect, "律师信息不存在");
            InputStream caseInfo = MockUtil.getFileIo("ZxCaseInfo.json");
            MockZxCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockZxCaseInfo.class);
            List<MockZxCaseDetail> collect1 = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).collect(Collectors.toList());
            AssertUtil.assertNull(collect1, "证件号 暂无信息");
            String lsh = collect1.get(0).getLsh();
            if (collect.contains(lsh)) {
                lawyerCaseInfoVo.setDetail(true);
                ZxCaseInfoVo caseInfoVo = this.caseInfoSearch(lsh);
                lawyerCaseInfoVo.setZxCaseInfoVo(caseInfoVo);
            } else {
                lawyerCaseInfoVo.setDetail(false);
                ZxCaseInfoVo caseInfoVo = this.caseInfoSearch(lsh);
                ZxCaseDetailInfoVo detailInfoVo = caseInfoVo.getZxCaseDetailInfoVo();
                ZxCaseProfileInfoVo caseProfileInfoVo = ZxCaseProfileInfoVo.builder()
                        .lsh(lsh)
                        .dz(detailInfoVo.getDz())
                        .ah(detailInfoVo.getAh())
                        .status(detailInfoVo.getStatus())
                        .ay(detailInfoVo.getAy())
                        .larq(detailInfoVo.getLarq())
                        .jarq(detailInfoVo.getJarq())
                        .cbbm(detailInfoVo.getCbbm())
                        .cbr(detailInfoVo.getCbr())
                        .ajlx(detailInfoVo.getAjlx()).build();
                List<String> sqr = detailInfoVo.getSqr().stream().map(item -> {
                    return item.getName();
                }).collect(Collectors.toList());
                List<String> bzxr = detailInfoVo.getBzxr().stream().map(item -> {
                    return item.getName();
                }).collect(Collectors.toList());
                caseProfileInfoVo.setSqr(sqr);
                caseProfileInfoVo.setBzxr(bzxr);
                lawyerCaseInfoVo.setZxCaseProfileInfoVo(caseProfileInfoVo);
            }
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        return lawyerCaseInfoVo;
    }


    @Override
    public ZxCaseInfoVo lawyerCaseInfo(ZxCaseSearchInfoDto zxCaseSearchInfoDto) {
        String ah = zxCaseSearchInfoDto.getAh();
        AssertUtil.assertNull(ah, "案号信息不能为空");
        String zjh = zxCaseSearchInfoDto.getZjh();
        AssertUtil.assertNull(zjh, "当事人证件信息不能为空");
        ZxCaseInfoVo caseInfoVo = new ZxCaseInfoVo();
        try {
            InputStream caseInfo = MockUtil.getFileIo("ZxCaseInfo.json");
            MockZxCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockZxCaseInfo.class);
            List<String> collect = mockCaseInfo.getData().stream().filter(item -> item.getAh().equals(ah)).map(item -> {
                return item.getLsh();
            }).collect(Collectors.toList());
            String lsh = collect.get(0);
            InputStream dsrInfo = MockUtil.getFileIo("ZxDsrInfo.json");
            MockZxDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockZxDsrInfo.class);
            List<MockZxDsrDetail> data = mockDsrInfo.getData();
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
    public ZxCaseInfoVo caseInfoSearch(String lsh) {
        ZxCaseInfoVo caseInfoVo = new ZxCaseInfoVo();
        try {
            // 案件列表
            InputStream caseInfo = MockUtil.getFileIo("ZxCaseInfo.json");
            MockZxCaseInfo mockCaseInfo = objectMapper.readValue(caseInfo, MockZxCaseInfo.class);
            List<MockZxCaseDetail> mockCaseDetailList = mockCaseInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            if (ObjectUtils.isEmpty(mockCaseDetailList) || mockCaseDetailList.size() > 1) {
                throw new DataException("流水号：" + lsh + "案件信息错误，请检查mock数据");
            }
            // 案件当事人
            InputStream dsrInfo = MockUtil.getFileIo("ZxDsrInfo.json");
            MockZxDsrInfo mockDsrInfo = objectMapper.readValue(dsrInfo, MockZxDsrInfo.class);
            List<MockZxDsrDetail> mockDsrDetailList = mockDsrInfo.getData().stream().filter(item -> item.getLsh().equals(lsh)).collect(Collectors.toList());
            ZxCaseDetailInfoVo zxCaseDetailInfoVo = personInfo(mockDsrDetailList, mockCaseDetailList.get(0));
            caseInfoVo.setZxCaseDetailInfoVo(zxCaseDetailInfoVo);
            // 执行日志
            InputStream zxLog = MockUtil.getFileIo("ZxLog.json");
            MockZxLogInfo mockZxLogInfo = objectMapper.readValue(zxLog, MockZxLogInfo.class);
            List<MockZxLogDetail> mockFileInfoData = mockZxLogInfo.getData();
            List<ZxCaseLogInfoVo> zxCaseLogInfoVos = mockFileInfoData.stream().map(item -> {
                return ZxCaseLogInfoVo.builder()
                        .content(item.getContent())
                        .zxrq(item.getZxrq())
                        .week(item.getWeek()).build();
            }).collect(Collectors.toList());
            caseInfoVo.setCaseLogInfoVos(zxCaseLogInfoVos);
        } catch (IOException e) {
            throw new DataException(e.getMessage());
        }
        System.out.print(caseInfoVo);
        return caseInfoVo;
    }

    private ZxCaseDetailInfoVo personInfo(List<MockZxDsrDetail> mockDsrDetailList, MockZxCaseDetail detail) {
        AssertUtil.assertNull(mockDsrDetailList, "当事人信息缺失");
        List<ZxCaseDsrInfoVo> caseDsrInfoVos = mockDsrDetailList.stream().map(item -> {
            ZxCaseDsrInfoVo caseDsrInfoVo = ZxCaseDsrInfoVo.builder()
                    .type(item.getType())
                    .dw(item.getDw())
                    .name(item.getName())
                    .zjh(item.getZjh()).build();
            return caseDsrInfoVo;
        }).collect(Collectors.toList());
        // 申请人
        List<ZxCaseDsrInfoVo> sqrList = caseDsrInfoVos.stream().filter(sqr -> sqr.getDw().equals("1")).collect(Collectors.toList());
        // 被执行人
        List<ZxCaseDsrInfoVo> bzxrList = caseDsrInfoVos.stream().filter(bzxr -> bzxr.getDw().equals("2")).collect(Collectors.toList());
        // 组装
        ZxCaseDetailInfoVo detailInfoVo = ZxCaseDetailInfoVo.builder()
                .ay(detail.getAy())
                .ah(detail.getAh())
                .dz(detail.getDz())
                .status(detail.getStatus())
                .ajlx(detail.getAjlx())
                .larq(detail.getLarq())
                .jarq(detail.getJarq())
                .cbbm(detail.getCbbm())
                .cbr(detail.getCbr())
                .spz(detail.getSpz())
                .sycx(detail.getSycx())
                .court(detail.getCourt())
                .bdje(detail.getBdje())
                .lxqk(detail.getLxqk())
                .akff(detail.getAkff())
                .sqr(sqrList)
                .bzxr(bzxrList).build();
        return detailInfoVo;
    }
}
