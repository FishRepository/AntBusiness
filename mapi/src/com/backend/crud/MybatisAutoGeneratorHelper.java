package com.backend.crud;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MybatisAutoGeneratorHelper {
	public static void generator(final String projectName,final String path,String[] tablePrefix,String[] tableNames,final String moduleName,boolean override){
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(path+"/src/main/java");

		gc.setFileOverride(override);
		gc.setActiveRecord(true);// 开启 activeRecord 模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert());
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		dsc.setUrl("jdbc:mysql://127.0.0.1:3306/fqgj?autoReconnect=true&amp;autoReconnectForPools=true");
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		if(tablePrefix!=null){
			strategy.setTablePrefix(tablePrefix);// 此处可以修改为您的表前缀
		}
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		if(tableNames!=null){
			strategy.setInclude(tableNames); // 需要生成的表
		}
		// 字段名生成策略
		// strategy.setFieldNaming(NamingStrategy.underline_to_camel);
		// strategy.setSuperServiceImplClass("com.baomidou.springwind.service.support.BaseServiceImpl");
		
//		strategy.setSuperControllerClass("com.jd.luban.base.CURDController");
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		// pc.setModuleName(rb.getString("modulename"));
		pc.setParent("com.jd.luban");// 自定义包路径
		pc.setController("controller." + moduleName);// 这里是控制器包名，默认 web
		pc.setEntity("domain." + moduleName);
		pc.setMapper("dao." + moduleName);
		pc.setXml("dao." + moduleName);
		pc.setService("service." + moduleName);
		pc.setServiceImpl("service." + moduleName + ".impl");
		mpg.setPackageInfo(pc);

		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
                map.put("requestMapping", moduleName);
                this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		// 调整 xml 生成目录演示
		focList.add(new FileOutConfig("/templates/controller.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				File f = new File(path);
				return f.getParent()+"/"+projectName+"/src/main/java/com/jd/luban/controller/" + moduleName + "/" + tableInfo.getEntityName() + "Controller.java";
			}
		});
		cfg.setFileOutConfigList(focList);
		// 调整 xml 生成目录演示
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return path+"/src/main/resources/mybatis/" + moduleName + "/" + tableInfo.getEntityName() + "Mapper.xml";
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		// 关闭默认 生成，调整生成 至 根目录
		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		tc.setController(null);
		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
	}

}
