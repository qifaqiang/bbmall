package com.wxsoft.xyd.system.service;

import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.system.model.Company;

public interface CompanyService {

	/**
	 * 判断用户名是否已经存在
	 * 
	 * @param company
	 * @return
	 */
	Company ifExistByLoginName(Company company);

	Company getPayByCompanyID(Integer id);

	Company getCompanyWxByCompanyId(Integer companyId);

	/**
	 * 插入新的公司
	 * 
	 * @param customer
	 */
	int insertCompany(Company company);

	int updatesetDisable(Company company);

	/**
	 * 
	 * @描述: 根据用户名和密码获取用户信息 @作者: kasiaits @日期:2013-3-19 @修改内容 @参数： @return @return
	 *      List <CommonUser> @throws
	 */
	Company getUserByNameAndPwd(String loginname, String password);

	/**
	 * 
	 * @描述: 查看公司根据id @作者: kasiaits @日期:2013-3-19 @修改内容 @参数： @return @return List
	 *      <CommonUser> @throws
	 */
	Company getCompanyById(int id);

	/**
	 * 
	 * <p>
	 * Title: TODO
	 * </p>
	 * <p>
	 * Description:查询用户角色
	 * </p>
	 * <p>
	 * Company:
	 * </p>
	 * 
	 * @author ljx
	 * @date 2015年7月6日下午6:42:38
	 */
	Company selectRoleByCompanyId(int id);

	/**
	 * 删除公司
	 * 
	 * @param companyid
	 */
	int deleteCompany(int companyid);

	/**
	 * 分页获取商户信息
	 * 
	 * @return
	 */
	List<Company> listPageAllUsers(Company company);

	List<Company> listPageUserCommon(Company company);

	List<Company> listPageUserCompany(Company company);

	/**
	 * 分页获取基地用户列表
	 * 
	 * @param company
	 * @return
	 */
	List<Company> listPageByBaseStore(Company company);

	/**
	 * 更新公司信息
	 * 
	 * @param company
	 * @param roleId
	 * @return
	 */
	int updateByPrimaryKeySelective(Company company, Integer roleId);

	/**
	 * 更新公司信息
	 * 
	 * @param company
	 * @param roleId
	 * @return
	 */
	int updateByCompanySelective(Company company);

	/**
	 * 插入公司信息
	 * 
	 * @param record
	 * @param roleId
	 * @return
	 */
	int insert(Company record, Integer roleId) throws Exception;

	Company getCompanyByVolFromId(Integer openId);

	/**
	 * 查询出所有基地用户的名字和ID 当roleID为40 的时候为基地用户
	 * 
	 * @param openId
	 * @return
	 */
	List<Company> selectCompanyName(Company company);

	/**
	 * 获取各个基地的坐标位置
	 * 
	 * @param openId
	 * @return
	 */
	List<Map<String, Object>> selectCompanyByXY(Integer companyId);

	/**
	 * 二维码图片
	 * 
	 * @param companyId
	 * @return
	 */
	String selectCompanyByQrcode(Integer companyId);

}
