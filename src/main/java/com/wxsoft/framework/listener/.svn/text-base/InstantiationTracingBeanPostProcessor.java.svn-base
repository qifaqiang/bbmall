package com.wxsoft.framework.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.wxsoft.framework.config.BaseConfig;
import com.wxsoft.xyd.prod.model.ProductBasic;
import com.wxsoft.xyd.prod.service.ProductBasicService;
import com.wxsoft.xyd.system.model.SysProportion;
import com.wxsoft.xyd.system.service.SysProportionService;

public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private SysProportionService sysProportionService;
	@Autowired
	private ProductBasicService productBasicService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
		if (event.getApplicationContext().getParent() == null) {// root
																// application
																// context
																// 没有parent，他就是老大.
			SysProportion sp = sysProportionService.selectByPrimaryKey(1);// 获取系统配置
			BaseConfig.sysProportion = sp;// 把配置文件放在变量中

			Map<Integer, ProductBasic> pbs = new HashMap<Integer, ProductBasic>(); // 商品字典表
			List<ProductBasic> pblist = productBasicService
					.getAllByProductBasic(null);
			for (ProductBasic productBasic : pblist) {
				pbs.put(productBasic.getId(), productBasic);
			}
			BaseConfig.productBasicMap = pbs;

		}
	}
}