package com.bzfar.service;

import com.bzfar.dto.CaseSearchInfoDto;
import com.bzfar.dto.FileDto;
import com.bzfar.dto.MuluDto;
import com.bzfar.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarkingService {

    /**
     * 标准版案件当事人不能输入案号时查询
     *
     * @param caseSearchInfoDto
     * @return
     */
    List<CaseProfileInfoVo> partySearchCaseList(CaseSearchInfoDto caseSearchInfoDto);

    /**
     * 律师校验是否直接进入详情界面
     *  律师证件号查询不到与案号匹配的案件，需要进入当事人校验页面
     *  律师证件号查询到了与案号匹配的案件，直接进入详情界面
     * @param caseSearchInfoDto
     * @return
     */
    LawyerCaseInfoVo lawyerCheck(CaseSearchInfoDto caseSearchInfoDto);

    /**
     * 律师案件查询，校验当事人证件号
     * @param caseSearchInfoDto
     * @return
     */
    Boolean lawyerCaseInfo(CaseSearchInfoDto caseSearchInfoDto);

    /**
     * 流水号查询案件信息
     *
     * @param lsh
     */
    CaseDetailInfoVo caseInfoSearch(String lsh);

    /**
     * 获取目录
     * @param muluDto
     * @return
     */
    MuluListsVo getMulu(MuluDto muluDto);


    /**
     * 获取文件信息
     * @param fileDto
     * @return
     */
    MarkingFileVo getFile(FileDto fileDto);

}
