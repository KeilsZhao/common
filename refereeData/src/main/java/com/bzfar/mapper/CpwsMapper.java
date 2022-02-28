package com.bzfar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzfar.entity.Cpws;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
public interface CpwsMapper extends BaseMapper<Cpws> {

    @Select("SELECT * from cpws LIMIT 1345 , 1000")
    List<Cpws> queryList();
}
