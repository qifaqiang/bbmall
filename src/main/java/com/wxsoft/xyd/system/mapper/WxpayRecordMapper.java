package com.wxsoft.xyd.system.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.system.model.WxpayRecord;;

/**
 * @文件名称: WxpayRecordMapper.java
 * @类路径: com.wxsoft.xyd.system.mapper.WxpayRecordMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-12-18 18:05:53
 */
@Repository
public interface  WxpayRecordMapper {
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