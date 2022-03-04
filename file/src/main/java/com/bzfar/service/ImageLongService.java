package com.bzfar.service;

import com.bzfar.vo.FormatVo;
import com.bzfar.vo.TempleFileVo;

import java.util.List;

/**
 * 图片操作
 * @author Administrator
 */
public interface ImageLongService {


    /**
     * 多张图片凭借
     *
     * @param templeFileVos 多张图片临时存储地址
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @return 合成后的图片地址
     */
    FormatVo splicingImage(List<TempleFileVo> templeFileVos,Boolean isHorizontal);

}
