/**   
 * @文件名称: WxpayRecordServiceImpl.java
 * @类路径: com.wxsoft.xyd.system.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2015-8-10 下午05:50:09  
 */
package com.wxsoft.xyd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.wxsoft.xyd.system.model.WxpayRecord;
import com.wxsoft.xyd.system.mapper.WxpayRecordMapper;
import com.wxsoft.xyd.system.service.WxpayRecordService;

@Service("wxpayRecordService")
public class WxpayRecordServiceImpl implements WxpayRecordService {
	
	@Autowired
	private WxpayRecordMapper wxpayRecordMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return wxpayRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WxpayRecord record) {
		return wxpayRecordMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(WxpayRecord record) {
		return wxpayRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public WxpayRecord selectByPrimaryKey(Integer id) {
		return wxpayRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public WxpayRecord selectByWxpayRecord(WxpayRecord record) {
		return wxpayRecordMapper.selectByWxpayRecord(record);
	}

	@Override
	public List<WxpayRecord> listPageByWxpayRecord(WxpayRecord clssname) {
		return wxpayRecordMapper.listPageByWxpayRecord(clssname);
	}

	@Override
	public List<WxpayRecord> getAllByWxpayRecord(WxpayRecord clssname) {
		return wxpayRecordMapper.getAllByWxpayRecord(clssname);
	}
}