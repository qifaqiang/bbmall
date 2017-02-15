/**   
 * @文件名称: Company.java
 * @类路径: com.wxltsoft.fxshop.common.model
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-29 下午04:47:05  
 */
package com.wxsoft.xyd.system.model;

import java.util.List;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.common.model.CompanyStock;

/**
 * @类功能说明：公司
 * @类修改者：kyz
 * @修改日期：2013-8-2 @修改说明： @公司名称：kyz
 * @作者：kyz
 * @创建时间：2013-8-2 上午08:29:05
 */

public class ComStock extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyName;                 //基地商户名称
	private Integer companyId;
	private List<CompanyStock>  companyStock;         //库存列表
	
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public List<CompanyStock> getCompanyStock() {
		return companyStock;
	}
	public void setCompanyStock(List<CompanyStock> companyStock) {
		this.companyStock = companyStock;
	}
	private CommonPage page;                    //分页
	public CommonPage getPage() {
		return page;
	}
	public void setPage(CommonPage page) {
		this.page = page;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}