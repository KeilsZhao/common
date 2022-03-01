package com.bzfar.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Iterator;

public class AnalysisUtil {

	public static String analysisJson(String str) {
		Object objJson = JSONObject.fromObject(str);

		objJson = analysisJson(objJson);

		return (String) objJson;

	}

	public static String analysisJson(Object objJson) {

		// 如果obj为json数组
		if (objJson instanceof JSONArray) {
			JSONArray objArray = (JSONArray) objJson;
			for (int i = 0; i < objArray.size(); i++) {
				analysisJson(objArray.get(i));
			}
		}
		// 如果为json对象
		else if (objJson instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) objJson;
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next().toString();
				Object object = jsonObject.get(key);
				// 如果得到的是数组
				if (object instanceof JSONArray) {
					JSONArray objArray = (JSONArray) object;
					analysisJson(objArray);
				}
				// 如果key中是一个json对象
				else if (object instanceof JSONObject) {
					analysisJson((JSONObject) object);
				}
				// 如果key中是其他
				else {
					jsonObject.put(key, Base64Util.decode(object.toString()));
				}
			}
		}
		return objJson.toString();
	}

}
