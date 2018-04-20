package com.backend.crud;

public class GeneratorPortal extends MybatisAutoGeneratorHelper{
	public static void main(String[] args) {
		final String path=System.getProperty("user.dir");
		String[] tablePrefix = new String[] { "popsicle_t_" };
		String[] tableNames = new String[] { "popsicle_t_user","popsicle_t_role","popsicle_t_resource"};
		String moduleName = "security";
		boolean override=false;
		generator("luban-web-portal",path,tablePrefix,tableNames,moduleName,override);
	}
}
