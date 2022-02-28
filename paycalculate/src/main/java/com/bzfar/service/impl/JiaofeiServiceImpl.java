package com.bzfar.service.impl;

import com.bzfar.annotation.CalculateMethod;
import com.bzfar.annotation.Jiaofei;
import com.bzfar.service.JiaofeiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class JiaofeiServiceImpl implements JiaofeiService {
    
    @Resource
    private Jiaofei jiaofei;

    @Override
    @CalculateMethod(type = "1")
    public List<Object> caiChan(BigDecimal fee) {
        return jiaofei.caichan(fee);
    }

    @Override
    @CalculateMethod(type = "2")
    public List<Object> liHun(BigDecimal fee) {
        return jiaofei.liHun(fee);
    }

    @Override
    @CalculateMethod(type = "3")
    public List<Object> liHunCaiChan(BigDecimal fee) {
        return jiaofei.liHunCaiChan(fee);
    }

    @Override
    @CalculateMethod(type = "4")
    public List<Object> renShenQuan(BigDecimal fee) {
        return jiaofei.renShenQuan(fee);
    }

    @Override
    @CalculateMethod(type = "5")
    public List<Object> renShenQuanCaiChan(BigDecimal fee) {
        return jiaofei.renShenQuanCaiChan(fee);
    }

    @Override
    @CalculateMethod(type = "6")
    public List<Object> qiTaFeiCaiChan(BigDecimal fee) {
        return jiaofei.qiTaFeiCaiChan(fee);
    }

    @Override
    @CalculateMethod(type = "7")
    public List<Object> zhiShiChanQuan(BigDecimal fee) {
        return jiaofei.zhiShiChanQuan(fee);
    }

    @Override
    @CalculateMethod(type = "8")
    public List<Object> zhiShiChanQuanCaiChan(BigDecimal fee) {
        return jiaofei.zhiShiChanQuanCaiChan(fee);
    }

    @Override
    @CalculateMethod(type = "9")
    public List<Object> laoDongZhengYi(BigDecimal fee) {
        return jiaofei.LaoDongZhengYi(fee);
    }

    @Override
    @CalculateMethod(type = "10")
    public List<Object> poChan(BigDecimal fee) {
        return jiaofei.getPochan(fee);
    }

    @Override
    @CalculateMethod(type = "11")
    public List<Object> guanXiaQuan(BigDecimal fee) {
        return jiaofei.guanXiaQuan(fee);
    }

    @Override
    @CalculateMethod(type = "12")
    public List<Object> gongShiCuiGao(BigDecimal fee) {
        return jiaofei.gongShiCuiGao(fee);
    }

    @Override
    @CalculateMethod(type = "13")
    public List<Object> zhiFuLing(BigDecimal fee) {
        return jiaofei.Zhifuling(fee);
    }

    @Override
    @CalculateMethod(type = "14")
    public List<Object> shanBiao(BigDecimal fee) {
        return jiaofei.shangBiao(fee);
    }

    @Override
    @CalculateMethod(type = "15")
    public List<Object> qiTaXingZheng(BigDecimal fee) {
        return jiaofei.qiTaXingZheng(fee);
    }

    @Override
    @CalculateMethod(type = "16")
    public List<Object> zhongCai(BigDecimal fee) {
        return jiaofei.zhongCai(fee);
    }

    @Override
    @CalculateMethod(type = "17")
    public List<Object> zhiXing(BigDecimal fee) {
        return jiaofei.zhiXing(fee);
    }

    @Override
    @CalculateMethod(type = "18")
    public List<Object> zhiXingCaiChan(BigDecimal fee) {
        return jiaofei.zhiXingCaiChan(fee);
    }

    @Override
    @CalculateMethod(type = "19")
    public List<Object> baoQuan(BigDecimal fee) {
        return jiaofei.baoQuan(fee);
    }

    @Override
    @CalculateMethod(type = "20")
    public List<Object> baoQuanCaiChan(BigDecimal fee) {
        return jiaofei.baoQuanCaiChan(fee);
    }
}
