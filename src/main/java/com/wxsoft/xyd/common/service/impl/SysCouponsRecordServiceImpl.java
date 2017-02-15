package com.wxsoft.xyd.common.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.framework.config.SystemConfig;
import com.wxsoft.framework.util.Tools;
import com.wxsoft.framework.util.wxpay.AdvancedUtil;
import com.wxsoft.xyd.common.mapper.SysCouponsMapper;
import com.wxsoft.xyd.common.mapper.SysCouponsRecordMapper;
import com.wxsoft.xyd.common.mapper.UserMapper;
import com.wxsoft.xyd.common.mapper.UserWxMapper;
import com.wxsoft.xyd.common.model.SNSUserInfo;
import com.wxsoft.xyd.common.model.SysCoupons;
import com.wxsoft.xyd.common.model.SysCouponsRecord;
import com.wxsoft.xyd.common.model.User;
import com.wxsoft.xyd.common.model.UserWx;
import com.wxsoft.xyd.common.model.WeixinOauth2Token;
import com.wxsoft.xyd.common.service.SysCouponsRecordService;
import com.wxsoft.xyd.system.mapper.SysCouponsConfMapper;
import com.wxsoft.xyd.system.model.SysCouponsConf;

@Service("sysCouponsRecordService")
public class SysCouponsRecordServiceImpl implements SysCouponsRecordService {

