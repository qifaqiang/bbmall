/**   
 * @文件名称: JsonBean.java
 * @类路径: com.wxsoft.framework.bean
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-15 上午09:39:04  
 */

package com.wxsoft.framework.bean;

/**
 * @类功能说明：
 * @类修改者：kasiait
 * @修改日期：2013-3-15
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-3-15 上午09:39:04
 */

public class JsonBean {

	private String retCode;
	private String retMsg;

	public JsonBean() {
		super();
	}

	public JsonBean(String retCode, String retMsg) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Override
	public String toString()
	{
		return "id:" + retCode + "name:" + retMsg;
	}
}
