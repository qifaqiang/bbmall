package com.wxltsoft.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxsoft.framework.util.Tools;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/ApplicationContext.xml" })
public class Tool {
	Logger logger = Logger.getLogger(this.getClass());
	@Inject
	private DataSource dataSource;

	/**
	 * 通过表创建实体类、mapper、Mybatis.xml、service、serviceImpl、sql-config、Application
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void createMain() {
		logger.info("反向工程开始，cpu正在全速运转……");
		String packAge = "com/wxsoft/xyd/quartz/";
		String table = "sys_schedule_job";
		try {
			createAllThing(packAge, table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 母体类
	 * 
	 * @param packAge
	 * @param table
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createAllThing(String packAge, String table)
			throws SQLException, IOException, URISyntaxException {
		String path = System.getProperty("user.dir");// 获取项目根目录
		logger.info("获取项目目录：" + path);
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet pkRSet = dbMeta.getPrimaryKeys(null, null, table);
		String pk_cloum = "";
		while (pkRSet.next()) {
			System.err.println("****** Comment ******");
			System.err.println("TABLE_CAT : " + pkRSet.getObject(1));
			System.err.println("TABLE_SCHEM: " + pkRSet.getObject(2));
			System.err.println("TABLE_NAME : " + pkRSet.getObject(3));
			System.err.println("COLUMN_NAME: " + pkRSet.getObject(4));
			System.err.println("KEY_SEQ : " + pkRSet.getObject(5));
			System.err.println("PK_NAME : " + pkRSet.getObject(6));
			System.err.println("****** ******* ******");
			try {
				pk_cloum = pkRSet.getObject(4).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("------error:请设置主键------");
				e.printStackTrace();
			}
		}

		ResultSet rs2 = stmt.executeQuery("show full COLUMNS from " + table);
		List<String> comments = new ArrayList<String>();
		while (rs2.next()) {
			comments.add(rs2.getString("Comment"));
		}

		ResultSet rs = stmt.executeQuery("select * from " + table
				+ " where 0=1");
		ResultSetMetaData md = rs.getMetaData();
		logger.info("初始化表结构");
		String className = toUpperCaseFirstOne(toProperty(table));// 把表明转成类名
		logger.info("开始生成Class");
		createClass(path, packAge, table, className, md, comments, true, true);// 生成实体类
		logger.info("开始生成mapper.xml");
		createMapperFile(path, packAge, table, className, md, pk_cloum);//
		// 生成mapperx.xml
		logger.info("开始生成MapperClass");
		createMapperClass(path, packAge, table, className);// 生成MapperClass
		logger.info("开始生成ServiceClass");
		createServiceClass(path, packAge, table, className);// 生成ServiceClass
		logger.info("开始生成createServiceImplClass");
		createServiceImplClass(path, packAge, table, className);//
		// 生成createServiceImplClass
		logger.info("成功，请刷新项目");
	}

	/**
	 * 创建MapperClass
	 * 
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createMapperClass(String path, String packAge, String table,
			String className) throws SQLException, IOException,
			URISyntaxException {
		// String path = Tool.class.getResource("/").toString().l;
		path += Common.SRC_JAVA;
		packAge += Common.MAPPER;

		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(Tool.class.getResource("")
				.toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("mapper.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put(
				"PACKAGE_URL",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put(
				"PACKAGE",
				packAge.replaceAll("\\/", ".")
						.substring(0, packAge.length() - 1)
						.replace("mapper", "model")
						+ "." + className + ";");
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", Tools.date2Str(new Date()));
		root.put(
				"CLASSPATH",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1)
						+ "." + className + "Mapper;");

		fileWrite(temp, path + packAge + className + "Mapper.java", root);
	}

	/**
	 * 创建createServiceImplClass
	 * 
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createServiceImplClass(String path, String packAge,
			String table, String className) throws SQLException, IOException,
			URISyntaxException {
		path += Common.SRC_JAVA;
		packAge += Common.SERVICE_IMPL;
		String pageNow = packAge.replaceAll("\\/", ".").substring(0,
				packAge.length() - 1);

		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(Tool.class.getResource("")
				.toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("serviceImpl.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put(
				"PACKAGE_URL",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put(
				"PACKAGE",
				packAge.replaceAll("\\/", ".")
						.substring(0, packAge.length() - 1)
						.replace("service", "model")
						+ "." + className + ";");
		root.put("PACKAGE1",
				pageNow.replace("service", "model").replace(".impl", "") + "."
						+ className + ";");
		root.put("PACKAGE2",
				pageNow.replace("service", "mapper").replace(".impl", "") + "."
						+ className + "Mapper;");
		root.put("PACKAGE3", pageNow.replace(".impl", "") + "." + className
				+ "Service;");
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", Tools.date2Str(new Date()));
		root.put(
				"CLASSPATH",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1)
						+ "." + className + "ServiceImpl;");
		fileWrite(temp, path + packAge + className + "ServiceImpl.java", root);
	}

	/**
	 * 创建Mapper
	 * 
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createServiceClass(String path, String packAge, String table,
			String className) throws SQLException, IOException,
			URISyntaxException {
		// String path = Tool.class.getResource("/").toString().l;
		path += Common.SRC_JAVA;
		packAge += Common.SERVICE;
		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(Tool.class.getResource("")
				.toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("service.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put(
				"PACKAGE_URL",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put(
				"PACKAGE",
				packAge.replaceAll("\\/", ".")
						.substring(0, packAge.length() - 1)
						.replace("service", "model")
						+ "." + className + ";");
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", Tools.date2Str(new Date()));
		root.put(
				"CLASSPATH",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1)
						+ "." + className + "Service;");
		fileWrite(temp, path + packAge + className + "Service.java", root);
	}

	/**
	 * 创建实体类
	 * 
	 * @param path
	 * @param packAge
	 * @param table
	 * @param className
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void createClass(String path, String packAge, String table,
			String className, ResultSetMetaData md, List<String> comments,
			boolean CommonPage, boolean AjaxPage) throws SQLException,
			IOException, URISyntaxException {
		path += Common.SRC_JAVA;
		packAge += Common.MODEL;
		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(Tool.class.getResource("")
				.toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("class.ftl");

		Map<String, Object> root = new HashMap<String, Object>();
		// package
		root.put(
				"PACKAGE_URL",
				packAge.replaceAll("\\/", ".").substring(0,
						packAge.length() - 1));

		// 类注释
		root.put("CLASS", className);
		root.put("PACKAGE", packAge);
		root.put("AUTH_NAME", Common.AUTH_NAME);
		root.put("COMPANY_NAME", Common.COMPANY_NAME);
		root.put("TIME", Tools.date2Str(new Date()));

		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();

		int count = md.getColumnCount();
		for (int i = 1; i <= count; i++) {
			Map<String, String> currency = new HashMap<String, String>();
			currency.put("name", toProperty(md.getColumnName(i)));
			currency.put("type", endP(md.getColumnClassName(i)));
			currency.put("comment", comments.get(i - 1));
			properties.add(currency);
		}
		if (CommonPage) {
			Map<String, String> currency = new HashMap<String, String>();
			currency.put("name", "page");
			currency.put("type", "CommonPage");
			currency.put("comment", "普通分页");
			properties.add(currency);
			// 引包
			root.put(
					"import_URL",
					"import com.wxsoft.framework.bean.BaseBean;\nimport com.wxsoft.xyd.system.model.CommonPage;\n");
		}
		if (AjaxPage) {
			Map<String, String> currency = new HashMap<String, String>();
			currency.put("name", "ajaxPage");
			currency.put("type", "AjaxPage");
			currency.put("comment", "ajax分页");
			properties.add(currency);
			// 引包
			root.put(
					"import_URL",
					"import com.wxsoft.framework.bean.BaseBean;\nimport com.wxsoft.xyd.system.model.CommonPage;\nimport com.wxsoft.xyd.system.model.AjaxPage;\n");
		}
		root.put("properties", properties);

		fileWrite(temp, path + packAge + className + ".java", root);

	}

	/**
	 * 创建映射实体文件Mapp.xml
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	// @Test
	public void createMapperFile(String path, String packAge, String table,
			String className, ResultSetMetaData md, String PK_colum)
			throws SQLException, IOException, URISyntaxException {
		path += Common.SRC_JAVA;
		packAge += Common.MAPPER;
		String selectALl = getSelectAll(table, md);

		/* Create and adjust the configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(Tool.class.getResource("")
				.toURI()));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* Get or create a template */
		Template temp = cfg.getTemplate("mybatis.ftl");

