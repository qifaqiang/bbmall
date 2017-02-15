/**   
 * @文件名称: BusinessOpportunitieImgService.java
 * @类路径: com.wxltsoft.fxshop.common.service
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-8-12 下午05:49:23  
 */

package com.wxsoft.xyd.system.service;

import java.util.List;

import com.wxsoft.xyd.system.model.UserRoleresource;

/**
 * @类功能说明：UserRoleresourceService
 * @类修改者：kyz
 * @修改日期：2013-9-12
 * @修改说明：
 * @名称：kyz
 * @作者：kyz
 * @创建时间：2013-9-12 下午05:49:23
 */

public interface UserRoleresourceService {

	int insert(UserRoleresource record);

	int insertSelective(UserRoleresource record);

	int deleteByRoleId(UserRoleresource example);

	List<UserRoleresource> selectByRoleId(int roleid);
}
