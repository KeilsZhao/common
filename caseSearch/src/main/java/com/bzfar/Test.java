package com.bzfar;

import com.bzfar.exception.DataException;
import com.bzfar.utils.HandleFile;
import com.bzfar.utils.MockFileUtils;
import com.bzfar.utils.MockJarPathUtils;
import com.bzfar.vo.CaseVo;
import com.bzfar.vo.caseSearch.ThirdCaseResultVo;
import com.bzfar.vo.caseSearch.ThirdCaseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test {

    public static void main(String[] args) throws IOException {
//        String json = "{\"code\":200,\"msg\":\"成功\",\"data\":[{\"AHDM\":\"228820180301000005\",\"AH\":\"(2018)鄂1083民初939号\",\"FYDM\":\"2288\",\"AJMC\":\"颜加普与郑慧娟离婚纠纷\",\"SAAY\":\"9015\",\"AYMS\":\"离婚纠纷\",\"AJLB\":\"民事\",\"SARQ\":\"20180717\",\"LARQ\":\"20180717\",\"JARQ\":\"20180809\",\"ZT\":\"1\",\"AJZTMS\":\"结案\",\"CBBM1\":\"42040522\",\"SPZ\":\"420405张军\",\"CBR\":\"420405张军\",\"SJY\":\"420405付小康\",\"FGZL\":null,\"JAFS\":\"1\",\"JAFSMS\":\"判决\",\"YSSX\":null,\"SXRQ\":null,\"XTAJLX\":\"21\",\"ZHGXSJ\":\"2019-06-12 20:52:31\",\"GDJSRQ\":null,\"DSR\":\"原告:颜加普;被告:郑慧娟\",\"JABD\":\"0\",\"XLA\":\"0\",\"KTRQ\":null,\"KTFT\":null,\"CBRMC\":\"张军\",\"SJYMC\":\"付小康\",\"SFSLAJ\":null,\"SAR\":\"420405聂璇\",\"list\":{\"DA_BASE\":{\"DHDM\":\"420405Z00004828\",\"NNDD\":\"2018\",\"DABM\":\"民初\",\"DH\":\"(2018)鄂1083民初939号\",\"QZH\":null,\"MLH\":null,\"JZH\":null,\"MJ\":null,\"BGQX\":\"永久\",\"LJBM\":null,\"LJR\":null,\"LJRQ\":\"20180316\",\"GDBM\":null,\"GDR\":null,\"GDRQ\":\"20180912\",\"YJR\":null,\"JSR\":null,\"JSRQ\":null,\"AJZCS\":\"2\",\"ZJCS\":\"1\",\"ZJYS\":\"38\",\"FJCS\":\"1\",\"FJYS\":\"12\",\"YSJS\":\"0\",\"YSYS\":\"0\",\"YSJCS\":\"0\",\"YSJYS\":\"0\",\"GAJCS\":\"0\",\"GAJYS\":\"0\",\"JCJCS\":\"0\",\"JCJYS\":\"0\",\"BJQK\":null,\"JDQK\":null,\"BEIZ\":null,\"ZWQK\":\"原告:颜加普;被告:郑慧娟\"},\"DA_SSZLXX\":{\"AH\":\"（2018）鄂1083民初00939号\",\"JBFY\":null,\"YSAH\":null,\"XGAH\":null,\"LAAY\":\"离婚纠纷\",\"LARQ\":\"20180316\",\"LAR\":null,\"AJLY\":null,\"ZXYJWSBH\":null,\"SYCX\":null,\"CBBM\":null,\"SPZH\":null,\"CBRR\":\"张军\",\"CBRSSJS\":null,\"HYCY\":null,\"HYCYSSJS\":null,\"SJY\":\"付小康\",\"JAFS\":null,\"YSJG\":null,\"ESFY\":null,\"ESJG\":null,\"ZSFY\":null,\"ZSJG\":null,\"ZXFY\":null,\"ZXJG\":null,\"JARQ\":\"20180912\",\"JABDJE\":\"0.0\",\"JAAY\":\"离婚纠纷\",\"SXRQ\":null,\"SSRQ\":null},\"DA_SSDSRXX\":[null,null]}}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HandleFile handleFile = new MockJarPathUtils();
            InputStream mockData = handleFile.handleMockData("CaseSearchMock.json");
            ThirdCaseResultVo thirdCaseResultVo = objectMapper.readValue(mockData, ThirdCaseResultVo.class);
            List<ThirdCaseVo> data = thirdCaseResultVo.getData();
            List<CaseVo> collect = data.stream().map(thirdCaseVo -> {
                return CaseVo.builder()
                        .ah(thirdCaseVo.getAh())
                        .type(thirdCaseVo.getAjlb())
                        .status(thirdCaseVo.getAjzt())
                        .ay(thirdCaseVo.getAyms())
                        .larq(thirdCaseVo.getLarq())
                        .jarq(thirdCaseVo.getJarq())
                        .cbbm(thirdCaseVo.getCbbm())
                        .cbr(thirdCaseVo.getCbrmc())
                        .build();
            }).collect(Collectors.toList());

            System.out.print(collect.size());
        }catch (Exception e) {
            throw new DataException(e.getMessage());
        }

    }
}
