/**
 * 公共分类页实现
 */
package com.wxsoft.xyd.system.model;

import com.wxsoft.framework.bean.BaseBean;

/**
 * 
 * @类功能说明：分页公共类声明
 * @类修改者：kasiait
 * @修改日期：2013-3-19
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-19 下午04:07:10
 */
public class CommonPage extends BaseBean {

	private static final long serialVersionUID = -8294642605483418585L;
	private int showCount = 10; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private static String functionName;
	private int tagNum = 5;//中间需要显示的标签数量
	private int defaultTagNum = 2;//当超出标签数量时当前页两边需要显示的数量

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	static {
		functionName = "nextPage";

	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {

			int prePage = currentPage - 1 == 0 ? currentPage : currentPage - 1;
			sb.append("<div class=\"col-md-1 col-sm-1 items-info\" style='float:right;margin-top: 15px'>共"
					+ totalPage
					+ "页</div><div class=\"col-md-11 col-sm-11\"><ul class=\"pagination pull-right\">");
			if (currentPage == 1) {

			} else {
				sb.append("<li><a href=\"#@\" onclick=\"" + functionName + "("
						+ prePage + ")\">«</a></li>\n");
			}

			for (int i = 1; i <= totalPage; i++) {
				if (i == currentPage) {
					sb.append("<li><span style='background-color: #EDEDED;'>" + i + "</span></li>\n");
				} else if (currentPage > tagNum) {// 如果当前页大于中间要显示的标签数，如第6页大于5
					// 显示第6页前两个和第六页后两个,45 78，1 2 必须显示
					if ((i < defaultTagNum + 1)
							|| (i >= currentPage - defaultTagNum && i < currentPage)
							|| (i > currentPage && i <= currentPage
									+ defaultTagNum)) {
						sb.append("<li><a href=\"#@\"  onclick=\""
								+ functionName + "(" + (i) + ")\">" + (i)
								+ "</a></li>\n");
					} else if ((i > defaultTagNum && i == currentPage
							- defaultTagNum - 1)
							|| (i > currentPage + defaultTagNum && i == currentPage
									+ defaultTagNum + 1)) {
						// 需要显示省略号的
						sb.append("<li><a href=\"#@\"  onclick=\"javascript:void(0)\">···</a></li>\n");
					}
				} else if (currentPage <= tagNum) {// 如果当前页小于中间要显示的标签数
					if (i <= tagNum + defaultTagNum) {// 需要显示1-7
						sb.append("<li><a href=\"#@\" onclick=\""
								+ functionName + "(" + (i) + ")\">" + (i)
								+ "</a>\n");
					} else if (i == tagNum + defaultTagNum + 1) {// 大于7需要显示省略号的，显示一个
						sb.append("<li><a href=\"#@\"  onclick=\"javascript:void(0)\">···</a></li>\n");
					}
				}
			}
			int nextPage = currentPage + 1 > totalPage ? currentPage
					: currentPage + 1;
			if (nextPage == currentPage) {

			} else {
				sb.append("<li><a href=\"#@\" class=\"selected2\" onclick=\""
						+ functionName + "(" + nextPage + ")\">»</a></li>\n");

			}

			sb.append("</div></ul></div></div> ");
			//
			// sb.append("	<ul>\n");
			// // System.out.println("currentPage:"+currentPage);
			// if (currentPage == 1) {
			// sb.append("	<li class=\"pageinfo\">首页</li>\n");
			// sb.append("	<li class=\"pageinfo\">上页</li>\n");
			// } else {
			// sb.append("	<li><a href=\"#@\" onclick=\"nextPage(1)\">首页</a></li>\n");
			// sb.append("	<li><a href=\"#@\" onclick=\"nextPage("
			// + (currentPage - 1) + ")\">上页</a></li>\n");
			// }
			// int showTag = 3; // 分页标签显示数量
			// int startTag = 1;
			// if (currentPage > showTag) {
			// startTag = currentPage - 1;
			// }
			// int endTag = startTag + showTag - 1;
			// for (int i = startTag; i <= totalPage && i <= endTag; i++) {
			// if (currentPage == i)
			// sb.append("<li class=\"current\">" + i + "</li>\n");
			// else
			// sb.append("	<li><a href=\"#@\" onclick=\"nextPage(" + i
			// + ")\">" + i + "</a></li>\n");
			// }
			// if (currentPage == totalPage) {
			// sb.append("	<li class=\"pageinfo\">下页</li>\n");
			// sb.append("	<li class=\"pageinfo\">尾页</li>\n");
			// } else {
			// sb.append("	<li><a href=\"#@\" onclick=\"nextPage("
			// + (currentPage + 1) + ")\">下页</a></li>\n");
			// sb.append("	<li><a href=\"#@\" onclick=\"nextPage(" + totalPage
			// + ")\">尾页</a></li>\n");
			// }
			// sb.append("	<li class=\"pageinfo\">第" + currentPage +
			// "页</li>\n");
			// sb.append("	<li class=\"pageinfo\">共" + totalPage + "页</li>\n");
			// sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){");

			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}}\n");
			sb.append("		else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		document.forms[0].action = url+page;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

}
