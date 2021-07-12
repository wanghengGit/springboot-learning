package com.kit.demo.util;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;

public class ValidateCode {
	/**
	 * 验证码难度级别 Simple-数字 Medium-数字和小写字母 Hard-数字和大小写字母
	 */
	private enum SecurityCodeLevel {
		Simple, Medium, Hard
	};

	/**
	 * 产生默认验证码，4位中等难度
	 * 
	 * @return
	 */
	public static String getSecurityCode() {
		return getSecurityCode(4, SecurityCodeLevel.Medium, false);
	}

	/**
	 * 产生长度和难度任意的验证码
	 * 
	 * @param length
	 * @param level
	 * @param isCanRepeat
	 * @return
	 */
	private static String getSecurityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
		// 随机抽取len个字符
		int len = length;
		// 字符集合（--除去易混淆的数字0,1,字母l,o,O）
		char[] codes = { 
				'2', '3', '4', '5', '6', '7', '8', '9', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' 
		};
		// 根据不同难度截取字符串
		if (level == SecurityCodeLevel.Simple) {
			codes = Arrays.copyOfRange(codes, 0, 10);
		} else if (level == SecurityCodeLevel.Medium) {
			codes = Arrays.copyOfRange(codes, 0, 36);
		}
		// 字符集和长度
		int n = codes.length;
		// 抛出运行时异常
		if (len > n && isCanRepeat == false) {
			throw new RuntimeException(String.format("调用ValidateCode.getSecurityCode(%1$s,%2$s,%3$s)出现异常，" + "当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s", len, level, isCanRepeat, n));
		}
		// 存放抽取出来的字符
		char[] result = new char[len];
		// 判断能否出现重复字符
		if (isCanRepeat) {
			for (int i = 0; i < result.length; i++) {
				// 索引0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为code[r]存放的数值
				result[i] = codes[r];
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				// 索引0 and n-1
				int r = (int) (Math.random() * n);
				// 将result中的第i个元素设置为code[r]存放的数值
				result[i] = codes[r];
				// 必须确保不会再次抽取到那个字符，这里用数组中最后一个字符改写code[r],并将n-1
				codes[r] = codes[n - 1];
				n--;
			}
		}
		return String.valueOf(result);
	}
	
	
	

	/**
	 * 生成验证码图片
	 * @param securityCode
	 * @return
	 */
	private static BufferedImage createImage(String securityCode){
		
		int codeLength = securityCode.length();//验证码长度
		int fontSize = 20;//字体大小
		// int fontWidth = fontSize+1;
		
		//图片宽高
//		int width = codeLength*fontWidth+6;
		int width = 80;
//		int height = fontSize*2+1;
		int height = 42;
		//图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.setColor(Color.WHITE);//设置背景色
		g.fillRect(0, 0, width, height);//填充背景
		g.setColor(Color.LIGHT_GRAY);//设置边框颜色
		g.setFont(new Font("Arial", Font.BOLD, height-2));//边框字体样式
		g.drawRect(0, 0, width-1, height-1);//绘制边框
		
		//绘制噪点
		SecureRandom rand = new SecureRandom();
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < codeLength*6; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawRect(x, y, 1, 1);//绘制1*1大小的矩形
		}
		//绘制验证码
		int codeY = height-13;
		g.setColor(new Color(19,148,246));
		g.setFont(new Font("Georgia", Font.BOLD, fontSize));
		for(int i=0;i<codeLength;i++){
			g.drawString(String.valueOf(securityCode.charAt(i)), i*16+8, codeY);
		}
		g.dispose();//关闭资源
		return image;
	}
	
	/***
	 * 返回验证码的图片对象
	 * @param securityCode
	 * @return
	 */
	public static BufferedImage getImage(String securityCode){
		return createImage(securityCode);
	}
	
	/**
	 * 返回验证码图片的流格式
	 * @param securityCode
	 * @return
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode){
		BufferedImage image = createImage(securityCode);
		return convertImageToStream(image);
	}
	
	/**
	 * 将BufferedImage转换成ByteArrayinputStream
	 * @param image
	 * @return
	 */
	private static ByteArrayInputStream convertImageToStream(BufferedImage image){
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", outStream);
			outStream.flush();
			byte[] b = outStream.toByteArray();
			inputStream = new ByteArrayInputStream(b);
		} catch (IOException e) {
//			LogTextUtils logger = LogTextUtils.getLogger();
//			logger.wirteError(e,new String[]{"\rValidateCode的异常"});
		}
		return inputStream;
	}
}
