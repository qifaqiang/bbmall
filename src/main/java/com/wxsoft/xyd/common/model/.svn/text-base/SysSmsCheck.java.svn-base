package com.wxsoft.xyd.common.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;

import java.util.Date;

/**
 * @文件名称: SysSmsCheck.java
 * @类路径: com/wxsoft/xyd/common/model/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft @时间：2015-07-03 17:22:57
 */
public class SysSmsCheck extends BaseBean {
	private static final long serialVersionUID = 1L;
	private CommonPage page;
	private Integer id;// 主键
	private String mobile;// 手机号
	private String code;// 验证码
	private Date addtime;// 录入时间1分钟之内不允许重发
	private int count;// 1分钟内发送次数 10条锁账号 1分钟后解锁

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}