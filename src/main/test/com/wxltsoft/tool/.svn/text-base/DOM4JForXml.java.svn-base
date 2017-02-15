package com.wxltsoft.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOM4JForXml {

	public static void main(String[] args) {
		// System.out.println(44);
		// DOM4JForXml handleXml = new DOM4JForXml();
		// String path = System.getProperty("user.dir");
		// String packAge = "com/wxltsoft/schoolmgr/extra/";
		// path += "/src/resources/mybatis/sqlmap-config.xml";
		// // 解析
		// try {
		// handleXml.paseXml(path,"55","222");
		// } catch (TransformerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	/**
	 * 获得doc对象
	 * 
	 * @param fileName
	 * @return
	 */
	public Document getDocument(String fileName) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

	/**
	 * 解析
	 * 
	 * @param fileName
	 * @throws TransformerException
	 */
	public void paseMyBatitsXml(String fileName, String className,
			String pageAge) throws TransformerException {
		fileName += "/src/resources/mybatis/sqlmap-config.xml";
		Document document = getDocument(fileName);
		Element tempElement = document.createElement("typeAlias");
		tempElement.setAttribute("type", pageAge + "." + className);
		tempElement.setAttribute("alias", className);
		document.getElementsByTagName("typeAliases").item(0)
				.appendChild(tempElement);

		Element tempElement2 = document.createElement("mapper");
		tempElement2.setAttribute("resource", "mybatis/" + className + ".xml");
		document.getElementsByTagName("mappers").item(0)
				.appendChild(tempElement2);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer tfer = tf.newTransformer();
		DOMSource dsource = new DOMSource(document);
		StreamResult sr = new StreamResult(new File(fileName));
		tfer.transform(dsource, sr);

		// String encoding = "utf-8";
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			// 一次读多个字节
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(fileName));
			String tempString = null;
			// int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				sb.append(tempString + "\n");
				// line++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		String doctype = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD SQL Map Config 3.0//EN\"  \"http://mybatis.org/dtd/mybatis-3-config.dtd\">";
		String result = doctype
				+ sb.toString().substring(
						sb.toString().indexOf("<configuration"),
						sb.toString().length());

		FileOutputStream out;
		try {
			out = new FileOutputStream(fileName);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write(result);
			bw.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解析
	 * 
	 * @param fileName
	 * @throws TransformerException
	 */
	public void paseSpringXml(String fileName, String className, String pageAge)
			throws TransformerException {
		fileName += "/src/resources/spring/ApplicationContext-service.xml";
		Document document = getDocument(fileName);
		Element tempElement = document.createElement("bean");
		tempElement.setAttribute("id", Tool.toLowerCaseFirstOne(className)
				+ "Service");
		tempElement.setAttribute("class", pageAge + "." + className
				+ "ServiceImpl");
		Element tempElementChild = document.createElement("property");
		tempElementChild.setAttribute("name",
				Tool.toLowerCaseFirstOne(className) + "Mapper");
		tempElementChild.setAttribute("ref",
				Tool.toLowerCaseFirstOne(className) + "Mapper");
		tempElement.appendChild(tempElementChild);
		document.getElementsByTagName("beans").item(0).appendChild(tempElement);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer tfer = tf.newTransformer();
		DOMSource dsource = new DOMSource(document);
		StreamResult sr = new StreamResult(new File(fileName));
		tfer.transform(dsource, sr);
	}

}
