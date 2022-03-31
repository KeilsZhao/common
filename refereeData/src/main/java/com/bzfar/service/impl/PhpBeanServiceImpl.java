package com.bzfar.service.impl;

import com.bzfar.entity.DataInfo;
import com.bzfar.entity.PhpTemBean;
import com.bzfar.exception.DataException;
import com.bzfar.service.PhpBeanService;
import com.bzfar.util.DbSearchLuence;
import com.bzfar.util.Similarity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PhpBeanServiceImpl implements PhpBeanService {

    @Autowired
    private PhpTemBean bean;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DbSearchLuence dbSearchLuece;

    // 精准查询
    private double maxSim = 1.0D;

    // 模糊查询
    private double fuzzyManSim = 0.5D;

    private HashMap<Double, String> mListQuestion = null;

    @Override
    public CompletableFuture<List<DataInfo>> appInfo(String query, Integer max) {
        mListQuestion = new HashMap<Double, String>();
        String[] fields = {"ay"};
        List<HashMap<String, String>> questions = dbSearchLuece.getQuestion(query,fields,max);
        List<DataInfo> list = new ArrayList<>();
        for (int i = 0 ; i < questions.size(); i++){
            try {
                HashMap<String, String> stringStringHashMap = questions.get(i);
                String dbquery = questions.get(i).get("ay");
                double sim = (new Similarity(query, dbquery)).sim();
                String json = objectMapper.writeValueAsString(questions.get(i));
                this.mListQuestion.put(Double.valueOf(sim), dbquery);
                if (sim >= maxSim) {
                    DataInfo dataInfo = objectMapper.readValue(json, DataInfo.class);
                    dataInfo.setContent(null);
                    list.add(dataInfo);
                }
            } catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
        Collections.shuffle(list);
        max = max < list.size() ? max : list.size();
        list = list.subList(0, max);
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<List<DataInfo>> causeFuzzySearch(String ay, Integer max) {
        mListQuestion = new HashMap<Double, String>();
        String[] fields = {"ay"};
        List<HashMap<String, String>> questions = dbSearchLuece.getQuestion(ay,fields,max);
        List<DataInfo> list = new ArrayList<>();
        for (int i = 0 ; i < questions.size(); i++){
            try {
                HashMap<String, String> stringStringHashMap = questions.get(i);
                String dbquery = questions.get(i).get("ay");
                double sim = (new Similarity(ay, dbquery)).sim();
                String json = objectMapper.writeValueAsString(questions.get(i));
                this.mListQuestion.put(Double.valueOf(sim), dbquery);
                if (sim >= fuzzyManSim) {
                    DataInfo dataInfo = objectMapper.readValue(json, DataInfo.class);
                    dataInfo.setContent(null);
                    list.add(dataInfo);
                }
            } catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
        Collections.shuffle(list);
        max = max < list.size() ? max : list.size();
        list = list.subList(0, max);
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<List<DataInfo>> courtNameFuzzySearch(String courtName, Integer max) {
        mListQuestion = new HashMap<Double, String>();
        String[] fields = {"countName"};
        List<HashMap<String, String>> questions = dbSearchLuece.getQuestion(courtName,fields,max);
        List<DataInfo> list = new ArrayList<>();
        for (int i = 0 ; i < questions.size(); i++){
            try {
                HashMap<String, String> stringStringHashMap = questions.get(i);
                String dbquery = questions.get(i).get("countName");
                double sim = (new Similarity(courtName, dbquery)).sim();
                String json = objectMapper.writeValueAsString(questions.get(i));
                this.mListQuestion.put(Double.valueOf(sim), dbquery);
                if (sim >= fuzzyManSim) {
                    DataInfo dataInfo = objectMapper.readValue(json, DataInfo.class);
                    dataInfo.setContent(null);
                    list.add(dataInfo);
                }
            } catch (Exception e) {
                throw new DataException(e.getMessage());
            }
        }
        Collections.shuffle(list);
        max = max < list.size() ? max : list.size();
        list = list.subList(0, max);
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<DataInfo> queryAppInfoById(String id) {
        HashMap<String, String> questionById = dbSearchLuece.getQuestionById(id);
        try {
            String json = objectMapper.writeValueAsString(questionById);
            DataInfo dataInfo = objectMapper.readValue(json, DataInfo.class);
            return CompletableFuture.completedFuture(dataInfo);
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
    }
}
