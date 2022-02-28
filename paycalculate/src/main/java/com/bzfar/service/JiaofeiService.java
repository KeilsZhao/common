package com.bzfar.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface JiaofeiService {

    /**
     * 财产案件
     *
     * @param fee 费用
     * @return
     */
    List<Object> caiChan(BigDecimal fee);

    /**
     * 离婚
     *
     * @return
     */
    List<Object> liHun(BigDecimal fee);

    /**
     * 离婚（财产）
     *
     * @param fee 费用
     * @return
     */
    List<Object> liHunCaiChan(BigDecimal fee);

    /**
     * 人身权
     *
     * @return
     */
    List<Object> renShenQuan(BigDecimal fee);

    /**
     * 人身权（财产）
     *
     * @param fee 费用
     * @return
     */
    List<Object> renShenQuanCaiChan(BigDecimal fee);

    /**
     * 其他非财产
     *
     * @return
     */
    List<Object> qiTaFeiCaiChan(BigDecimal fee);

    /**
     * 知识产权
     *
     * @return
     */
    List<Object> zhiShiChanQuan(BigDecimal fee);

    /**
     * 知识产权（财产）
     *
     * @param fee
     * @return
     */
    List<Object> zhiShiChanQuanCaiChan(BigDecimal fee);


    /**
     * 劳动争议
     *
     * @return
     */
    List<Object> laoDongZhengYi(BigDecimal fee);

    /**
     * 破产
     *
     * @param fee 费用
     * @return
     */
    List<Object> poChan(BigDecimal fee);


    /**
     * 管辖权
     *
     * @return
     */
    List<Object> guanXiaQuan(BigDecimal fee);

    /**
     * 公示催告
     *
     * @return
     */
    List<Object> gongShiCuiGao(BigDecimal fee);

    /**
     * 支付令
     *
     * @param fee 费用
     * @return
     */
    List<Object> zhiFuLing(BigDecimal fee);

    /**
     * 商标
     *
     * @return
     */
    List<Object> shanBiao(BigDecimal fee);

    /**
     * 其他行政案件
     *
     * @return
     */
    List<Object> qiTaXingZheng(BigDecimal fee);


    /**
     * 仲裁
     *
     * @return
     */
    List<Object> zhongCai(BigDecimal fee);


    /**
     * 执行
     *
     * @return
     */
    List<Object> zhiXing(BigDecimal fee);

    /**
     * 执行（财产）
     *
     * @param fee 费用
     * @return
     */
    List<Object> zhiXingCaiChan(BigDecimal fee);

    /**
     * 保全
     *
     * @return
     */
    List<Object> baoQuan(BigDecimal fee);

    /**
     * 保全（财产）
     *
     * @param fee 费用
     * @return
     */
    List<Object> baoQuanCaiChan(BigDecimal fee);
}
