package com.bzfar.service;

import com.bzfar.dto.ZxCaseSearchInfoDto;
import com.bzfar.vo.caseSearch.newCaseInfo.CaseInfoVo;
import com.bzfar.vo.caseSearch.zxNewCaseInfo.ZxCaseInfoVo;
import com.bzfar.vo.caseSearch.zxNewCaseInfo.ZxCaseProfileInfoVo;
import com.bzfar.vo.caseSearch.zxNewCaseInfo.ZxLawyerCaseInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZxCaseSearchService {


    /**
     * 标准版案件当事人不能输入案号时查询
     *
     * @param zxCaseSearchInfoDto
     * @return
     */
    List<ZxCaseProfileInfoVo> partySearchCaseList(ZxCaseSearchInfoDto zxCaseSearchInfoDto);


    /**
     * 律师校验是否直接进入详情界面
     *  律师证件号查询不到与案号匹配的案件，需要进入当事人校验页面
     *  律师证件号查询到了与案号匹配的案件，直接进入详情界面
     * @param zxCaseSearchInfoDto
     * @return
     */
    ZxLawyerCaseInfoVo lawyerCheck(ZxCaseSearchInfoDto zxCaseSearchInfoDto);

    /**
     * 律师案件查询，校验当事人证件号
     * @param zxCaseSearchInfoDto
     * @return
     */
    ZxCaseInfoVo lawyerCaseInfo(ZxCaseSearchInfoDto zxCaseSearchInfoDto);

    /**
     * 标准版案件详情查询
     *
     * @param lsh
     */
    ZxCaseInfoVo caseInfoSearch(String lsh);

}
