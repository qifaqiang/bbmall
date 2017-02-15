package com.wxsoft.xyd.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.common.model.UserCart;

/**
 * @文件名称: UserCartMapper.java
 * @类路径: com/wxltsoft/schoolmgr/extra/mapper/
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-02-20 09:32:40
 */
@Repository
public interface UserCartMapper {

	List<UserCart> selectByIsCart(Map<String, Object> maps);

	// 根据主键删除
	int deleteByPrimaryKey(UserCart record);
	//批量删除
	
		int deleteUserCartBetach(int [] ids);
	// 全部插入
	int insert(UserCart record);

	// 根据所需插入
	int insertSelective(UserCart record);

	// 根据所需更新
	int updateByPrimaryKeySelective(UserCart record);

	// 根据主键查询
	UserCart selectByPrimaryKey(Integer id);

	// 根据所需查询
	UserCart selectByUserCart(UserCart record);
	//更加用户选择的id获取
		List<Map<String,Object>> getUserCartByids(int [] ids);
    //根据用户id
	List<Map<String,Object>> getUserCart(UserCart userCart);
	// 分页获取
	List<UserCart> listPageByUserCart(UserCart clssname);

	// 获取全部
	List<UserCart> getAllByUserCart(UserCart clssname);
	
	//获取当前用户的购物车数量
	int selectCountByUser(Integer userId);
}