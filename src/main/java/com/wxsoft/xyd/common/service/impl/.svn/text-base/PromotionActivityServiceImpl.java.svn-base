package com.wxsoft.xyd.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.util.Tools;
import com.wxsoft.xyd.common.mapper.PromotionActivityMapper;
import com.wxsoft.xyd.common.mapper.PromotionProductMapper;
import com.wxsoft.xyd.common.model.PromotionActivity;
import com.wxsoft.xyd.common.model.PromotionProduct;
import com.wxsoft.xyd.common.service.PromotionActivityService;
import com.wxsoft.xyd.system.mapper.SysLogMapper;
import com.wxsoft.xyd.system.model.SysLog;

@Service("promotionActivityService")
public class PromotionActivityServiceImpl implements PromotionActivityService {

	@Autowired
	private PromotionActivityMapper promotionActivityMapper;
	@Autowired
	private PromotionProductMapper promotionProductMapper;
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int a = promotionProductMapper.deleteByPrimary(id);
		return promotionActivityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(PromotionActivity record, SysLog sl, String prodId) {
		sysLogMapper.insertSelective(sl);
		int s = promotionActivityMapper.insertSelective(record);
		if (record.getAllProduct() == 0) {
			PromotionProduct ppd = new PromotionProduct();
			if (Tools.notEmpty(prodId)) {
				String[] ary = prodId.split(";");
				if (ary.length > 0) {
					for (int i = 0; i < ary.length; i++) {
						ppd.setProdId(Integer.parseInt(ary[i]));
						ppd.setPromotionId(record.getId());
						promotionProductMapper.insertSelective(ppd);
					}
				}
			}
		}
		return s;
	}

	@Override
	public int updateByPrimaryKeySelective(PromotionActivity record, SysLog sl, String prodId) {
		sysLogMapper.insertSelective(sl);
		// 先删除
		// if (record.getAllProduct() == 1) {
		String[] ary = prodId.split(";");
		if (Tools.notEmpty(prodId)) {
				promotionProductMapper.deleteByPrimary(record.getId());
		}
		// }
		// 等于零就是不是所有商品，然后就去后添加，1的话就是全场促销，就不用添加了
		if (record.getAllProduct() == 0) {
			if (Tools.notEmpty(prodId)) {
				PromotionProduct ppd = new PromotionProduct();
				ary = prodId.split(";");

				if (ary.length > 0) {
					for (int i = 0; i < ary.length; i++) {
						ppd.setProdId(Integer.parseInt(ary[i]));
						ppd.setPromotionId(record.getId());
						promotionProductMapper.insertSelective(ppd);
					}
				}
			}
		}
		return promotionActivityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PromotionActivity selectByPrimaryKey(Integer id) {
		return promotionActivityMapper.selectByPrimaryKey(id);
	}

	@Override
	public PromotionActivity selectByPromotionActivity(PromotionActivity record) {
		return promotionActivityMapper.selectByPromotionActivity(record);
	}

	@Override
	public List<PromotionActivity> listPageByPromotionActivity(PromotionActivity clssname) {
		return promotionActivityMapper.listPageByPromotionActivity(clssname);
	}

	@Override
	public List<Map<String, Object>> getAllByPromotionActivity(PromotionActivity clssname) {
		return promotionActivityMapper.getAllByPromotionActivity(clssname);
	}

	@Override
	public int deleteByPrimary(Integer promotionId) {
		// TODO Auto-generated method stub
		return promotionActivityMapper.deleteByPrimary(promotionId);
	}

	@Override
	public int updateByPrimaryKey(PromotionActivity record) {
		// TODO Auto-generated method stub
		return promotionActivityMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PromotionActivity> getAllByPromotionActivityNormal(PromotionActivity clssname) {
		// TODO Auto-generated method stub
		return promotionActivityMapper.getAllByPromotionActivityNormal(clssname);
	}

	@Override
	public Map<String, Object> getByPromotionActivity(PromotionActivity clssname) {
		// TODO Auto-generated method stub
		return promotionActivityMapper.getByPromotionActivity(clssname);
	}
}