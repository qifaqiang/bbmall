package com.wxsoft.xyd.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.common.mapper.UserCommentMapper;
import com.wxsoft.xyd.common.model.UserComment;
import com.wxsoft.xyd.common.service.UserCommentService;
import com.wxsoft.xyd.order.mapper.OrdersDetailMapper;
import com.wxsoft.xyd.order.model.OrdersDetail;
import com.wxsoft.xyd.prod.mapper.ProductStatisticsMapper;
import com.wxsoft.xyd.prod.model.ProductStatistics;

@Service("userCommentService")
public class UserCommentServiceImpl implements UserCommentService {

	@Autowired
	private UserCommentMapper userCommentMapper;
	@Autowired
	private ProductStatisticsMapper productStatisticsMapper;
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserComment record) {
		//修改订单详情的评价状态
		OrdersDetail ord = new OrdersDetail();
		ord.setId(record.getOrderDetailId());
		ord.setLocationStatus("1");
		ordersDetailMapper.updateByPrimaryKeySelective(ord);
		return userCommentMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserComment record) {
		UserComment uc = userCommentMapper.selectByPrimaryKey(record.getId());
		if (uc.getvState() == 3) {
			record.setvTime(new Date());
			if (record.getvState() == 1) {
				ProductStatistics ps = new ProductStatistics();
				switch (uc.getCommentLevel()) {
				case 1:
					ps.setCommentHigh(uc.getStarCount());
					ps.setCommentScore(uc.getStarCount());
					break;
				case 2:
					ps.setCommentMiddle(uc.getStarCount());
					ps.setCommentScore(uc.getStarCount());
					break;
				case 3:
					ps.setCommentLow(uc.getStarCount());
					ps.setCommentScore(uc.getStarCount());
					break;
				default:
					break;
				}
				
				ps.setProdId(uc.getProdId());
				productStatisticsMapper.updateByPrimaryKeySelective(ps);
			}
			return userCommentMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	@Override
	public UserComment selectByPrimaryKey(Integer id) {
		return userCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserComment selectByUserComment(UserComment record) {
		return userCommentMapper.selectByUserComment(record);
	}

	@Override
	public List<UserComment> listPageByUserComment(UserComment clssname) {
		return userCommentMapper.listPageByUserComment(clssname);
	}

	@Override
	public List<UserComment> getAllByUserComment(UserComment clssname) {
		return userCommentMapper.getAllByUserComment(clssname);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageByUserCommentFrontShow(
			UserComment clssname) {
		// TODO Auto-generated method stub
		return userCommentMapper.listAjaxPageByUserCommentFrontShow(clssname);
	}

	@Override
	public int selectCountLevelFromUserComment(UserComment clssname) {
		// TODO Auto-generated method stub
		return userCommentMapper.selectCountLevelFromUserComment(clssname);
	}
}