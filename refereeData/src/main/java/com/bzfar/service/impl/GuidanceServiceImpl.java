package com.bzfar.service.impl;

import com.bzfar.entity.DataAiBean;
import com.bzfar.entity.PhpTemBean;
import com.bzfar.service.GuidanceService;
import com.bzfar.util.DbSearchLuence;
import com.bzfar.util.GuidanceQuestionLuence;
import com.bzfar.util.Similarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Fimipeler
 * @Description GuidanceServiceImpl
 * @Date 2021/6/16 10:44
 */
@Service
public class GuidanceServiceImpl implements GuidanceService {

    @Autowired
    private GuidanceQuestionLuence guidanceQuestionLuence;


    HashMap<String, String> userLenth = null;

    HashMap<Double, String> mListQuestion = null;

    private double maxSim = 0.0D;

    private String mfindtitle;

    private String mfindanswer;

    private static String[] mInfo = new String[] { "小法暂时只能为您提供法院业务和法律法规咨询，您可以选择以下热门问题点击查看", "小法最擅长的是诉讼服务咨询，比如以下热门问题，您可以点击查看",
            "小法正在工作呢，不要跟小法闲聊哦，您可以选择以下问题进行查看" };


    @Override
    public DataAiBean askQuestion(String question) {
        PhpTemBean bean = new PhpTemBean();
        mListQuestion = new HashMap<Double, String>();
         userLenth = guidanceQuestionLuence.getQuestion(question);
        for (Iterator<Map.Entry<String, String>> iter = userLenth.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> element = iter.next();
            String dbquery = element.getKey();
            double sim = (new Similarity(question, dbquery)).sim();
            this.mListQuestion.put(Double.valueOf(sim), dbquery);
            if (sim > maxSim) {
                maxSim = sim;
                mfindtitle = element.getKey();
                mfindanswer = element.getValue();
            }
        }
        DataAiBean dataAiBean = dataSort(this.maxSim, bean);
        maxSim = 0.0D;
        mfindtitle = "";
        mfindanswer = "";
        return dataAiBean;
    }

    public DataAiBean dataSort(double threshold, PhpTemBean phptemBean) {
        DataAiBean bean = null;
//		if (threshold < 0.6D) {
//			bean = dataSortOut(1, phptemBean);
//		} else if (threshold >= 0.6D && threshold < 0.9D) {
//			bean = dataSortOut(2, phptemBean);
//		} else if (threshold >= 0.9D) {
//			bean = dataSortOut(3, phptemBean);
//		}
        bean = dataSortOut(3, phptemBean);
        return bean;
    }

    public DataAiBean dataSortOut(int state, PhpTemBean phptemBean) {
        System.out.println("分值:" + this.maxSim);
        System.out.println("最佳搜索问题:" + this.mfindtitle + "---" + this.mfindanswer);
        if (state == 1)
            return resultQuesion(phptemBean);
        if (state == 2) {
            ArrayList<Double> yzlist = new ArrayList<>();
            Iterator<Map.Entry<Double, String>> iter = this.mListQuestion.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Double, String> element = iter.next();
                double yz = ((Double) element.getKey()).doubleValue();
                yzlist.add(Double.valueOf(yz));
            }
            Collections.sort(yzlist);
            Collections.reverse(yzlist);
            DataAiBean bean = new DataAiBean();
            ArrayList<DataAiBean.DataList> listbean = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                try {
                    listbean.add(new DataAiBean.DataList(this.mListQuestion.get(yzlist.get(i))));
                } catch (Exception exception) {
                }
            }
            bean.setData(listbean);
            bean.setCode(1);
            bean.setInfo("已为您找到以下相似问题，您可以点击查看");
            return bean;
        }
        if (state == 3) {
            DataAiBean bean = new DataAiBean();
            bean.setCode(1);
            bean.setInfo("已为您找到");
            bean.setAnswer(this.mfindanswer);
            return bean;
        }
        return null;
    }

    public static DataAiBean resultQuesion(PhpTemBean phptemBean) {
        Random random = new Random();
        DataAiBean bean = new DataAiBean();
        bean.setCode(1);
        bean.setInfo("无结果");
        bean.setAnswer(mInfo[random.nextInt(mInfo.length)]);
        return bean;
    }
}
