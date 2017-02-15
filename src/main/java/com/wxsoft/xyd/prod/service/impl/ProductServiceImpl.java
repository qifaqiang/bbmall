package com.wxsoft.xyd.prod.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.mapper.CompanyStockMapper;
import com.wxsoft.xyd.common.model.CompanyStock;
import com.wxsoft.xyd.prod.mapper.ProductCatalogMapper;
import com.wxsoft.xyd.prod.mapper.ProductImageMapper;
import com.wxsoft.xyd.prod.mapper.ProductMapper;
import com.wxsoft.xyd.prod.mapper.ProductPackageMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoDetailMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationInfoMapper;
import com.wxsoft.xyd.prod.mapper.ProductSpecificationStockMapper;
import com.wxsoft.xyd.prod.model.Product;
import com.wxsoft.xyd.prod.model.ProductCatalog;
import com.wxsoft.xyd.prod.model.ProductImage;
import com.wxsoft.xyd.prod.model.ProductPackage;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfo;
import com.wxsoft.xyd.prod.model.ProductSpecificationInfoDetail;
import com.wxsoft.xyd.prod.model.ProductSpecificationStock;
import com.wxsoft.xyd.prod.service.ProductService;

/**
 * 商品 Service
 * 
 * @author kyz
 * 
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductCatalogMapper productCatalogMapper;
	@Autowired
	private ProductImageMapper productImageMapper;
	@Autowired
	private ProductSpecificationInfoMapper productSpecificationInfoMapper;
	@Autowired
	private ProductPackageMapper productPackageMapper;
	@Autowired
	private ProductSpecificationInfoDetailMapper productSpecificationInfoDetailMapper;
	@Autowired
	private ProductSpecificationStockMapper productSpecificationStockMapper;

	@Override
	public int deleteByPrimaryKey(Product record) {
		return productMapper.deleteByPrimaryKey(record);
	}

	@Override
	public int insert(Product record) {
		return productMapper.insert(record);
	}

	@Override
	public int insertSelective(Product record, String[] values,
			Integer[] inventorynumbers, BigDecimal[] marketPrices,
			BigDecimal[] prices, Integer[] skus, String[] prodspec_ck,
			String proBasicCount,Map specInfoDetalMap,String[] prod_sprc_ids) {
		if("1".equals(record.getIsSpecification()+"")){//启用规格
			String[] proImg = record.getPicuri().split(";");
			// 设置第一张图片为产品首图
			record.setPicuri(proImg[0]);

			// 根据二级分类获取一级分类信息
			ProductCatalog pc = productCatalogMapper.selectByPrimaryKey(record
					.getCatalogId2());
			record.setCatalogId1(pc.getPid());

			// 插入产品表
			int result = productMapper.insertSelective(record);
			if (result > 0) {
				// 遍历插入产品图片表
				for (String string : proImg) {
					ProductImage pi = new ProductImage();
					pi.setUri(string);
					pi.setProdid(record.getId());
					productImageMapper.insertSelective(pi);
				}

				int i = 0;
				if (null != prices) {
					for (BigDecimal integer : prices) {
						ProductSpecificationInfo psi = new ProductSpecificationInfo();
						psi.setMarketPrice(marketPrices[i]);
						psi.setPrice(integer);
						psi.setProductId(record.getId());
						psi.setInventorynumber(inventorynumbers[i]);
						productSpecificationInfoMapper.insertSelective(psi);
						//再插入规格附属表
						for (String string : prodspec_ck) {
							ProductSpecificationInfoDetail psid_t = new ProductSpecificationInfoDetail();
							psid_t.setSpecificationInfoId(psi.getId());
							
							String[] specDetail_strs = (String[]) specInfoDetalMap.get("specDetalInpt_"+string);
							String specificationDetailId_tt = specDetail_strs[i];
							psid_t.setSpecificationDetailId(Integer.parseInt(specificationDetailId_tt));
							
							psid_t.setSpecificatonId(Integer.parseInt(string));
							psid_t.setCreatetime("1");//当前时间
							productSpecificationInfoDetailMapper.insertSelective(psid_t);
						}
						i++;
					}
				}

				ProductSpecificationInfo psiTempLow = new ProductSpecificationInfo();// 最低商品规格信息
				if (record.getType() == 1) {// 礼盒商品
					ProductPackage pp = null;
					if(null != prod_sprc_ids && prod_sprc_ids.length >0){
						for (String temp : prod_sprc_ids) {
							pp = new ProductPackage();
							String temps[] = temp.split("_");
							pp.setProdId(record.getId());
							pp.setSpecificationInfoId(Integer.parseInt(temps[1]));
							pp.setProductId(Integer.parseInt(temps[2]));
							pp.setType(Integer.parseInt(temps[0]));
							productPackageMapper.insertSelective(pp);
						}
					}
				} else {// 普通商品
					psiTempLow.setProductId(record.getId());
					psiTempLow.setDelFlag("0");//未删除的
					psiTempLow = productSpecificationInfoMapper
							.getLowPrice(psiTempLow);
				}

				record.setPrice(psiTempLow.getPrice());
				record.setMarketPrice(psiTempLow.getMarketPrice());
				record.setInventorynumber(psiTempLow.getInventorynumber());
				record.setRemark(psiTempLow.getInventorynumber() == null ? ""
						: psiTempLow.getInventorynumber() + "");
				record.setSpecid(psiTempLow.getId());
				productMapper.updateByPrimaryKeySelective(record);
			}
			return result;
		}else{//不启用规格
			String[] proImg = record.getPicuri().split(";");
			// 设置第一张图片为产品首图
			record.setPicuri(proImg[0]);
			// 根据二级分类获取一级分类信息
			ProductCatalog pc = productCatalogMapper.selectByPrimaryKey(record.getCatalogId2());
			record.setCatalogId1(pc.getPid());
			// 插入产品表
			int result = productMapper.insertSelective(record);
			if (result > 0) {
				// 遍历插入产品图片表
				for (String string : proImg) {
					ProductImage pi = new ProductImage();
					pi.setUri(string);
					pi.setProdid(record.getId());
					productImageMapper.insertSelective(pi);
				}

				if (record.getType() == 1) {// 礼盒商品
					ProductPackage pp = null;
					if(null != prod_sprc_ids && prod_sprc_ids.length >0){
						for (String temp : prod_sprc_ids) {
							pp = new ProductPackage();
							String temps[] = temp.split("_");
							pp.setProdId(record.getId());
							pp.setSpecificationInfoId(Integer.parseInt(temps[1]));
							pp.setProductId(Integer.parseInt(temps[2]));
							pp.setType(Integer.parseInt(temps[0]));
							productPackageMapper.insertSelective(pp);
						}
					}
				}
			}
			return result;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(Product record, String[] values,
			Integer[] inventorynumbers, BigDecimal[] marketPrices,
			BigDecimal[] prices, Integer[] skus, String[] prodspec_ck,
			String proBasicCount,Map specInfoDetalMap,String[] prod_sprc_ids) {
		
		// 如果修改图片那么进行处理
		if (Tools.notEmpty(record.getPicuri())) {
			String[] proImg = record.getPicuri().split(";");
			// 设置第一张图片为产品首图
			record.setPicuri(proImg[0]);
			productImageMapper.deleteByProdId(record.getId());
			// 遍历插入产品图片表
			for (String string : proImg) {
				ProductImage pi = new ProductImage();
				pi.setUri(string);
				pi.setProdid(record.getId());
				productImageMapper.insertSelective(pi);
			}
		}
		
		if ("1".equals(record.getIsSpecification()+"")) {// 如果开启规格，那么对历史规格做处理
			ProductSpecificationInfo psiTemp = new ProductSpecificationInfo();
			psiTemp.setProductId(record.getId());
			psiTemp.setDelFlag("0");//未删除的
			List<ProductSpecificationInfo> psList = productSpecificationInfoMapper
					.getAllByProductSpecificationInfo(psiTemp);
			Map<String, String> maps = new HashMap<String, String>();
			for (ProductSpecificationInfo productSpecificationInfo : psList) {
				maps.put("p" + productSpecificationInfo.getId(), "");
			}

			if (null != prices) {
				int i = 0;
				if (skus == null || skus.length == 0) {
					skus = new Integer[1];
				}
				for (Integer integer : skus) {
					ProductSpecificationInfo psi = productSpecificationInfoMapper
							.selectByPrimaryKey(integer);
					if (null != psi) {
						psi.setInventorynumber(inventorynumbers[i]);
						psi.setMarketPrice(marketPrices[i]);
						psi.setPrice(prices[i]);
						productSpecificationInfoMapper.updateByPrimaryKeySelective(psi);
						
						productSpecificationInfoDetailMapper.deleteBySpecificationInfoId(psi.getId());//删掉之前的规格值
						//再插入规格附属表
						for (String string : prodspec_ck) {
							ProductSpecificationInfoDetail psid_t = new ProductSpecificationInfoDetail();
							psid_t.setSpecificationInfoId(psi.getId());
							
							String[] specDetail_strs = (String[]) specInfoDetalMap.get("specDetalInpt_"+string);
							String specificationDetailId_tt = specDetail_strs[i];
							psid_t.setSpecificationDetailId(Integer.parseInt(specificationDetailId_tt));
							
							psid_t.setSpecificatonId(Integer.parseInt(string));
							psid_t.setCreatetime("1");//当前时间
							productSpecificationInfoDetailMapper.insertSelective(psid_t);
						}
					} else {
						psi = new ProductSpecificationInfo();
						psi.setInventorynumber(inventorynumbers[i]);
						psi.setMarketPrice(marketPrices[i]);
						psi.setPrice(prices[i]);
						psi.setProductId(record.getId());
						productSpecificationInfoMapper.insertSelective(psi);
						//再插入规格附属表
						for (String string : prodspec_ck) {
							ProductSpecificationInfoDetail psid_t = new ProductSpecificationInfoDetail();
							psid_t.setSpecificationInfoId(psi.getId());
							
							String[] specDetail_strs = (String[]) specInfoDetalMap.get("specDetalInpt_"+string);
							String specificationDetailId_tt = specDetail_strs[i];
							psid_t.setSpecificationDetailId(Integer.parseInt(specificationDetailId_tt));
							
							psid_t.setSpecificatonId(Integer.parseInt(string));
							psid_t.setCreatetime("1");//当前时间
							productSpecificationInfoDetailMapper.insertSelective(psid_t);
						}
					}
					maps.remove("p" + integer);
					i++;
				}

			}
			for (Map.Entry<String, String> entry : maps.entrySet()) {
				productSpecificationInfoMapper.deleteByPrimaryKey(Integer
						.parseInt(entry.getKey().replace("p", "")));
			}

			ProductSpecificationInfo psiTempLow = new ProductSpecificationInfo();// 最低商品规格信息
			if (record.getType() == 1) {// 礼盒商品
				ProductPackage pp = null;
				if(null != prod_sprc_ids && prod_sprc_ids.length >0){
					productPackageMapper.deleteByProdId(record.getId());//先把之前该产品下的所有产品删掉
					
					for (String temp : prod_sprc_ids) {
						pp = new ProductPackage();
						String temps[] = temp.split("_");
						pp.setProdId(record.getId());
						pp.setSpecificationInfoId(Integer.parseInt(temps[1]));
						pp.setProductId(Integer.parseInt(temps[2]));
						pp.setType(Integer.parseInt(temps[0]));
						productPackageMapper.insertSelective(pp);
					}
				}
			} else {// 普通商品
				psiTempLow.setProductId(record.getId());
				psiTempLow.setDelFlag("0");
				psiTempLow = productSpecificationInfoMapper
						.getLowPrice(psiTempLow);
			}

			record.setPrice(psiTempLow.getPrice());
			record.setMarketPrice(psiTempLow.getMarketPrice());
			record.setRemark(psiTempLow.getInventorynumber() == null ? ""
					: psiTempLow.getInventorynumber() + "");
			record.setSpecid(psiTempLow.getId());
			record.setInventorynumber(psiTempLow.getInventorynumber());
			return productMapper.updateByPrimaryKeySelective(record);
		}else{//不启用规格
			productSpecificationInfoMapper.deleteByProductId(record.getId());//删掉所有的该产品的规格
			if (record.getType() == 1) {//礼盒商品
				ProductPackage pp = null;
				if(null != prod_sprc_ids && prod_sprc_ids.length >0){
					productPackageMapper.deleteByProdId(record.getId());//先把之前该产品下的所有产品删掉
					
					for (String temp : prod_sprc_ids) {
						pp = new ProductPackage();
						String temps[] = temp.split("_");
						pp.setProdId(record.getId());
						pp.setSpecificationInfoId(Integer.parseInt(temps[1]));
						pp.setProductId(Integer.parseInt(temps[2]));
						pp.setType(Integer.parseInt(temps[0]));
						productPackageMapper.insertSelective(pp);
					}
				}
			} 

			return productMapper.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public Product selectByPrimaryKey(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public Product selectByProduct(Product record) {
		return productMapper.selectByProduct(record);
	}

	@Override
	public List<Map<String, Object>> listPageByProduct(Product clssname) {
		return productMapper.listPageByProduct(clssname);
	}

	@Override
	public List<Product> getAllByProduct(Product clssname) {
		return productMapper.getAllByProduct(clssname);
	}

	@Override
	public Map<String, Object> selectFrontByProduct(Product record) {
		// TODO Auto-generated method stub
		return productMapper.selectFrontByProduct(record);
	}

	@Override
	public List<Product> selectProductByCartOrder(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return productMapper.selectProductByCartOrder(maps);
	}

	@Override
	public int updateKuCunJian(Product classname) {
		// TODO Auto-generated method stub
		return productMapper.updateKuCunJian(classname);
	}

	@Override
	public List<Map<String, String>> listPageByVProduct(Product clssname) {
		// TODO Auto-generated method stub
		return productMapper.listPageByVProduct(clssname);
	}

	@Override
	public int updateByPrimaryKeySelective(Product record) {
		// TODO Auto-generated method stub
		return productMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Product> listPageByProductBooking(Product clssname) {
		// TODO Auto-generated method stub
		return productMapper.listPageByProductBooking(clssname);
	}

	@Override
	public Product selectByProductBooking(Product record) {
		return productMapper.selectByProductBooking(record);
	}

	@Override
	public Product selectFrontByProductBooking(Product record) {
		// TODO Auto-generated method stub
		return productMapper.selectByProductBooking(record);
	}

	// 商城侧根据分类ID 或者排序 获取商品列表 不包含预售商品
	@Override
	public List<Product> getProListByProduct(Product clssname) {
		// TODO Auto-generated method stub
		return productMapper.getProListByProduct(clssname);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageProduct(Product pro) {
		// TODO Auto-generated method stub
		return productMapper.listAjaxPageProduct(pro);
	}

	@Override
	public List<Map<String, Object>> selectProductFrontByShowList(
			Map<String, Object> map) {
		return productMapper.selectProductFrontByShowList(map);
	}

	@Override
	public List<Integer> getCarProductVsUserId(String prodIds, Integer userId) {
		String[] prodId = prodIds.split(",");
		List<Product> pro = productMapper.getStockByProductId(prodId);
		List<Integer> result = new ArrayList<Integer>();
		Set<Integer> tempProdID = new HashSet<Integer>();
		Map<String, Object> params = new HashMap<String, Object>(2);
		for (Product p : pro) {
			tempProdID.add(p.getId());
		}
		params.put("userId", userId);
		params.put("prodId", tempProdID);
		pro = productMapper.getCarProductVsUserId(params);
		for (Product pr : pro) {
			result.add(pr.getId());
		}
		return result;

	}

	@Override
	public List<Integer> getCangProductVsUserId(String prodIds, Integer userId) {
		String[] prodId = prodIds.split(",");
		List<Product> pro = productMapper.getStockByProductId(prodId);
		List<Integer> result = new ArrayList<Integer>();
		Set<Integer> tempProdID = new HashSet<Integer>();
		Map<String, Object> params = new HashMap<String, Object>(2);
		for (Product p : pro) {
			tempProdID.add(p.getId());
		}
		params.put("userId", userId);
		params.put("prodId", tempProdID);
		pro = productMapper.getCangProductVsUserId(params);
		for (Product pr : pro) {
			result.add(pr.getId());
		}
		return result;

	}

	@Override
	public List<Integer> getKuCunByProductIdsVsCompanyId(String prodIds,
			Integer companyId, String counts, String specIds) {
		// 放置结果
		List<Integer> result = new ArrayList<Integer>();

		// 临时存放
		CompanyStock temp = new CompanyStock();
		String[] prodId = prodIds.split(",");
		String[] count = null == counts ? null : counts.split(",");
		String[] specId = null == specIds ? null : specIds.split(",");
		List<Product> p = productMapper.getStockByProductId(prodId);// 临时获取所有商品
		
		if(null != p && p.size() > 0){
//			ProductSpecificationStock pss = new ProductSpecificationStock();
//			pss.setCompanyId(companyId);
//			List<Map<String,Object>> prodStockList = productSpecificationStockMapper.selectStockByClass(pss);//获取该基地的所有产品的库存信息
			for (Product product : p) {
				if (product.getType() == 0) {
					// 普通商品
					if ( 0 == product.getIsSpecification().intValue() ) {
						//不启用规格
						if(product.getInventorynumber() <= 0){
							result.add(product.getId());
						}
					} else if ( 1 == product.getIsSpecification().intValue() ) {
						//启用规格  根据商品ID 去规格表里面查询是否存在
						int cur_count = productSpecificationInfoMapper.selectTotalInvcountByProid(product.getId());
						if(cur_count <= 0){
							result.add(product.getId());
						}
					}
				}else{//礼盒包
					ProductPackage pPack = new ProductPackage();
					pPack.setProdId(product.getId());
					List<ProductPackage> pplist = productPackageMapper
							.getAllByProductPackage(pPack);
					//跟里面包含的产品的库存来判断该礼盒是否有库存
					boolean flag = true;//代表都有货
					for (ProductPackage productPackage : pplist) {
						ProductSpecificationStock pss_t = new ProductSpecificationStock();
						pss_t.setCompanyId(companyId);
						pss_t.setSpecificationInfoId(productPackage.getSpecificationInfoId());
						pss_t.setProductId(productPackage.getProductId());
						pss_t.setType(productPackage.getType());
						ProductSpecificationStock resultPss = productSpecificationStockMapper.selectByProductSpecificationStock(pss_t);
						if(resultPss == null || resultPss.getInventorycount() <= 0){
							flag = false;
							break;
						}
					}
					
					if(!flag){
						result.add(product.getId());
					}
				}
			}
		}

		return result;
	}

	@Override
	public Map<String, Integer> getKuCunByProductIdsVsCompanyIdReturnSpecOrProductid(
			String prodIds, Integer companyId, String counts, String specIds) {
		// 放置结果
		Map<String, Integer> result = new HashMap<String, Integer>();

		// 临时存放
		CompanyStock temp = new CompanyStock();
		String[] prodId = prodIds.split(",");
		String[] count = null == counts ? null : counts.split(",");
		String[] specId = null == specIds ? null : specIds.split(",");

		if(null != prodId && prodId.length>0){
			for(int i=0;i<prodId.length;i++){
				Product product = productMapper.selectByPrimaryKey(Integer.parseInt(prodId[i]));//
				if(null != product){
					if (product.getSellStatus() != 1 || product.getDelFlag() == 1) {// 判断商品状态
						if (product.getType() == 0) {// 普通商品
							if(null == product.getIsSpecification() || 0== product.getIsSpecification()){//没有启用规格
								result.put("P_" + product.getId(), 0);
							}else{
								result.put("SPEC_" + specId[i], 0);
							}
						} else {// 礼盒包商品
							result.put("P_" + product.getId(), 0);
						}
					} else {
						if (product.getType() == 0) {// 普通商品
							if(null == product.getIsSpecification() || product.getIsSpecification()==0){//没有启用规格
								ProductSpecificationStock pss_t = new ProductSpecificationStock();
								pss_t.setCompanyId(companyId);
								pss_t.setProductId(product.getId());
								pss_t.setType(0);
								ProductSpecificationStock resultPss = productSpecificationStockMapper.selectByProductSpecificationStock(pss_t);
								if(null == resultPss || resultPss.getInventorycount() <=0 || Integer.parseInt(count[i]) > resultPss.getInventorycount()){
									result.put("P_" + product.getId(), resultPss == null? 0 : resultPss.getInventorycount());
								}
							}else{
								ProductSpecificationStock pss_t = new ProductSpecificationStock();
								pss_t.setCompanyId(companyId);
								pss_t.setSpecificationInfoId(Integer.parseInt(specId[i]));
								pss_t.setProductId(product.getId());
								pss_t.setType(1);
								ProductSpecificationStock resultPss = productSpecificationStockMapper.selectByProductSpecificationStock(pss_t);
								if(null == resultPss || resultPss.getInventorycount() <=0 || Integer.parseInt(count[i]) > resultPss.getInventorycount()){
									result.put("SPEC_"+specId[i], resultPss == null? 0 : resultPss.getInventorycount());
								}
							}
							
						} else {//礼盒商品
							ProductPackage pPack = new ProductPackage();
							pPack.setProdId(product.getId());
							List<ProductPackage> pplist = productPackageMapper
									.getAllByProductPackage(pPack);
							boolean flag = true;//商品包里面第一个产品
							int maxCount = 0;
							//跟里面包含的产品的库存来判断该礼盒是否有库存
							for (ProductPackage productPackage : pplist) {
								ProductSpecificationStock pss_t = new ProductSpecificationStock();
								pss_t.setCompanyId(companyId);
								pss_t.setSpecificationInfoId(productPackage.getSpecificationInfoId());
								pss_t.setProductId(productPackage.getProductId());
								pss_t.setType(productPackage.getType());
								ProductSpecificationStock resultPss = productSpecificationStockMapper.selectByProductSpecificationStock(pss_t);
								if(resultPss == null || resultPss.getInventorycount() <= 0){
									maxCount = 0;
									break;
								}else{
									if(flag){
										flag = false;
										maxCount = resultPss.getInventorycount();
									}else{
										if(resultPss.getInventorycount() < maxCount){
											maxCount = resultPss.getInventorycount();
										}
									}
								}
							}
							if(maxCount == 0 || Integer.parseInt(count[i]) > maxCount){
								result.put("P_" + product.getId(), maxCount);
							}
						}
					}

				}else{
					result.put("异常", 0);
				}
				
			}
		}
		
		
//		// 临时存放
//		CompanyStock temp = new CompanyStock();
//		String[] prodId = prodIds.split(",");
//		String[] count = null == counts ? null : counts.split(",");
//		String[] specId = null == specIds ? null : specIds.split(",");
//
//		List<Product> p = productMapper.getStockNoCheckStateByProductId(prodId);// 临时获取所有商品
//
//		List<CompanyStock> tempCompanyStock = new ArrayList<CompanyStock>();// 临时存放基地库存
//		List<ProductSpecificationInfo> tempProductSpecificationInfo = new ArrayList<ProductSpecificationInfo>();// 临时存放商品规格信息
//		Map<Integer, ProductSpecificationInfo> tempProductSpecificationInfoMap = new HashMap<Integer, ProductSpecificationInfo>();
//		Map<Integer, CompanyStock> tempCompanyStockMap = new HashMap<Integer, CompanyStock>();
//		int i = 0;
//		Set<Integer> tempBasicIds = new HashSet<Integer>();// 临时存放商品字典id
//		Set<Integer> tempProdID = new HashSet<Integer>();// 临时存放商品id
//		List<Map<String, Object>> tempSpAll = new ArrayList<Map<String, Object>>();
//		
//		Map<String, Integer> tempStockMap = new HashMap<String, Integer>();
//		Set<String> typespecIdProdId = new HashSet<String>();
//		int j = 0;
//		for (Product product : p) {
//			if (product.getSellStatus() != 1 || product.getDelFlag() == 1) {// 判断商品状态
//				if (product.getType() == 0) {// 普通商品
//					if(null == product.getIsSpecification() || 0== product.getIsSpecification()){//没有启用规格
//						result.put("P_" + product.getId(), 0);
//					}else{
//						result.put("SPEC_" + specId[i], 0);
//					}
//				} else {// 礼盒包商品
//					result.put("P_" + product.getId(), 0);
//				}
//			} else {
//				if (product.getType() == 0) {// 普通商品
//					if (null != specId) {
//						String key_t ="";
//						if(null == product.getIsSpecification() || 0== product.getIsSpecification()){//没有启用规格
//							key_t = "0_0_"+product.getId();
//						}else{
//							key_t = "1_"+specId[j]+"_"+product.getId();
//						}
//						if(typespecIdProdId.contains(key_t)){
//							Integer cur_count = tempStockMap.get(key_t);
//							tempStockMap.put(key_t, cur_count + Integer.parseInt(count[j]));
//						}else{
//							typespecIdProdId.add(key_t);
//							tempStockMap.put(key_t,Integer.parseInt(count[j]));
//						}
//					}
//				} else {//礼盒商品
//					ProductPackage pPack = new ProductPackage();
//					pPack.setProdId(product.getId());
//					List<ProductPackage> pplist = productPackageMapper.getAllByProductPackage(pPack);
//					product.setProductPackageList(pplist);
//					for (ProductPackage pp2 : pplist) {
//						String key_t ="";
//						if(pp2.getType() == 0){//没有启用规格
//							key_t = "0_0_"+pp2.getProductId();
//						}else{
//							key_t = "1_"+pp2.getSpecificationInfoId()+"_"+pp2.getProductId();
//						}
//						if(typespecIdProdId.contains(key_t)){
//							Integer cur_count = tempStockMap.get(key_t);
//							tempStockMap.put(key_t, cur_count + Integer.parseInt(count[j]));
//						}else{
//							typespecIdProdId.add(key_t);
//							tempStockMap.put(key_t,Integer.parseInt(count[j]));
//						}
//					}
//				}
//			}
//			j++;
//
//		}
//		if (tempStockMap.size() == 0) {
//			return result;
//		}
//		ProductSpecificationStock pss_temp = null;
//		for (String str : typespecIdProdId) {
//			pss_temp = new ProductSpecificationStock();
//			String[] strs = str.split("_");
//			pss_temp.setType(Integer.parseInt(strs[0]));
//			pss_temp.setSpecificationInfoId(Integer.parseInt(strs[1]));
//			pss_temp.setProductId(Integer.parseInt(strs[2]));
//			pss_temp.setCompanyId(companyId);
//			Integer buyCount = tempStockMap.get(str);//将要购买的数量
//			pss_temp.setInventorycount(buyCount);
//		}
//		
//		
//		
//		
//		
//		Map<String, Object> params = new HashMap<String, Object>(3);
//		params.put("companyId", companyId);
//		if(null != tempBasicIds && tempBasicIds.size()>0){
//			params.put("prodBasicId", tempBasicIds);
//		}else{
//			params.put("prodBasicId", null);
//		}
//		params.put("tempProdID", tempProdID);
//		tempCompanyStock = companyStockMapper.selectByCompanyStockMaps(params);// error
//
//		for (CompanyStock cStock : tempCompanyStock) {
//			tempCompanyStockMap.put(cStock.getProdBasicId(), cStock);
//		}
//
//		// 如果規格爲空，那么取出该商品的最小规格还存起来
//		if (null == specId && tempProdID.size() > 0) {
//			tempProductSpecificationInfo = productSpecificationInfoMapper
//					.getLowInventorynumberMaps(params);
//			for (ProductSpecificationInfo psInfo : tempProductSpecificationInfo) {
//				tempProductSpecificationInfoMap.put(psInfo.getProductId(),
//						psInfo);
//			}
//		}
//		// 如果規格不爲空，那么取出该商品的传入规格还存起来
//		if (null != specId && tempProdID.size() > 0) {
//			tempProductSpecificationInfo = productSpecificationInfoMapper
//					.getInventorynumberMaps(tempSpAll);
//			for (ProductSpecificationInfo psInfo : tempProductSpecificationInfo) {
//				tempProductSpecificationInfoMap.put(psInfo.getId(), psInfo);
//			}
//		}
//
//		for (Product product : p) {
//			if (product.getSellStatus() != 1 || product.getDelFlag() == 1) {// 判断商品状态
//
//				if (product.getType() == 0) {// 普通商品
//					result.put("SPEC_" + specId[i], 0);
//				} else {// 礼盒包商品
//					result.put("P_" + product.getId(), 0);
//				}
//			} else {
//				int tempCount = 1;
//
//				if (null != count) {
//					tempCount = Integer.parseInt(count[i]);
//				}
//				if (tempCount < 1) {
//					if (product.getType() == 0) {// 普通商品
//						result.put("SPEC_" + specId[i], 0);
//					} else {// 礼盒包商品
//						result.put("P_" + product.getId(), 0);
//					}
//				}
//				if (product.getType() == 0) {// 普通商品
//					// 记录临时规格，如果不传规格id 那么查询最小
//					ProductSpecificationInfo pSpec = null;
//					temp = tempCompanyStockMap.get(product.getProdBasicsId());
//					if (null != specId) {// 存在规格id
//						pSpec = tempProductSpecificationInfoMap.get(Integer
//								.parseInt(specId[i]));
//
//					} else {
//						pSpec = tempProductSpecificationInfoMap.get(product
//								.getId());
//					}
//
//					if (null != pSpec) {
//						if (null != temp
//								&& (temp.getInventorynumber() - pSpec
//										.getInventorynumber() * tempCount) >= 0) {// 基地商品库存不足
//
//						} else {
//							if (temp != null) {
//								int tCount = temp.getInventorynumber()
//										/ pSpec.getInventorynumber();// 最大库存
//								result.put("SPEC_" + pSpec.getId(), tCount);
//							} else {
//								result.put("SPEC_" + pSpec.getId(), 0);
//							}
//						}
//					} else {
//						result.put("SPEC_" + specId[i], 0);
//					}
//
//				} else {// 礼盒包商品
//
//					List<ProductPackage> pp = product.getProductPackageList();
//					boolean canSale = true;
//					List<Integer> tempC = new ArrayList<Integer>();// 礼盒中每一个商品的最大数量
//
//					for (ProductPackage productPackage : pp) {
//						temp = tempCompanyStockMap.get(productPackage
//								.getProdBaseicId());
//						// 获取礼盒中每一个字典的id对应的系统库存表
//						if (canSale
//								&& null != temp
//								&& (temp.getInventorynumber() - productPackage
//										.getInventorynumber() * tempCount) >= 0) {// 基地商品库存不足
//						} else {
//							if (null == temp) {
//								tempC.add(0);
//								canSale = false;
//							} else {
//								int tCount = temp.getInventorynumber()
//										/ productPackage.getInventorynumber();
//								tempC.add(tCount);
//								canSale = false;
//							}
//
//						}
//					}
//					if (canSale) {
//					} else {
//						result.put("P_" + product.getId(),
//								tempC.size() > 0 ? Collections.min(tempC) : 0);
//					}
//				}
//			}
//			i++;
//		}

		return result;
	}

	
	public static void main(String[] args) {
		List<Integer> tempC = new ArrayList<Integer>();// 礼盒中每一个商品的最大数量
		System.out.println(Collections.min(tempC));
	}

	@Override
	public List<Map<String, Object>> selectProductList(Product map) {
		// TODO Auto-generated method stub
		return productMapper.selectProductList(map);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageProductList(Product pro) {
		// TODO Auto-generated method stub
		return productMapper.listAjaxPageProductList(pro);
	}

	@Override
	public Map<String, Object> selectFrontByProductInfo(Product record) {
		// TODO Auto-generated method stub
		return productMapper.selectFrontByProductInfo(record);
	}

	@Override
	public Product selectProductByCart(Integer id) {
		// TODO Auto-generated method stub
		return productMapper.selectProductByCart(id);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageProductPromotion(Product pro) {
		// TODO Auto-generated method stub
		return productMapper.listAjaxPageProductPromotion(pro);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageAllProductPromotion(Product pro) {
		// TODO Auto-generated method stub
		return productMapper.listAjaxPageAllProductPromotion(pro);
	}

	@Override
	public List<Product> selectTopRecommendProductByCart() {
		// TODO Auto-generated method stub
		return productMapper.selectTopRecommendProductByCart();
	}

}