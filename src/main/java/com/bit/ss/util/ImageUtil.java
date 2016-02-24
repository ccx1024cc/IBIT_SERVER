package com.bit.ss.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.imgscalr.Scalr;

import com.bit.ss.exception.OuterException;

import static org.imgscalr.Scalr.*;

/**   
 * @Title: ImageUtil.java 
 * @Package com.bit.ss.util 
 * @Description:  图像辅助类
 * @author CCX
 * @date 2016年1月4日 上午10:42:43 
 * @version V1.0   
 */
public class ImageUtil {

	/**
	 * 
	 * @Title: createThumbnailByRatio 
	 * @Description: 等比缩放
	 * @return BufferedImage    返回类型 
	 * @throws
	 */
	public BufferedImage createThumbnail(BufferedImage img, int ratio) {
		// ratio.eg=125
		img = resize(img, Method.SPEED, ratio, OP_ANTIALIAS, OP_BRIGHTER);
		return pad(img, 4);
	}

	/**
	 * 
	 * @Title: createThumbnailFixedWidth 
	 * @Description: 宽适应缩放
	 * @return BufferedImage    返回类型 
	 * @throws
	 */
	public BufferedImage createThumbnailFixedWidth(BufferedImage img, int width, int height) {
		BufferedImage thumbnail = Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH, height, width,
				Scalr.OP_ANTIALIAS);
		return thumbnail;
	}

	/**
	 * 
	 * @Title: createThumbnailFixedWidth 
	 * @Description: 高适应缩放
	 * @return BufferedImage    返回类型 
	 * @throws
	 */
	public BufferedImage createThumbnailFixedHeight(BufferedImage img, int width, int height) {
		BufferedImage thumbnail = Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT, height, width,
				Scalr.OP_ANTIALIAS);
		return thumbnail;
	}

	/**
	 *
	 * @Title: saveImage 
	 * @Description: 保存图片
	 * @return String    保存的路径/文件名 
	 * @throws
	 */
	public String saveImage(FormDataBodyPart body, String path) throws IOException {
		ContentDisposition info = body.getContentDisposition();
		String filename = info.getFileName();
		if (filename == null || filename.equals(""))
			return null;
		String format = getFormat(filename);
		if (format.equals("jpeg") || format.equals("gif") || format.equals("png") || format.equals("jpg")) {
			String newFilename = UUID.randomUUID() + "." + format;
			InputStream image = body.getValueAs(InputStream.class);
			FileUtils.copyInputStreamToFile(image, new File(path + File.separator + newFilename));
			return path + File.separator + newFilename;
		} else {
			throw new OuterException(Status.NOT_ACCEPTABLE, OuterException.ICON_FORMAT_UNACCEPTABLE);
		}
	}

	/**
	 * 
	 * @Title: getFormat 
	 * @Description: 通过文件名得到格式
	 * @return String    返回文件格式
	 * @throws
	 */
	private String getFormat(String filename) {
		String[] temp = filename.split("\\.");
		return temp[temp.length - 1];
	}

	public void createThumbnailFixedWidth(String orignPath, String newPath, int width, int height) throws IOException {
		BufferedImage imageOrign = ImageIO.read(new File(orignPath));
		String format = orignPath.substring(orignPath.lastIndexOf(".") + 1);
		ImageIO.write(createThumbnailFixedWidth(imageOrign, width, height), format, new File(newPath));
	}

}
