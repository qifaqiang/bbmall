/**   
 * @文件名称: BusinessOpportunitieImgService.java
 * @类路径: com.wxltsoft.fxshop.common.service
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-8-12 下午05:49:23  
 */

package com.wxsoft.xyd.system.service;

import com.wxsoft.xyd.system.model.UserRoleAssociated;

/**
 * @类功能说明：UserRoleAssociatedService
 * @类修改者：kyz
 * @修改日期：2013-9-12
 * @修改说明：
 * @名称：kyz
 * @作者：kyz
 * @创建时间：2013-9-12 下午05:49:23
 */

public interface UserRoleAssociatedService {
	int insert(UserRoleAssociated record);

	int deleteByRoleId(UserRoleAssociated example);
	
	int deleteByUserId(UserRoleAssociated example);
	
	int updateByUserId(UserRoleAssociated example);
	int updateRoleIdByUserId(UserRoleAssociated example);
	
	UserRoleAssociated selectRoleId(Integer id);
}
