/**   
 * @文件名称: UserServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */
package com.wxsoft.xyd.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.mapper.SysCouponsMapper;
import com.wxsoft.xyd.common.mapper.SysCouponsRecordMapper;
import com.wxsoft.xyd.common.mapper.UserMapper;
import com.wxsoft.xyd.common.model.SysCoupons;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.system.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SysCouponsMapper sysCouponsMapper;
	@Autowired
	private SysCouponsRecordMapper sysCouponsRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	public static void main(String[] args) {
		System.out.println(Tools.getdate(3));
	}

	@Override
	public int insertSelective(User record) {
		if (userMapper.insertSelective(record) > 0) {
			SysCoupons sc = new SysCoupons();
			sc.setType(0);
			List<SysCoupons> sclist = sysCouponsMapper.getAllBySysCoupons(sc);
			for (SysCoupons sysCoupons : sclist) {
				SysCouponsRecord scr = new SysCouponsRecord();
				scr.setUserId(record.getId());
				scr.setCouponsId(sysCoupons.getId());
				scr.setNeedPrice(sysCoupons.getNeedPrice());
				scr.setSubstractPrice(sysCoupons.getSubstractPrice());
				scr.setWFrom(2);
				scr.setAddtime(new Date());
				scr.setStartTime(new Date());
				scr.setEndTime(Tools.getdate(sysCoupons.getValidTime()));
				scr.setState(1);
				sysCouponsRecordMapper.insertSelective(scr);
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User selectByUser(User record) {
		return userMapper.selectByUser(record);
	}

	@Override
	public List<User> listPageByUser(User clssname) {
		return userMapper.listPageByUser(clssname);
	}

	@Override
	public List<User> getAllByUser(User clssname) {
		return userMapper.getAllByUser(clssname);
	}

	@Override
	public int updateDisable(User user) {
		return userMapper.updateDisable(user);
	}

	@Override
	public List<Map<String, Object>> listPageUserVsWx(User record) {
		return userMapper.listPageUserVsWx(record);
	}

	@Override
	public User selectmachineCodeIfDel(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectmachineCodeIfDel(user);
	}

	@Override
	public int selectWithArchive(Integer userid) {
		// TODO Auto-generated method stub
		return userMapper.selectWithArchive(userid);
	}

}