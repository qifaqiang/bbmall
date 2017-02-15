package com.wxsoft.xyd.system.service;

import java.util.List;
import com.wxsoft.xyd.system.model.WxpayRecord;;

/**
 * @文件名称: WxpayRecordService.java
 * @类路径: com.wxsoft.xyd.system.service.WxpayRecordService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-18 18:05:53
 */
public interface  WxpayRecordService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(WxpayRecord record);

	// 根据所需更新
	int updateByPrimaryKeySelective(WxpayRecord record);

	// 根据主键查询
	WxpayRecord selectByPrimaryKey(Integer id);

	// 根据所需查询
	WxpayRecord selectByWxpayRecord(WxpayRecord record);

	// 分页获取
	List<WxpayRecord> listPageByWxpayRecord(WxpayRecord clssname);

	// 获取全部
	List<WxpayRecord> getAllByWxpayRecord(WxpayRecord clssname);
}