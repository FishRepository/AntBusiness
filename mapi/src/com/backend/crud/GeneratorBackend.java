package com.backend.crud;

public class GeneratorBackend extends MybatisAutoGeneratorHelper{

	public static void main(String[] args) {
		final String path=("/Users/qizhihang/gitspace/mappers");
		String[] tablePrefix = new String[] { "" };
		String[] tableNames = new String[] { "ordergoods" };
		String moduleName = "ordergoods";
		boolean override=true;
		generator("mappers",path,tablePrefix,tableNames,moduleName,override);
	}
}
