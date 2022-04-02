package com.bzfar.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

public class HttpRequestUtils {

	private static int connectTime = 10000; // 连接超时时间10s

	private static int timeOutTime = 30000; // 超时时间30s

	public static JSONObject httpPost(String url, JSONObject jsonParam) throws IOException {
		return httpPost(url, jsonParam, null, false);
	}

	public static String httpPost(String url, String jsonStr) throws IOException {
		return httpPost(url, jsonStr, null, false);
	}

	public static JSONObject httpPost(String url, String authorization, JSONObject jsonParam) throws IOException {
		return httpPost(url, jsonParam, authorization, false);
	}

	public static String httpPost(String url, String authorization, String jsonStr) throws IOException {
		return httpPost(url, jsonStr, authorization, false);
	}

	/**
	 * post请求
	 * 
	 * @param url            url地址
	 * @param jsonParam      参数
	 * @param noNeedResponse 不需要返回结果
	 * @return
	 * @throws IOException
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam, String authorization, boolean noNeedResponse)
			throws IOException {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				if (authorization != null) {
					method.addHeader("Authorization", "Bearer " + authorization);
				}
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 读取服务器返回过来的json字符串数据 **/
			String str = EntityUtils.toString(result.getEntity());
			if (noNeedResponse) {
				return null;
			}
			/** 把json字符串转换成json对象 **/
			jsonResult = JSON.parseObject(str);
			return jsonResult;
		} catch (IOException e) {
			System.out.println("post请求提交失败:" + url);
			throw e;
		}
	}

	/**
	 * 发送get请求
	 * 
	 * @param url 路径
	 * @return
	 * @throws IOException
	 */
	public static String httpGet(String url) throws IOException {
		// get请求返回结果
		String strResult = "";
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			// 发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			/** 读取服务器返回过来的json字符串数据 **/
			strResult = EntityUtils.toString(response.getEntity());
			/** 把json字符串转换成json对象 **/
			url = URLDecoder.decode(url, "UTF-8");
		} catch (IOException e) {
			System.out.println("get请求提交失败:" + url);
			throw e;
		}
		return strResult;
	}

	public static void readTxtFile(String filePath){
		try {
			String encoding="UTF-8";
			File file=new File(filePath);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file),encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					System.out.println(lineTxt);
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

	public static void main(String argv[]){
		String filePath = "F:\\ZhengJuan.txt";
//      "res/";
		readTxtFile(filePath);
	}
	private static String httpPost(String url, String jsonStr, Object authorization, boolean noNeedResponse)
			throws IOException {
		// post请求返回结果
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, connectTime);
		HttpConnectionParams.setSoTimeout(httpParams, timeOutTime);
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);

		String resultStr = "";
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonStr) {
				// 解决中文乱码问题
				System.out.println(jsonStr);
				StringEntity entity = new StringEntity(jsonStr, "UTF-8");
				entity.setContentType("application/json");
				entity.setContentEncoding("UTF-8");
				if (authorization != null) {
					method.addHeader("Authorization", "Bearer " + authorization);
				}
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			/** 读取服务器返回过来的json字符串数据 **/
			resultStr = EntityUtils.toString(result.getEntity());
			if (noNeedResponse) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("post请求提交失败:" + url);
			throw e;
		}
		return resultStr;
	}

	/**
	 * 上传图片
	 * 
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @param contentType 没有传入文件类型默认采用application/octet-stream
	 *                    contentType非空采用filename匹配默认的图片类型
	 * @return 返回response数据
	 * @throws Exception
	 */
	public static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,
			String contentType) throws Exception {
		String res = "";
		HttpURLConnection conn = null;
		// boundary就是request头和上传文件内容的分隔符
		String BOUNDARY = "---------------------------123821742118716";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			// conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT
			// 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();

					// 没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
					contentType = new MimetypesFileTypeMap().getContentType(file);
					// contentType非空采用filename匹配默认的图片类型
					if (!"".equals(contentType)) {
						if (filename.endsWith(".png")) {
							contentType = "image/png";
						} else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")
								|| filename.endsWith(".jpe")) {
							contentType = "image/jpeg";
						} else if (filename.endsWith(".gif")) {
							contentType = "image/gif";
						} else if (filename.endsWith(".ico")) {
							contentType = "image/image/x-icon";
						}
					}
					if (contentType == null || "".equals(contentType)) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
}