package com.backend.common;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Img64Util {
	public static byte[] GenerateImage(String img64Str) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = null;
		try {
			bytes = decoder.decodeBuffer(img64Str);
			for (int i = 0; i < bytes.length; i++) {
				if (bytes[i]<0) {
					bytes[i]+=256;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	public static String imgUrl(String img64Str, String path, String fileName, String urlPath) throws IOException {
        hasFile(path);
		String y = Calendar.getInstance().get(Calendar.YEAR)+"";
		String m = Calendar.getInstance().get(Calendar.MONTH)+ 1 +"";
		String timeString = y+m;
		path += timeString + "/";
		hasFile(path);
		String pathString;
		String file = StringUtils.isNotBlank(fileName)?imgName(fileName):new Date().getTime() + ".jpg";
		FileOutputStream os = new FileOutputStream(path + file);
		pathString = urlPath + timeString + "/" +file;
		os.write(GenerateImage(img64Str));
		os.flush();
		os.close();
		return pathString;
	}
	private static String imgName(String n){
		return MD5Util.string2MD5(n) + ".jpg";
	}
	private static void hasFile(String filename){
		File file =new File(filename);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
	}
}
