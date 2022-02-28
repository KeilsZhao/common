package com.bzfar.service.impl;

import com.bzfar.configuration.ScoringConfiguration;
import com.bzfar.configuration.TongDaHaiConfiguration;
import com.bzfar.enums.CourtEnum;
import com.bzfar.enums.TongDaHaiScoringEnum;
import com.bzfar.exception.DataException;
import com.bzfar.po.FilePo;
import com.bzfar.po.ScoringPo;
import com.bzfar.po.TongDaHaiScoringPo;
import com.bzfar.service.ScoringService;
import com.bzfar.util.mock.HandleFile;
import com.bzfar.util.mock.MockJarPathUtils;
import com.bzfar.vo.DossierVo;
import com.bzfar.vo.FileVo;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 刘成
 * @date 2021-6-3
 */
@Service
@Slf4j
public class TongDaHaiDossierCatalog implements ScoringService {

    @Autowired
    private TongDaHaiConfiguration tongDaHaiConfiguration;

    @Autowired
    private ScoringConfiguration scoringConfiguration;

    @Autowired
    private XmlMapper xmlMapper;

    @Override
    public List<DossierVo> getDossierCatalog(CourtEnum courtEnum, String ah) {
        log.info("【查询】ah = {}" , ah);
        if(scoringConfiguration.isScoringMock()){
            return getMockDossierCatalog(ah);
        }
        return null;
    }

    /**
     * mock结果数据
     * @param ah 案号
     * @return
     */
    private List<DossierVo> getMockDossierCatalog(String ah){
        try{
            HandleFile handleFile = new MockJarPathUtils();
            InputStream mock = handleFile.handleMockData("tongdahai");
            Scanner s = new Scanner(mock).useDelimiter("\\A");
            String mockStr = s.hasNext() ? s.next() : "";
            return getDossierCatalog(mockStr , ah);
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }


    private List<DossierVo> getDossierCatalog(String decodeStr, String ah){
        try{
            String result = new String(new BASE64Decoder().decodeBuffer(decodeStr), "UTF-8");
            TongDaHaiScoringPo resultPo = xmlMapper.readValue(result, TongDaHaiScoringPo.class);
            List<ScoringPo> scoringPoList = resultPo.getScoringList().stream().filter(item -> item.getJzlb().equals(TongDaHaiScoringEnum.POSITIVE.getName())).collect(Collectors.toList());
            List<FilePo> filePos = resultPo.getFileList().stream().filter(item -> item.getJzlb().equals(TongDaHaiScoringEnum.POSITIVE.getName()) && item.getPgtype().equals(TongDaHaiScoringEnum.TEXT.getName())).collect(Collectors.toList());
            List<ScoringTransit> transit = transit(scoringPoList);
            List<DossierVo> collect = transit.stream().sorted(Comparator.comparing(ScoringTransit::getTransitYeMa).reversed()).map(item -> {
                DossierVo dossierVo = DossierVo.builder()
                        .type(item.getJzlb())
                        .name(item.getClbt())
                        .startPageNum(item.getTransitYeMa())
                        .serialNum(item.getXh())
                        .build();
                List<FileVo> fileList = new ArrayList<>();
                Iterator<FilePo> iterator = filePos.iterator();
                while (iterator.hasNext()){
                    FilePo filePo = iterator.next();
                    if(item.getTransitYeMa() <= Integer.parseInt(filePo.getYema().split("-")[0])){
                        FileVo fileVo = FileVo.builder()
                                .category(filePo.getJzlb())
                                .type(filePo.getPgtype())
                                .page(filePo.getYema())
                                .fileName(filePo.getFileName())
                                .ossName(filePo.getOssName())
                                .ftpServer(filePo.getFtpServer())
                                .build();
                        fileList.add(fileVo);
                        iterator.remove();
                    }
                }
                dossierVo.setFileList(fileList);
                return dossierVo;
            }).sorted(Comparator.comparing(DossierVo::getStartPageNum)).collect(Collectors.toList());
            return collect;
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }

    /**
     * 第三方数据转化为中转数据
     * @param list 第三方数据
     * @return
     */
    private List<ScoringTransit> transit(List<ScoringPo> list){
        List<ScoringTransit> collect = list.stream().map(item -> {
            ScoringTransit scoringTransit = new ScoringTransit();
            BeanUtils.copyProperties(item, scoringTransit);
            scoringTransit.setClbt(item.getClbt());
            scoringTransit.setTransitYeMa(Integer.parseInt(item.getP1()));
            return scoringTransit;
        }).collect(Collectors.toList());
        return collect;
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("卷宗第三方数据中转类")
class ScoringTransit{

    @ApiModelProperty("正卷、副卷")
    private String jzlb;

    private String ch;

    private String xh;

    @ApiModelProperty("标题")
    private String clbt;

    @ApiModelProperty("中转页码")
    private Integer transitYeMa;
}