/**   
 * @文件名称: PageBean.java
 * @类路径: com.kasiait.framework.bean
 * @描述: TODO
 * @作者：kasiait
 * @时间：2013-2-5 上午10:06:58  
 */

package com.wxsoft.framework.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能说明：
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：wxlt
 * @作者：kasiait
 * @邮箱：kasiaits@gmail.com
 * @创建时间：2013-2-5 上午10:06:58
 */

public class PageBean<T> {

	// 总记录数
	private int totalRows;

	// 每页记录数
	private int pageSize;

	// 当前页数
	private int currentPage;

	// 总页数
	private int totalPages;

	// 显示记录集
	private List<T> dispList;

	/**
	 * 构造函数
	 * 
	 * @param totalRows
	 *            总记录数
	 * @param pageSize
	 *            页数
	 * @param currentPage
	 *            当前页数
	 */
	public PageBean(int totalRows, int pageSize, int currentPage) {
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		this.currentPage = currentPage;
	}

	/**
	 * 构造函数
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param countHQL
	 *            count语句
	 * @param listHQL
	 *            查询语句
	 */
	public PageBean(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 验证是否是合法的页数
	 * 
	 * @return 是/否 合法
	 */
	public boolean isValidPage() {
		if (this.totalPages > 0
				&& (this.currentPage < 1 || this.currentPage > this.totalPages)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 取得开始记录
	 * 
	 * @return 当页开始记录
	 */
	public int getStartRow() {
		return (currentPage - 1) * pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalRows
	 *            总记录数
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * 判断是否有下一页
	 * 
	 * @return 是/否有下一页
	 */
	public boolean isHasNextPage() {
		return (this.currentPage < this.totalPages);
	}

	/**
	 * 判断是否有上一页
	 * 
	 * @return 是/否有上一页
	 */
	public boolean isHasPreviousPage() {
			return (currentPage > 1);
	}

	/**
	 * 指定页码为首页
	 */
	public void first() {
		currentPage = 1;
	}

	/**
	 * 指定页码为当前页的上一页
	 */
	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
	}

	/**
	 * 指定页码为当前页的下一页
	 * 
	 */
	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
	}

	/**
	 * 指定页码为末页
	 */
	public void last() {
		currentPage = totalPages;
	}

	/**
	 * 处理当前页码大于总页数的情况，如果当前页码大于总页数，则指定页码为末页
	 * 
	 * @param _currentPage
	 *            当前页码
	 */
	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			last();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getDispList() {
		if (null == this.dispList) {
			return new ArrayList();
		}
		return dispList;
	}

	public void setDispList(List<T> dispList) {
		this.dispList = dispList;
	}

	public int getNextPage() {
		return this.currentPage + 1;
	}

	public int getPreviousPage() {
		return this.currentPage - 1;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * 
	 * @描述: 
	 * @作者: kasiait
	 * @邮箱：kasiaits@gmail.com
	 * @日期:2013-2-5
	 * @修改内容
	 * @参数： @return    
	 * @return boolean   
	 * @throws
	 */
	public boolean getHasNextPage() {
		return currentPage < totalPages;
	}

	/**
	 * 
	 * @描述: 
	 * @作者: kasiait
	 * @邮箱：kasiaits@gmail.com
	 * @日期:2013-2-5
	 * @修改内容
	 * @参数： @return    
	 * @return boolean   
	 * @throws
	 */
	public boolean getHasPavPage() {
		return currentPage > 1;
	}

}



