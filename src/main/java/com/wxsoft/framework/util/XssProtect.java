package com.wxsoft.framework.util;

public class XssProtect {

	public static String protectAgainstltAndGt(String html) {

		String text = null;
		if (html != null && !html.equals("")) {
			text = html.replace("<", "&lt").replace(">", "&gt");
		}
		return text;
	}

	public static void main(String[] args) {
		String html = "ffff<script>alert(5)</script>sdfsdfsdf";
		String v = protectAgainstltAndGt(html);
		System.out.println(v);

	}

	public static String protectAgainstXSS(String html) {

		// String text = null;
		// if (html != null && !html.equals("")) {
		// StringReader reader = new StringReader(html);
		// StringWriter writer = new StringWriter();
		// try {
		// // Parse incoming string from the "html" variable
		// HTMLParser.process(reader, writer, new XSSFilter(), true);
		// // Return the parsed and cleaned up string
		// text = writer.toString();
		// } catch (Exception e) {
		// String res = html.substring(0, html.indexOf("<"));
		// String reslast = html.substring(html.indexOf("<"), html.length());
		// try {
		// reader = new StringReader(reslast);
		// HTMLParser.process(reader, writer, new XSSFilter(), true);
		// System.out.println(writer.toString());
		// // Return the parsed and cleaned up string
		// text = res + writer.toString();
		// } catch (HandlingException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// } finally {
		// try {
		// writer.close();
		// reader.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		return html;
	}

}
