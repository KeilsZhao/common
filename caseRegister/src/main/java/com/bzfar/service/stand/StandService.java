package com.bzfar.service.stand;

import java.util.List;

/**
 * 标准版立案相关依赖接口-采用四川简阳作为样例
 */
public interface StandService {

    /**
     * 获取案由信息
     *
     * @param lb 类由类别，民事一审（2）首次执行（8）
     * @return
     */
    List<Object> queryAy(String lb);

    /**
     * 根据kind获取法标数据
     *
     * @param kind 需要查询的KIND值
     * @return
     */
    List<Object> queryBzdm(String kind);
}
