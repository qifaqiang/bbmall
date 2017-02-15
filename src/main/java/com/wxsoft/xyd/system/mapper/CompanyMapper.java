/**   
 * @文件名称: CompanyMapper.java
 * @类路径: com.wxltsoft.fxshop.common.mapper
 * @描述: TODO
 * @作者：kasiaits
 * @时间：2013-3-29 下午04:53:17  
 */

package com.wxsoft.xyd.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wxsoft.xyd.system.model.Company;

/**
 * @类功能说明：公司信息
 * @类修改者：kasiait @修改日期：2013-3-29 @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits @创建时间：2013-3-29 下午04:53:17
 */
@Repository
public interface CompanyMapper {

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

	/**
	 * 
	 * @描述: 根据用户名和密码获取用户信息 @作者: kasiaits @日期:2013-3-19 @修改内容 @参数： @return @return
	 *      List <CommonUser> @throws
	 */
	Company getUserByNameAndPwd(Map<String, String> param);

	/**
	 * 
	 * @描述: 查看公司根据id @作者: kasiaits @日期:2013-3-19 @修改内容 @参数： @return @return List
	 *      <CommonUser> @throws
	 */
	Company getCompanyById(int id);

	Company selectRoleByCompanyId(int id);

	/**
	 * 
	 * @描述: 更新 @作者: kasiaits @日期:2013-3-19 @修改内容 @参数： @return @return List
	 *      <CommonUser> @throws
	 */
	int updateCompany(Company company);

	int updatesetDisable(Company company);

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
	 * 根据volfrom获取公司信息
	 * 
	 * @param openId
	 * @return
	 */
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
	List<Map<String, Object>> selectCompanyByXY(@Param(value="companyId")Integer companyId);
	
	/**
	 * 二维码图片
	 * @param companyId
	 * @return
	 */
	String selectCompanyByQrcode( Integer companyId);

}
