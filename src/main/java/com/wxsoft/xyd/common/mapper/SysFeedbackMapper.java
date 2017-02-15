package com.wxsoft.xyd.common.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.wxsoft.xyd.common.model.SysFeedback;;

/**
 * @文件名称: SysFeedbackMapper.java
 * @类路径: com.wxsoft.xyd.common.mapper.SysFeedbackMapper;
 * @描述: TODO
 * @作者：kyz
 * @公司：wxltsoft
 * @时间：2015-11-24 15:15:12
 */
@Repository
public interface  SysFeedbackMapper {
	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需插入
	int insertSelective(SysFeedback record);

	// 根据所需更新
	int updateByPrimaryKeySelective(SysFeedback record);

	// 根据主键查询
	SysFeedback selectByPrimaryKey(Integer id);

	// 根据所需查询
	SysFeedback selectBySysFeedback(SysFeedback record);

	// 分页获取
	List<SysFeedback> listPageBySysFeedback(SysFeedback clssname);

	// 获取全部
	List<SysFeedback> getAllBySysFeedback(SysFeedback clssname);
}