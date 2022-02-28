package com.bzfar.service;

import com.bzfar.dto.CpwsDto;
import com.bzfar.vo.CpwsInfoVo;
import com.bzfar.vo.CpwsVo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
@Service
public interface CpwsService  {

    /**
     * 根据案由或法院名称进行模糊查询，随机获取10条
     * @param cpwsDto 案由或法院名称+显示条数
     * @return
     */
    List<CpwsVo> getCpwsList(CpwsDto cpwsDto);


    /**
     * 根据ID获取文书详情
     * @param id 文书ID
     * @return 文书详情
     */
    CpwsInfoVo getCpwsById(String id);
}
