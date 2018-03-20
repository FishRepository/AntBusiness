package com.api.common.utils;


public class StringUtil {

	public static boolean isValid(String inStr) {
		if (inStr == null) {
			return false;
		} else if ("".equals(inStr)) {
			return false;
		} else if ("null".equals(inStr)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isEmpty(String str) {
		if (str == null){
			return true;
		}else{
			return "".equals(str.trim());
		}
	}
}