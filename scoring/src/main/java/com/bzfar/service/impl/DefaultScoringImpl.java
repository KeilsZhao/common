package com.bzfar.service.impl;

import com.bzfar.enums.CourtEnum;
import com.bzfar.service.ScoringService;
import com.bzfar.vo.DossierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘成
 * @date 2021-6-3
 */
@Service
@Primary
public class DefaultScoringImpl implements ScoringService {

    @Autowired
    private TongDaHaiDossierCatalog tongDaHaiDossierCatalog;

    @Override
    public List<DossierVo> getDossierCatalog(CourtEnum courtEnum, String ah) {
        List<DossierVo> dossierCatalog = new ArrayList<>();
         switch (courtEnum){
            case TONGDAHAI:
                dossierCatalog = tongDaHaiDossierCatalog.getDossierCatalog(null, ah);
                break;
        }
        return dossierCatalog;
    }
}
