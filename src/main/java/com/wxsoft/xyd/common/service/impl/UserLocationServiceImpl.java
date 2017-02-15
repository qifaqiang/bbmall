package com.wxsoft.xyd.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.UserLocationMapper;
import com.wxsoft.xyd.common.model.UserLocation;
import com.wxsoft.xyd.common.service.UserLocationService;

@Service("userLocationService")
public class UserLocationServiceImpl implements UserLocationService {
	@Autowired
	private UserLocationMapper userLocationMapper;

	@Override
	public int deleteByPrimaryKey(Integer id, Integer userId) {

		// 删除之后判断是否还有默认收货地址，没有就设置一个默认收货地址
		int sa = userLocationMapper.deleteByPrimaryKey(id);
		int res = 0;
		if (sa > 0) {
			UserLocation uslocal = new UserLocation();
			uslocal.setStatus(true);
			uslocal.setUserid(userId);

			List<UserLocation> list = userLocationMapper.getAllByUserLocation(uslocal);
			if (list.size() > 0) {
				// 判断是否含有默认收货的
				res = 0;
			} else {
				uslocal.setStatus(false);
				List<UserLocation> lists = userLocationMapper.getAllByUserLocation(uslocal);
				uslocal = new UserLocation();
				if (lists.size() > 0) {
					uslocal.setUserid(userId);
					uslocal.setId(lists.get(0).getId());
					uslocal.setStatus(true);
					if (lists.size() == 1) {
						if (userLocationMapper.updateByPrimaryKeySelective(uslocal) > 0) {
							res = 0;
						} else {
							res = 2;
						}
					} else {
						userLocationMapper.updateByPrimaryKeySelective(uslocal);
						uslocal.setStatus(false);
						if (userLocationMapper.updateStatus(uslocal) > 0) {
							res = 0;
						} else {
							res = 2;
						}
					}
				} else {
					res = 0;
				}
			}
		}
		return res;
	}

	@Override
	public int insert(UserLocation record) {
		return userLocationMapper.insert(record);
	}

	@Override
	public int insertSelective(UserLocation record) {
		// 先看看这个是不是默认，如果是的话就修改其他的状态
		int nstau = 0;
		if (record.getStatus() == true) {
			nstau = userLocationMapper.updateBySetDefault(record.getUserid());
		}
		int is = userLocationMapper.insertSelective(record);
		return is + nstau;
	}

	@Override
	public int updateByPrimaryKeySelective(UserLocation record) {
		int stau = 0;
		if (record.getStatus() == true) {
			stau = userLocationMapper.updateBySetDefault(record.getUserid());
		}
		UserLocation us = new UserLocation();
		us.setUserid(record.getUserid());
		us.setStatus(true);
		List<UserLocation> res = userLocationMapper.getAllByUserLocation(us);
		// true 为零

		if (res.size() == 1) {
			// 更新之前判断是否为默认 插入前有默认直接插入 插入时候有默认
			us.setUserid(record.getUserid());
			us.setStatus(false);
			List<UserLocation> rea = userLocationMapper.getAllByUserLocation(us);
			// false为零
			if (rea.size() == 0) {
				record.setStatus(true);
			}
			if ((int) res.get(0).getId() == record.getId()) {
				if (record.getStatus() == false) {
					record.setStatus(true);
				}
			}
			/*
			 * if(rea.size()==1){ record.setStatus(true); }
			 */
			if (record.getStatus() == true) {
				// 设置其他的为 false
				stau = userLocationMapper.updateBySetDefault(record.getUserid());
			}
		}
		if (res.size() == 0) {
			record.setStatus(true);
		}
		int nstau = userLocationMapper.updateByPrimaryKeySelective(record);
		int al = stau + nstau;
		return al;
	}

	@Override
	public int updatestau(UserLocation record) {
		return userLocationMapper.updateStatus(record);
	}

	@Override
	public UserLocation selectByPrimaryKey(Integer id) {
		return userLocationMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserLocation selectByUserLocation(UserLocation record) {
		return userLocationMapper.selectByUserLocation(record);
	}

	@Override
	public List<UserLocation> listAjaxPageByUserLocation(UserLocation clssname) {
		return userLocationMapper.listAjaxPageByUserLocation(clssname);
	}

	@Override
	public List<UserLocation> getAllByUserLocation(UserLocation clssname) {
		return userLocationMapper.getAllByUserLocation(clssname);
	}

	@Override
	public int updateBySetDefault(int usrid) {
		// TODO Auto-generated method stub
		return userLocationMapper.updateBySetDefault(usrid);
	}

	@Override
	public UserLocation selectByUserLocationOrStatus(UserLocation userLocation) {
		// TODO Auto-generated method stub
		return userLocationMapper.selectByUserLocationOrStatus(userLocation);
	}

	@Override
	public int updateStatus(UserLocation record) {
		// TODO Auto-generated method stub
		return userLocationMapper.updateStatus(record);
	}

	@Override
	public UserLocation selectById(UserLocation record) {
		// TODO Auto-generated method stub
		return userLocationMapper.selectById(record);
	}

	@Override
	public int updateByUerId(UserLocation record) {
		// TODO Auto-generated method stub
		return userLocationMapper.updateByPrimaryKeySelective(record);
	}
}