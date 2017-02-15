package com.wxsoft.xyd.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.wxsoft.xyd.common.model.CompanyStock;
import com.wxsoft.xyd.common.mapper.CompanyStockMapper;
import com.wxsoft.xyd.common.service.CompanyStockService;

@Service("companyStockService")
public class CompanyStockServiceImpl implements CompanyStockService {
	
	@Autowired
	private CompanyStockMapper companyStockMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return companyStockMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CompanyStock record) {
		return companyStockMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyStock record) {
		return companyStockMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CompanyStock selectByPrimaryKey(Integer id) {
		return companyStockMapper.selectByPrimaryKey(id);
	}

	@Override
	public CompanyStock selectByCompanyStock(CompanyStock record) {
		return companyStockMapper.selectByCompanyStock(record);
	}

	@Override
	public List<CompanyStock> listPageByCompanyStock(CompanyStock clssname) {
		return companyStockMapper.listPageByCompanyStock(clssname);
	}

	@Override
	public List<CompanyStock> getAllByCompanyStock(CompanyStock clssname) {
		return companyStockMapper.getAllByCompanyStock(clssname);
	}

	/* (non-Javadoc)
	 * 根据基地商户ID查询
	 * @see com.wxsoft.xyd.common.service.CompanyStockService#selectByCompanyKey(java.lang.Integer)
	 */
	@Override
	public List<CompanyStock> selectByCompanyKey(Integer company_id) {
		// TODO Auto-generated method stub
		return companyStockMapper.selectByCompanyKey(company_id);
	}
	@Override
	public int updateSelective(CompanyStock record) {
		return companyStockMapper.updateSelective(record);
	}
}