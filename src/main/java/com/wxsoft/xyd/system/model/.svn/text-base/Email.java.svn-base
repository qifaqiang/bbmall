package com.wxsoft.xyd.system.model;


import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.wxsoft.framework.util.Tools;

/**
 * 说明:<br>
 * 
 * @author 匡亚洲
 * @version Build Time Jul 24, 2009
 */
public class Email implements Serializable {

	private static final long serialVersionUID = 9063903350324510652L;

	/** 收件人 **/
	private String addressee;

	/** 抄送给 **/
	private String cc;

	/** 邮件主题 **/
	private String subject;

	/** 邮件内容 **/
	private String content;

	/** 附件 **/
	private MultipartFile[] attachment = new MultipartFile[0];

	// ////////////////////////解析邮件地址//////////////////////////////

	public String[] getAddress() {
		if (Tools.isEmpty(this.addressee)) {
			return null;
		}
		addressee = addressee.trim();
		addressee.replaceAll("；", ";");
		addressee.replaceAll(" ", ";");
		addressee.replaceAll(",", ";");
		addressee.replaceAll("，", ";");
		addressee.replaceAll("|", ";");
		return addressee.split(";");
	}

	// ///////////////////////////Getter &&
	// Setter///////////////////////////////
	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile[] getAttachment() {
		return attachment;
	}

	public void setAttachment(MultipartFile[] attachment) {
		this.attachment = attachment;
	}

}