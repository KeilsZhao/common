package com.bzfar.utils;

import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.Map;


public class XmlUtil {
	
	public static com.alibaba.fastjson.JSONObject xmlToJson(String str) {
		JSONObject jsonObj = null;
		try {
            jsonObj = XML.toJSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
		com.alibaba.fastjson.JSONObject result = JSON.parseObject(jsonObj.toString()).getJSONObject("Response");
		if(result == null) {
			result = JSON.parseObject(jsonObj.toString()).getJSONObject("response");
		}
		if(result == null) {
			result = JSON.parseObject(jsonObj.toString());
		}
        return result;
	}
	
	
	public static Map<String, Object> xmlToMap(String str){
		JSONObject jsonObj = null;
		try {
            jsonObj = XML.toJSONObject(str);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		Map<String, Object> result = (Map<String, Object>)jsonObj.toMap().get("Response");
		if(result == null) {
			result = (Map<String, Object>)jsonObj.toMap().get("response");
		}
        return result;
	}
	

	public static void main(String[] args) {
		String xmlStr = "<VXTDH_CatalogueCollection xmlns=\"http://schemas.datacontract.org/2004/07/Rightway.CED.Data.TDH\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><VXTDH_Catalogue><m_QueryConditionCollection i:nil=\"true\" xmlns=\"http://schemas.datacontract.org/2004/07/Matrix.Entity\" xmlns:a=\"http://schemas.datacontract.org/2004/07/Matrix.Entity.Query\"/><Catalogue>正卷</Catalogue><CatalogueId>900000000124684</CatalogueId><VolumeCaption>正卷_0001</VolumeCaption></VXTDH_Catalogue></VXTDH_CatalogueCollection>";
		System.out.println(XmlUtil.xmlToJson(xmlStr));

	}

}
