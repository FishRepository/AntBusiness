package com.api.common.utils;


import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import net.sourceforge.pinyin4j.PinyinHelper;

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

	public static boolean isChinese(String str){
		if(StrUtil.isEmpty(str)){
			return false;
		}
		if(str.length()==1){
			return PinyinUtil.isChinese(str.charAt(0));
		}
		String[] split = str.split("");
		for (String ch : split) {
			if(!PinyinUtil.isChinese(ch.charAt(0))){
				return false;
			}
		}
		return true;
	}

	private static boolean isPingYin(String str) {
		String[] split = str.split("");
		for (String ch : split) {
			if(!CharUtil.isLetter(ch.charAt(0))){
				return false;
			}
		}
		return true;
	}

	public static boolean bookNameEquals(String queryString, String bookName){
		if(isChinese(queryString)){
			return bookName.contains(queryString);
		}
		if(queryString.length()==1){
			if(Character.isLetter(queryString.charAt(0))){
				char queryStringCh = queryString.charAt(0);
				char firstLetter = PinyinUtil.getFirstLetter(bookName.charAt(0));
				return ObjectUtil.equal(Character.toUpperCase(queryStringCh), Character.toUpperCase(firstLetter));
			}
			return bookName.contains(queryString);
		}
		if(isPingYin(queryString)){
			String pinyin = PinyinUtil.getPinyin(bookName);
			return pinyin.contains(queryString);
		}
		return false;
	}

	public static void main(String[] args) {
		String pinyin = PinyinUtil.getPinyin("123123");
		char firstLetter = PinyinUtil.getFirstLetter("王国".charAt(0));
		System.out.println(isPingYin("wangasdasd111"));
	}
}