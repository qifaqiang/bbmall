package com.wxsoft.xyd.common.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.common.model.SysCouponsRecord;

/**
 * @文件名称: SysCouponsRecordService.java
 * @类路径: com.wxsoft.xyd.common.service.SysCouponsRecordService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-22 11:47:04
 */
public interface SysCouponsRecordService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysCouponsRecord record);
	int insertSelectiveT(SysCouponsRecord record);
	// 根据所需更新
	int updateByPrimaryKeySelective(SysCouponsRecord record);
	int selectCountByOrdersn(SysCouponsRecord record);
	// 根据主键查询
	SysCouponsRecord selectByPrimaryKey(Integer id);
   
	// 根据所需查询
	SysCouponsRecord selectBySysCouponsRecord(SysCouponsRecord record);

	// 分页获取
	List<SysCouponsRecord> listPageBySysCouponsRecord(SysCouponsRecord clssname);

	List<Map<String, Object>> listPageBySysCouponsRecordWithUser(
			SysCouponsRecord clssname);
	List<Map<String,Object>> listByOrderSnRedBag(SysCouponsRecord sysCouponsRecord);
	List<SysCouponsRecord> getUserCouponsRecord(SysCouponsRecord sysCoupons);

	// 获取全部
	List<SysCouponsRecord> getAllBySysCouponsRecord(SysCouponsRecord clssname);

	List<SysCouponsRecord> listBySysCouponsRecordCan(SysCouponsRecord clssname);

	// 获得登陆用户的未使用优惠券
	List<Map<String, Object>> listAjaxPageBySysCouponsRecordCan(
			SysCouponsRecord clssname);

	List<SysCouponsRecord> listBySysCouponsRecordExpire(
			SysCouponsRecord clssname);

	// 获得登陆用户的失效过期优惠券
	List<Map<String, Object>> listAjaxPageBySysCouponsRecordExpire(
			SysCouponsRecord clssname);

	// 获得登陆用户的已使用优惠券
	List<Map<String, Object>> listAjaxPageBySysCouponsRecordOver(
			SysCouponsRecord clssname);

	List<SysCouponsRecord> listBySysCouponsRecordOver(SysCouponsRecord clssname);
    String  insertRedBag(String ifRmobile,String code,String ordersn,String mobile);
	// 注册后，把优惠券转化成人
	int updateByRegMobile(SysCouponsRecord record);
}