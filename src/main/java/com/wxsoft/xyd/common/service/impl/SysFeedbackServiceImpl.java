package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.common.model.SysFeedback;
import com.wxsoft.xyd.common.mapper.SysFeedbackMapper;
import com.wxsoft.xyd.common.service.SysFeedbackService;

@Service("sysFeedbackService")
public class SysFeedbackServiceImpl implements SysFeedbackService {
	
	@Autowired
	private SysFeedbackMapper sysFeedbackMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysFeedbackMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysFeedback record) {
		return sysFeedbackMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SysFeedback record) {
		return sysFeedbackMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysFeedback selectByPrimaryKey(Integer id) {
		return sysFeedbackMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysFeedback selectBySysFeedback(SysFeedback record) {
		return sysFeedbackMapper.selectBySysFeedback(record);
	}

	@Override
	public List<SysFeedback> listPageBySysFeedback(SysFeedback clssname) {
		return sysFeedbackMapper.listPageBySysFeedback(clssname);
	}

	@Override
	public List<SysFeedback> getAllBySysFeedback(SysFeedback clssname) {
		return sysFeedbackMapper.getAllBySysFeedback(clssname);
	}
}