package com.bzfar.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.encoding.Base64;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QRCodeUtil {

	/**
	 *
	 * 生成二维码文件
	 * 
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 * @param content 内容
	 * @param
	 * @see [类、类#方法、类#成员]
	 */
	public static String generatEncode(String filePath, String fileName, String content) {

		int width = 200; // 图像宽度
		int height = 200; // 图像高度
		String format = "png";// 图像类型

		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
			String path = FileSystems.getDefault().getPath(filePath, fileName + "." + format).toString();
			File file = new File(path);
			MatrixToImageWriter.writeToFile(bitMatrix, format, file);// 输出图像
			log.info("二维码地址：" + filePath + fileName + "." + format);
			return "/file?num=" + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("二维码输出异常");
			return null;
		}
	}

	/**
	 *
	 * 解析二维码内容
	 * 
	 * @param filePath 二维码绝对路径
	 * @see [类、类#方法、类#成员]
	 */
	public static String parseDecode(String filePath) {
		
		BufferedImage image;
		try {
			image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			JSONObject content = new JSONObject(result.getText());

			return content.toString();
		} catch (Exception e) {
			log.error("二维码输出异常");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * 生成二维码输出流 在jsp页面中直接展示时使用 无须保存 即生成即展示
	 * @param response
	 * @param content 内容
	 * @see [类、类#方法、类#成员]
	 */
	public static void generatEncode(HttpServletResponse response, String content) {

		int width = 200; // 图像宽度
		int height = 200; // 图像高度
		String format = "png";// 图像类型
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
			MatrixToImageWriter.writeToStream(bitMatrix, format, response.getOutputStream());// 输出图像
			log.info("二维码输出成功");
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("二维码输出异常");
		}
	}
	
	/**
	 *
	 * 生成二维码Base64
	 * 
	 * @param content 内容
	 * @param
	 * @see [类、类#方法、类#成员]
	 */
	public static String generatBase64Encode(String content) {

		int width = 200; // 图像宽度
		int height = 200; // 图像高度

       ByteArrayOutputStream os = new ByteArrayOutputStream();
       @SuppressWarnings("rawtypes")
       HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
       hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 指定字符编码为“utf-8”
       hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 指定二维码的纠错等级为中级
       hints.put(EncodeHintType.MARGIN, 2); // 设置图片的边距

       try {
       	QRCodeWriter writer = new QRCodeWriter();
       	BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
       	BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
       	ImageIO.write(bufferedImage, "png", os);
			/**
	         * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析，可以让前端加，也可以在下面加上
	         */
	        String resultImage = new String("data:image/png;base64," + Base64.encode(os.toByteArray()));
	        return resultImage;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("二维码输出异常");
			return null;
		}
	}
}
