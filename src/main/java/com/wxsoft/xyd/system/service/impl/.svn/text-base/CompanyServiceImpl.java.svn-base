/**   
 * @文件名称: CompanyServiceImpl.java
 * @类路径: com.wxltsoft.fxshop.common.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-3-29 下午05:50:09  
 */

package com.wxsoft.xyd.system.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.util.Qrcode;
import com.wxsoft.xyd.system.mapper.CompanyMapper;
import com.wxsoft.xyd.system.mapper.UserRoleAssociatedMapper;
import com.wxsoft.xyd.system.model.Company;
import com.wxsoft.xyd.system.model.UserRoleAssociated;
import com.wxsoft.xyd.system.service.CompanyService;

/**
 * @类功能说明：
 * 
 * @类修改者：kasiait @修改日期：2013-3-29 @修改说明：
 * @公司名称：kyz
 * @作者：kyz @创建时间：2013-3-29 下午05:50:09
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	/*
	 * mybatis代理类对象
	 */
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private UserRoleAssociatedMapper userRoleAssociatedMapper;

	@Override
	public Company getUserByNameAndPwd(String loginname, String password) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("login", loginname);
		param.put("passwd", password);
		return companyMapper.getUserByNameAndPwd(param);
	}

	@Override
	public Company getCompanyById(int id) {
		// TODO Auto-generated method stub
		return companyMapper.getCompanyById(id);
	}

	@Override
	public int deleteCompany(int companyid) {
		// TODO Auto-generated method stub
		return companyMapper.deleteCompany(companyid);
	}

	@Override
	public int insertCompany(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.insertCompany(company);
	}

	@Override
	public Company ifExistByLoginName(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.ifExistByLoginName(company);
	}

	@Override
	public int updateByPrimaryKeySelective(Company company, Integer roleId) {
		// TODO Auto-generated method stub
		UserRoleAssociated ura = new UserRoleAssociated();
		ura.setRoleid(roleId);
		ura.setUserid(company.getCompanyId());

		int i = companyMapper.updateCompany(company);
		if (i > 0) {
			if (null != roleId && roleId != 0) {
				return userRoleAssociatedMapper.updateRoleIdByUserId(ura);
			}
		}
		return i;
	}

	@Override
	public int insert(Company record, Integer roleId) throws Exception {
		// TODO Auto-generated method stub
		int i = companyMapper.insertCompany(record);
		if (i > 0) {
			// 如果是基地用户那么需要创建二维码
			if (roleId == 40) {
				Qrcode handler = new Qrcode();
				String imgFile = BaseConfig.FX_PIC_PATH
						+ "/attached/baseCompany/";
				File file = new File(imgFile);
				if (!file.exists() && !file.isDirectory()) {
					file.mkdir();
				}
				String tempPath = System.nanoTime() + ".jpg";
				String imgPath = imgFile + tempPath;
				handler.encode(BaseConfig.FX_DOMAIN_WWW
						+ "/wap/index.html?companyId=" + record.getCompanyId(),
						1000, 1000, imgPath);
				record.setQrcodeUrl("/attached/baseCompany/" + tempPath);
				companyMapper.updateCompany(record);
			}
			UserRoleAssociated ura = new UserRoleAssociated();
			ura.setRoleid(roleId);
			ura.setUserid(record.getCompanyId());
			if (userRoleAssociatedMapper.insert(ura) > 0) {
			}
		}
		return i;
	}

	@Override
	public List<Company> listPageAllUsers(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.listPageAllUsers(company);
	}

	@Override
	public int updateByCompanySelective(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.updateCompany(company);
	}

	@Override
	public Company getCompanyByVolFromId(Integer openId) {
		// TODO Auto-generated method stub
		return companyMapper.getCompanyByVolFromId(openId);
	}

	@Override
	public List<Company> listPageUserCommon(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.listPageUserCommon(company);
	}

	@Override
	public Company getPayByCompanyID(Integer id) {
		// TODO Auto-generated method stub
		return companyMapper.getPayByCompanyID(id);
	}

	@Override
	public Company getCompanyWxByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMapper.getCompanyWxByCompanyId(companyId);
	}

	@Override
	public List<Company> listPageUserCompany(Company company) {
		return companyMapper.listPageUserCompany(company);
	}

	@Override
	public int updatesetDisable(Company company) {
		return companyMapper.updatesetDisable(company);
	}

	@Override
	public Company selectRoleByCompanyId(int id) {
		return companyMapper.selectRoleByCompanyId(id);
	}

	@Override
	public List<Company> listPageByBaseStore(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.listPageByBaseStore(company);
	}

	/**
	 * 查询出所有基地用户的名字和ID 当roleID为40 的时候为基地用户
	 * 
	 * @param openId
	 * @return
	 */
	@Override
	public List<Company> selectCompanyName(Company company) {
		return companyMapper.selectCompanyName(company);

	}

	@Override
	public List<Map<String, Object>> selectCompanyByXY(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyByXY(companyId);
	}

	@Override
	public String selectCompanyByQrcode(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMapper.selectCompanyByQrcode(companyId);
	}

}