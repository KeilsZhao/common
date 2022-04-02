package com.bzfar.service;

import com.bzfar.dto.CaseSearchDto;
import com.bzfar.dto.CaseSearchInfoDto;
import com.bzfar.vo.CaseListVo;
import com.bzfar.vo.caseSearch.newCaseInfo.CaseInfoVo;
import com.bzfar.vo.caseSearch.newCaseInfo.CaseProfileInfoVo;
import com.bzfar.vo.caseSearch.newCaseInfo.LawyerCaseInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseSearchService {

    /**
     * 通用案件查询
     *
     * @param caseSearchDto
     * @return
     */
    CaseListVo searchCase(CaseSearchDto caseSearchDto);

    /**
     * 四川案件查询
     * @param caseSearchDto
     * @return
     */
    CaseListVo searchSc(CaseSearchDto caseSearchDto);


    /**
     * 洪山区
     * @param caseSearchDto
     * @return
     */
    CaseListVo searchHs(CaseSearchDto caseSearchDto);

    /**
     * 标准版案件当事人不能输入案号时查询
     *
     * @param caseSearchInfoDto
     * @return
     */
    List<CaseProfileInfoVo> partySearchCaseList(CaseSearchInfoDto caseSearchInfoDto);


    /**
     * 标准版案件当事人查询新版接口（可以输入案号）
     *
     * @param caseSearchInfoDto 查询参数
     * @return
     */
    CaseInfoVo partySearchCaseInfo(CaseSearchInfoDto caseSearchInfoDto);

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
    CaseInfoVo lawyerCaseInfo(CaseSearchInfoDto caseSearchInfoDto);

    /**
     * 标准版案件详情查询
     *
     * @param lsh
     */
    CaseInfoVo caseInfoSearch(String lsh);

}
