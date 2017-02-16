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
			String prodIds, String counts, String specIds) {
		// 放置结果
		Map<String, Integer> result = new HashMap<>();

		// 临时存放
		CompanyStock temp = new CompanyStock();
		String[] prodId = prodIds.split(",");
		String[] count = null == counts ? null : counts.split(",");
		String[] specId = null == specIds ? null : specIds.split(",");

		if(null != prodId && prodId.length>0){
			for(int i=0;i<prodId.length;i++){
				Product product = productMapper.selectByPrimaryKey(Integer.parseInt(prodId[i]));//
				if(null != product){
					// 判断商品状态
					if (product.getSellStatus() != 1 || product.getDelFlag() == 1) {
						if (product.getType() == 0) {// 普通商品
							if(null == product.getIsSpecification() || 0== product.getIsSpecification()){
								//没有启用规格
								result.put("P_" + product.getId(), 0);
							}else{
								result.put("SPEC_" + specId[i], 0);
							}
						} else {// 礼盒包商品
							result.put("P_" + product.getId(), 0);
						}
					} else {
						if (product.getType() == 0) {
							// 普通商品
							if(null == product.getIsSpecification() || product.getIsSpecification()==0){
								//没有启用规格
								if( product.getInventorynumber() <=0 || Integer.parseInt(count[i]) > product.getInventorynumber() ){
									result.put("P_" + product.getId(), product.getInventorynumber());
								}
							}else{
								ProductSpecificationInfo psi = new ProductSpecificationInfo();
								psi.setProductId(product.getId());
								psi.setId(Integer.parseInt(specId[i]));
								psi.setDelFlag("0");
								psi = productSpecificationInfoMapper.selectByProductSpecificationInfo(psi);

								if(null == psi || psi.getInventorynumber() <=0 || Integer.parseInt(count[i]) > psi.getInventorynumber()){
									result.put("SPEC_"+specId[i], psi == null? 0 : psi.getInventorynumber());
								}
							}
							
						} else {
							//礼盒商品
//							ProductPackage pPack = new ProductPackage();
//							pPack.setProdId(product.getId());
//							List<ProductPackage> pplist = productPackageMapper
//									.getAllByProductPackage(pPack);
//							boolean flag = true;//商品包里面第一个产品
//							int maxCount = 0;
//							//跟里面包含的产品的库存来判断该礼盒是否有库存
//							for (ProductPackage productPackage : pplist) {
//								ProductSpecificationStock pss_t = new ProductSpecificationStock();
//								pss_t.setCompanyId(companyId);
//								pss_t.setSpecificationInfoId(productPackage.getSpecificationInfoId());
//								pss_t.setProductId(productPackage.getProductId());
//								pss_t.setType(productPackage.getType());
//								ProductSpecificationStock resultPss = productSpecificationStockMapper.selectByProductSpecificationStock(pss_t);
//								if(resultPss == null || resultPss.getInventorycount() <= 0){
//									maxCount = 0;
//									break;
//								}else{
//									if(flag){
//										flag = false;
//										maxCount = resultPss.getInventorycount();
//									}else{
//										if(resultPss.getInventorycount() < maxCount){
//											maxCount = resultPss.getInventorycount();
//										}
//									}
//								}
//							}
//							if(maxCount == 0 || Integer.parseInt(count[i]) > maxCount){
//								result.put("P_" + product.getId(), maxCount);
//							}
						}
					}

				}else{
					result.put("异常", 0);
				}
				
			}
		}
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