	@Autowired
	private SysCouponsRecordMapper sysCouponsRecordMapper;
	@Autowired
	private SysCouponsConfMapper sysCouponsConfMapper;
	@Autowired
	private SysCouponsMapper sysCouponsMapper;
	@Autowired
	private UserWxMapper userWxMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysCouponsRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysCouponsRecord record) {
		// SysCouponsService sysCouponsService=new SysCouponsServiceImpl();
		SysCoupons sys = new SysCoupons();
		SysCouponsRecord sysr = new SysCouponsRecord();
		// 判断领取的优惠券是否删除或者过期
		sys = sysCouponsMapper.selectByPrimaryKeyCe(record.getCouponsId());
		if (null != sys) {
			// 该优惠券总数减1
			sysr.setUserId(record.getUserId());
			sysr.setCouponsId(record.getCouponsId());
			sysr = sysCouponsRecordMapper.selectBySysCouponsRecord(sysr);
			if (null != sysr) {
				return 0;
			} else {
				sys.setId(record.getCouponsId());
				sysCouponsMapper.updateCount(sys);
				return sysCouponsRecordMapper.insertSelective(record);
			}
		} else {
			return 0;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(SysCouponsRecord record) {
		return sysCouponsRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysCouponsRecord selectByPrimaryKey(Integer id) {
		return sysCouponsRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysCouponsRecord selectBySysCouponsRecord(SysCouponsRecord record) {
		return sysCouponsRecordMapper.selectBySysCouponsRecord(record);
	}

	@Override
	public List<SysCouponsRecord> listPageBySysCouponsRecord(
			SysCouponsRecord clssname) {
		return sysCouponsRecordMapper.listPageBySysCouponsRecord(clssname);
	}

	@Override
	public List<SysCouponsRecord> getAllBySysCouponsRecord(
			SysCouponsRecord clssname) {
		return sysCouponsRecordMapper.getAllBySysCouponsRecord(clssname);
	}

	@Override
	public List<Map<String, Object>> listPageBySysCouponsRecordWithUser(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper
				.listPageBySysCouponsRecordWithUser(clssname);
	}

	@Override
	public List<SysCouponsRecord> getUserCouponsRecord(
			SysCouponsRecord sysCoupons) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.getUserCouponsRecord(sysCoupons);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageBySysCouponsRecordCan(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper
				.listAjaxPageBySysCouponsRecordCan(clssname);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageBySysCouponsRecordExpire(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper
				.listAjaxPageBySysCouponsRecordExpire(clssname);
	}

	@Override
	public List<Map<String, Object>> listAjaxPageBySysCouponsRecordOver(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper
				.listAjaxPageBySysCouponsRecordOver(clssname);
	}

	@Override
	public List<SysCouponsRecord> listBySysCouponsRecordCan(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.listBySysCouponsRecordCan(clssname);
	}

	@Override
	public List<SysCouponsRecord> listBySysCouponsRecordExpire(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.listBySysCouponsRecordExpire(clssname);
	}

	@Override
	public List<SysCouponsRecord> listBySysCouponsRecordOver(
			SysCouponsRecord clssname) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.listBySysCouponsRecordOver(clssname);
	}

	@Override
	public int updateByRegMobile(SysCouponsRecord record) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.updateByRegMobile(record);
	}

	@Override
	public int insertSelectiveT(SysCouponsRecord record) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.insertSelectiveT(record);
	}

	@Override
	public List<Map<String, Object>> listByOrderSnRedBag(
			SysCouponsRecord sysCouponsRecord) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.listByOrderSnRedBag(sysCouponsRecord);
	}

	@Override
	public int selectCountByOrdersn(SysCouponsRecord record) {
		// TODO Auto-generated method stub
		return sysCouponsRecordMapper.selectCountByOrdersn(record);
	}

	@Override
	public String insertRedBag(String ifRmobile, String code, String ordersn,
			String mobile) {
		// TODO Auto-generated method stub
		if (ifRmobile == null || ifRmobile.equals("")) {// 如果以前没有抢过
			String accessToken = null;
			String openId = null;
			if (!"authdeny".equals(code)) {
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(SystemConfig.APPID,
								SystemConfig.APPSECRET, code);
				// 网页授权接口访问凭证
				accessToken = weixinOauth2Token.getAccessToken();
				// 用户标识
				openId = weixinOauth2Token.getOpenId();
			}
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
					openId);
			UserWx userWx = new UserWx();
			userWx.setOpenId(openId);
			userWx = userWxMapper.selectByUserWx(userWx);
			if (userWx != null) {
				userWx.setMobile(mobile);
				userWxMapper.updateByPrimaryKeySelective(userWx);
			} else {
				UserWx userWxl = new UserWx();
				userWxl.setPicUrl(snsUserInfo.getHeadImgUrl());
				userWxl.setNickName(snsUserInfo.getNickname());
				userWxl.setMobile(mobile);
				User user = new User();
				user.setLogin(mobile);
				user = userMapper.selectByUser(user);
				if (user != null) {
					userWxl.setUserId(user.getId());
				}
				userWxl.setOpenId(snsUserInfo.getOpenId());
				userWxMapper.insertSelective(userWxl);// 保存信息
			}
		}
		String url = null;
		SysCouponsRecord sysCouponsRecord = new SysCouponsRecord();
		SysCouponsRecord sysCouponsRecordl = new SysCouponsRecord();
		sysCouponsRecordl.setMobile(mobile);
		sysCouponsRecordl.setOrderSn(ordersn);
		sysCouponsRecordl = sysCouponsRecordMapper
				.selectBySysCouponsRecord(sysCouponsRecordl);
		if (sysCouponsRecordl == null) {// 是否抢过
			SysCouponsConf scc = sysCouponsConfMapper.selectByPrimaryKey(1);
			// 判断红包个数
			SysCouponsRecord record = new SysCouponsRecord();
			record.setOrderSn(ordersn);
			int count = sysCouponsRecordMapper.selectCountByOrdersn(record);// 红包个数
			if (count > scc.getCount()) {
				url = BaseConfig.FX_DOMAIN_WWW
						+ "/wap/wxShare/redBagReceived.html?redPrice=aleradyW&ordersn="
						+ ordersn + "&mobile=" + mobile;
			} else {
				String rangs[] = null;
				if (null != scc) {
					rangs = scc.getPriceRange().split("-");
					String startTime = Tools.date2Str(new Date(), "yyyy-MM-dd")
							+ " 00:00:00";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DAY_OF_MONTH, scc.getTimeRange());
					String endTime = sf.format(c.getTime()) + " 23:59:59";
					sysCouponsRecord.setStartTime(Tools.str2Date(startTime));
					sysCouponsRecord.setEndTime(Tools.str2Date(endTime));
				}
				sysCouponsRecord.setAddtime(new Date());
				sysCouponsRecord.setOrderSn(ordersn);
				BigDecimal pl = new BigDecimal("0");
				sysCouponsRecord.setNeedPrice(pl);
				sysCouponsRecord.setState(1);
				double mini = Double.valueOf(rangs[0]);
				double max = Double.valueOf(rangs[1]);
				double redPrice = getRandomNum(mini, max);// 领取的奖金

				BigDecimal redPr = new BigDecimal(redPrice);
				sysCouponsRecord.setSubstractPrice(redPr);
				sysCouponsRecord.setMobile(mobile);
				User user = new User();
				user.setLogin(mobile);
				user = userMapper.selectByUser(user);
				if (user != null) {
					sysCouponsRecord.setUserId(user.getId());// 保存用户ID
				}
				int reuslt = sysCouponsRecordMapper
						.insertSelectiveT(sysCouponsRecord);// 保存优惠券
				url = BaseConfig.FX_DOMAIN_WWW
						+ "/wap/wxShare/redBagReceived.html?redPrice="
						+ redPrice + "&ordersn=" + ordersn + "&mobile="
						+ mobile;

			}
		} else {
			url = BaseConfig.FX_DOMAIN_WWW
					+ "/wap/wxShare/redBagReceived.html?redPrice=aleradyG&ordersn="
					+ ordersn + "&mobile=" + mobile;
		}
		return url;
	}

	/**
	 * 产生规定范围内的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static double getRandomNum(double min, double max) {
		double res = (Math.random() * (max - min)) + min;

		BigDecimal b = new BigDecimal(res / 1.11);
		double df = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df;
	}

}