		Map<String, Object> root = new HashMap<String, Object>();

		root.put("selectALl", selectALl.toLowerCase());
		root.put("namespace",
				packAge.replaceAll("\\/", ".").substring(0, packAge.length())
						+ className);
		root.put("table", table.toLowerCase());
		root.put("className", className);
		root.put("PK_colum", PK_colum.toLowerCase());
		root.put("PRIMARY", toProperty(PK_colum));

		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();
		int count = md.getColumnCount();
		for (int i = 1; i <= count; i++) {
			Map<String, String> currency = new HashMap<String, String>();
			currency.put("property", toProperty(md.getColumnName(i)));
			currency.put("column", md.getColumnName(i).toLowerCase());
			properties.add(currency);
		}
		root.put("properties", properties);
		fileWrite(temp, path + packAge + className + ".xml", root);
	}

	/**
	 * 返回查询所有咧
	 * 
	 * @param table
	 * @throws SQLException
	 * @throws IOException
	 */
	public String getSelectAll(String table, ResultSetMetaData md)
			throws SQLException, IOException {
		String result = "";
		int count = md.getColumnCount();
		for (int i = 1; i <= count; i++) {
			result += md.getColumnName(i) + ",";
		}
		return result.substring(0, result.length() - 1);

	}

	public static String firstLow(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static void main(String[] args) {
	}

	public String toProperty(String col) {
		col = col.toLowerCase();
		String[] ss = col.split("_");
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (String s : ss) {
			if (first)
				sb.append(s);
			else
				sb.append(s.substring(0, 1).toUpperCase() + s.substring(1));
			first = false;
		}
		return sb.toString();
	}

	public String endP(String type) {
		if (type.substring(type.lastIndexOf(".") + 1).equals("Timestamp")) {
			return "String";
		} else {
			return type.substring(type.lastIndexOf(".") + 1);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	public String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	public static void fileWrite(Template temp, String filename,
			Map<String, Object> root) {
		File file = new File(filename);
		FileOutputStream out;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new FileOutputStream(filename);
			try {
				temp.process(root, new OutputStreamWriter(out,"utf-8"));
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}}
