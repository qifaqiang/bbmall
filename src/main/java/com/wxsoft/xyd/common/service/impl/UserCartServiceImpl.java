package com.wxsoft.xyd.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.UserCartMapper;
import com.wxsoft.xyd.common.model.UserCart;
import com.wxsoft.xyd.common.service.UserCartService;
import com.wxsoft.xyd.order.mapper.OrdersMapper;
import com.wxsoft.xyd.prod.mapper.ProductMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoMapper;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;

@Service("userCartService")
public class UserCartServiceImpl implements UserCartService {
	@Autowired
	private UserCartMapper userCartMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductSpecificationInfoMapper productSpecificationInfoMapper;
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public int deleteByPrimaryKey(UserCart record) {
		return userCartMapper.deleteByPrimaryKey(record);
	}

	@Override
	public int insert(UserCart record) {
		Product p = productMapper.selectProductByCart(record.getProdId());
		UserCart uc = userCartMapper.selectByUserCart(record);
		if (null != uc) {// 判断以前是否加入过购物车
			if (null != record.getSpecId() && record.getSpecId() != 0) {// 存在规格
				ProductSpecificationInfo psi = new ProductSpecificationInfo();
				psi.setId(record.getSpecId());
				psi = productSpecificationInfoMapper
						.selectByProductSpecificationInfo(psi);
				if (null != psi) {
					if ((record.getCount()) > psi.getInventorynumber()) {// 如果购买数量大于库存返回失败
						return -1;
					} else {
						uc.setCount(record.getCount());
						userCartMapper.updateByPrimaryKeySelective(uc);
						return uc.getId();
					}
				}
			} else {
				if ((record.getCount()) > p.getInventorynumber()) {// 如果购买数量大于库存返回失败
					return -1;
				} else {
					if (p.getType() == 1) {// 预售商品数量只能是1
						Map<String, String> maps = new HashMap<String, String>();
						maps.put("prodId", p.getId() + "");
						maps.put("userId", record.getUserId() + "");
						if (ordersMapper.selectByUserAndProductId(maps) > 0) {
							return 0;
						}
						uc.setCount(1);
					} else {// 非预售商品，直接改数量
						uc.setCount(record.getCount());
					}
					userCartMapper.updateByPrimaryKeySelective(uc);
					return uc.getId();
				}
			}
		} else {
			userCartMapper.insertSelective(record);
			return record.getId();
		}
		return 0;

	}

	@Override
	public int insertSelective(UserCart record) {
		Product p = productMapper.selectProductByCart(record.getProdId());

		if (null != record.getSpecId() && record.getSpecId() != 0) {// 存在规格
			ProductSpecificationInfo psi = new ProductSpecificationInfo();
			psi.setId(record.getSpecId());
			psi = productSpecificationInfoMapper
					.selectByProductSpecificationInfo(psi);
			if (null != psi) {
				UserCart uc = userCartMapper.selectByUserCart(record);
				if (null != uc) {// 判断以前是否加入过购物车
					if ((uc.getCount() + record.getCount()) > psi
							.getInventorynumber()) {// 如果购买数量大于库存返回失败
						return -1;
					} else {
						uc.setCount(uc.getCount() + record.getCount());
						return userCartMapper.updateByPrimaryKeySelective(uc);
					}
				} else {
					return userCartMapper.insertSelective(record);
				}
			}
		} else {// 不存在规格
			UserCart uc = userCartMapper.selectByUserCart(record);
			if (null != uc) {// 判断以前是否加入过购物车
				if ((uc.getCount() + record.getCount()) > p
						.getInventorynumber()) {// 如果购买数量大于库存返回失败
					return -1;
				} else {
					uc.setCount(uc.getCount() + record.getCount());
					return userCartMapper.updateByPrimaryKeySelective(uc);
				}
			} else {
				return userCartMapper.insertSelective(record);
			}
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(UserCart record) {
		return userCartMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserCart selectByPrimaryKey(Integer id) {
		return userCartMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserCart selectByUserCart(UserCart record) {
		return userCartMapper.selectByUserCart(record);
	}

	@Override
	public List<UserCart> listPageByUserCart(UserCart clssname) {
		return userCartMapper.listPageByUserCart(clssname);
	}

	@Override
	public List<UserCart> getAllByUserCart(UserCart clssname) {
		return userCartMapper.getAllByUserCart(clssname);
	}

	@Override
	public List<Map<String, Object>> getUserCart(UserCart userCart) {
		// TODO Auto-generated method stub
		return userCartMapper.getUserCart(userCart);
	}

	@Override
	public List<Map<String,Object>> getUserCartByids(int[] ids) {
		// TODO Auto-generated method stub
		return userCartMapper.getUserCartByids(ids);
	}

	@Override
	public int selectCountByUser(Integer userId) {
		// TODO Auto-generated method stub
		return userCartMapper.selectCountByUser(userId);
	}

	@Override
	public int deleteUserCartBetach(int[] ids) {
		// TODO Auto-generated method stub
		return userCartMapper.deleteUserCartBetach(ids);
	}


}