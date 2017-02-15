package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.common.model.CompanyStockinRecord;
import com.wxsoft.xyd.common.mapper.CompanyStockinRecordMapper;
import com.wxsoft.xyd.common.service.CompanyStockinRecordService;

@Service("companyStockinRecordService")
public class CompanyStockinRecordServiceImpl implements CompanyStockinRecordService {
	
	@Autowired
	private CompanyStockinRecordMapper companyStockinRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return companyStockinRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CompanyStockinRecord record) {
		return companyStockinRecordMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyStockinRecord record) {
		return companyStockinRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CompanyStockinRecord selectByPrimaryKey(Integer id) {
		return companyStockinRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public CompanyStockinRecord selectByCompanyStockinRecord(CompanyStockinRecord record) {
		return companyStockinRecordMapper.selectByCompanyStockinRecord(record);
	}

	@Override
	public List<CompanyStockinRecord> listPageByCompanyStockinRecord(CompanyStockinRecord clssname) {
		return companyStockinRecordMapper.listPageByCompanyStockinRecord(clssname);
	}

	@Override
	public List<CompanyStockinRecord> getAllByCompanyStockinRecord(CompanyStockinRecord clssname) {
		return companyStockinRecordMapper.getAllByCompanyStockinRecord(clssname);
	}
}