package com.wxsoft.xyd.quartz.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.quartz.model.SysScheduleJob;;

/**
 * @文件名称: SysScheduleJobService.java
 * @类路径: com.wxsoft.xyd.quartz.service.SysScheduleJobService;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2016-05-19 09:21:30
 */
public interface  SysScheduleJobService {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);
	// 根据主键暂停
	int updatePauseOne(Integer id);
	//根据主键恢复
	int updateresumeOne(Integer id);
	//根据重启
	int updateRestartOne(Integer id);

	// 根据所需插入
	int insertSelective(SysScheduleJob record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysScheduleJob record);

	// 根据主键查询
	SysScheduleJob selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysScheduleJob selectBySysScheduleJob(SysScheduleJob record);

	// 分页获取
	List<SysScheduleJob> listPageBySysScheduleJob(SysScheduleJob clssname);

	// 获取全部
	List<SysScheduleJob> getAllBySysScheduleJob(SysScheduleJob clssname);
	
	
	
	//自动确认收获方法
	void updateAutoAcceptOrder();
	//自动确认收获方法
	List<Map<String,Integer>>  updateAutoOverOrder();
	//自动取消订单方法
	void updateAutoCancelOrder();
	//自动清空验证码方法
	void updateAutoClearSms();
	//优惠券自动失效
	void updateAutoCouponsoverdue();
}