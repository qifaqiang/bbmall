package com.wxsoft.framework.dao;

import java.util.Map;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.framework.bean.PageBean;


/**
 * 
 * @author kasiait
 *
 */
public interface BaseDaoInterface {
	/**
	 * 添加
	 * @param baseBean 要添加的实体
	 * @return 生成的实体id
	 */
	public boolean add(BaseBean baseBean);

	/**
	 * 刪除实体
	 * @param persistBean 要删除的实体
	 */
	public boolean delete(BaseBean baseBean);
	
	/**
	 * 根据编号删除
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * 根据参数查询实体
	 * @param <T>
	 * @param param
	 * @return
	 */
	public BaseBean queryByParam(Map<String, Object> param);

	/**
	 * 通过id查询实体
	 * 
	 * @param id 实体id
	 * @return 实体
	 */
	public  BaseBean queryById(String id);

	/**
	 * 更新实体
	 * 
	 * @param baseBean 要更新的实体
	 */
	public boolean update(BaseBean baseBean);
	
	/**
	 * 记录总数
	 * 
	 * @return
	 */
	public int getAllCount();

	/**
	 * 分页
	 * @param <T>
	 * 
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageBean getByPageNo(int pageNo);
}
