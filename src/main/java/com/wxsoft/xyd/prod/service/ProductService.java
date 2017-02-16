package com.wxsoft.xyd.prod.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.prod.model.Product;

public interface ProductService {
	int deleteByPrimaryKey(Product record);

	int insert(Product record);

	int insertSelective(Product record, String[] values,
			Integer[] inventorynumbers, BigDecimal[] marketPrices,
			BigDecimal[] prices, Integer[] skus, String[] prodspec_ck,
			String proBasicCount,Map specInfoDetalMap,String[] prod_sprc_ids);

	int updateByPrimaryKeySelective(Product record, String[] values,
			Integer[] inventorynumbers, BigDecimal[] marketPrices,
			BigDecimal[] prices, Integer[] skus, String[] prodspec_ck,
			String proBasicCount,Map specInfoDetalMap,String[] prod_sprc_ids);

	int updateByPrimaryKeySelective(Product record);

	Product selectByPrimaryKey(Integer id);

	// Product selectByPrimaryKeyCount(Integer id);

	Product selectByProduct(Product record);

	Product selectByProductBooking(Product record);

	Map<String, Object> selectFrontByProduct(Product record);

	Map<String, Object> selectFrontByProductInfo(Product record);

	List<Map<String, Object>> listPageByProduct(Product clssname);

	List<Map<String, Object>> listAjaxPageProduct(Product pro);

	// 促销商品列表
	List<Map<String, Object>> listAjaxPageProductPromotion(Product pro);

	// 促销所有商品列表
	List<Map<String, Object>> listAjaxPageAllProductPromotion(Product pro);

	List<Product> listPageByProductBooking(Product clssname);

	List<Map<String, String>> listPageByVProduct(Product clssname);

	List<Product> getAllByProduct(Product clssname);

	List<Product> selectProductByCartOrder(Map<String, Object> maps);

	// 商品减库存
	int updateKuCunJian(Product classname);

	// 前台查询预约商品信息
	Product selectFrontByProductBooking(Product record);

	// 商城侧根据分类ID 或者排序 获取商品列表 不包含预售商品
	List<Product> getProListByProduct(Product clssname);

	// 前台展示
	public List<Map<String, Object>> selectProductFrontByShowList(
			Map<String, Object> map);

	// 前台展示根据基地判断库存 一次传入多个商品
	public List<Integer> getKuCunByProductIdsVsCompanyId(String prodIds,
			Integer companyId, String count, String specIds);
	// 前台展示根据商品ID判断购物车中有没有该商品
	public List<Integer> getCarProductVsUserId(String prodIds, Integer userId);
	
	// 前台展示根据商品ID判断收藏中有没有该商品
	public List<Integer> getCangProductVsUserId(String prodIds, Integer userId);
	
	// 查询库存信息，如果普通商品，那么判断商品规格是否够库存，返回类型SPEC_***,数量 如果礼盒商品返回P_***,数量
	public Map<String, Integer> getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
			String prodIds, String counts, String specIds);

	// 商品类别详细信息（productlist.html）
	public List<Map<String, Object>> selectProductList(Product pd);

	List<Map<String, Object>> listAjaxPageProductList(Product pro);

	// 购物车操作
	Product selectProductByCart(Integer id);

	// pc首页 8个推荐
	List<Product> selectTopRecommendProductByCart();

}