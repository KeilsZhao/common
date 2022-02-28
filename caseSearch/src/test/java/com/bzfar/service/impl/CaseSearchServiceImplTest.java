package com.bzfar.service.impl;

import com.bzfar.service.CaseSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CaseSearchServiceImplTest {

    @Autowired
    private CaseSearchService caseSearchService;

    @Test
    public void caseInfoSearch() {
        caseSearchService.caseInfoSearch("001");
    }
}