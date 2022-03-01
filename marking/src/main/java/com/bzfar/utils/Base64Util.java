package com.bzfar.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

// Base64
public class Base64Util {

	final static BASE64Encoder encoder = new BASE64Encoder();

	final static BASE64Decoder decoder = new BASE64Decoder();

	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(String data) {
		if ("".equals(data)) {
			return "";
		}
//		return data;
		try {
			return encoder.encode(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 解密
	 * 
	 * @param code
	 * @return
	 */
	public static String decode(String code) {
		if ("".equals(code)) {
			return "";
		}
		String content = null;
		try {
			content = new String(decoder.decodeBuffer(code), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

	public static Map<String, Object> decodeMap(Map<String, Object> map) {
		Map<String, Object> temp = new HashMap<String, Object>();
		for (Map.Entry<String, Object> da : map.entrySet()) {
			temp.put(da.getKey(), Base64Util.decode(da.getValue().toString()));
		}
		return temp;
	}

	public static ArrayList<Map<String, Object>> decodeList(ArrayList<Map<String, Object>> list) {
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			result.add(Base64Util.decodeMap(map));
		}
		return result;
	}

	// 将图片转成base64
	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	// 将base64码转成图片
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void base64ToFile(String base64, String filePath, String fileName) {
		File file = null;
		// 创建文件目录
		File dir = new File(filePath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		try {
			byte[] bytes = Base64.getDecoder().decode(base64);
			file = new File(filePath + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Base64Util.decode("eG1s5qC85byP6ZSZ6K+v"));
	}

//	public static void main(String[] args) {
//		String img = Base64Util.GetImageStr("/Users/liuxiuqi/Desktop/杨宇.jpg");
//		System.out.println(img);
//		Base64Util.GenerateImage(img, "/Users/liuxiuqi/Desktop/杨宇1.jpg");
//	}
}
