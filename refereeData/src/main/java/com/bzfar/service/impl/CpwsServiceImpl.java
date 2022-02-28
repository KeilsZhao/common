package com.bzfar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzfar.entity.Cpws;
import com.bzfar.exception.DataException;
import com.bzfar.mapper.CpwsMapper;
import com.bzfar.service.CpwsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethons
 * @since 2020-11-23
 */
@Service
public class CpwsServiceImpl extends ServiceImpl<CpwsMapper, Cpws> implements CpwsService {

    @Resource
    private CpwsMapper cpwsMapper;


    @Override
    public Cpws queryById(Integer id) {
        Cpws cpws = null;
        try{
            cpws = cpwsMapper.selectById(id);
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
        return cpws;
    }

    @Override
    public List<Cpws> queryAll() {
        List<Cpws> cpws = new ArrayList<>();
        try{
            cpws = cpwsMapper.queryList();
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
        return cpws;
    }
}
