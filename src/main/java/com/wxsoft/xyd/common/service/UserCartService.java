package com.wxsoft.xyd.common.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.UserCart;

public interface UserCartService {
	int deleteByPrimaryKey(UserCart record);
	//批量删除
	
   int deleteUserCartBetach(int [] ids);
	int insert(UserCart record);

	int insertSelective(UserCart record);

	int updateByPrimaryKeySelective(UserCart record);

	UserCart selectByPrimaryKey(Integer id);

	UserCart selectByUserCart(UserCart record);
    List<Map<String,Object>> getUserCart(UserCart userCart);
	List<UserCart> listPageByUserCart(UserCart clssname);
	//更加用户选择的id获取
     List<Map<String,Object>> getUserCartByids(int [] ids);
	List<UserCart> getAllByUserCart(UserCart clssname);
	//获取当前用户的购物车数量
	int selectCountByUser(Integer userId);
}