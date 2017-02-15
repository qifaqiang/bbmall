package com.wxsoft.xyd.quartz.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.wxsoft.xyd.quartz.service.SysScheduleJobService;
/**
 * 优惠券自动失效的定时任务
 * @author fafa
 *
 */
public class AutoCouponsoverdueJob extends SpringBeanAutowiringSupport implements Job{

	@Autowired
	private SysScheduleJobService sysScheduleJobService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static int flag = 0;//未开始状态
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("******* 优惠券自动失效的定时任务   >>>>>>>>> 已启动");
		if(flag == 0){//说明可以执行
			flag = 1;//任务开始执行
			logger.info("******* 优惠券自动失效的定时任务   >>>>>>>>> 开始调用存储过程处理业务逻辑*****");
			try {
				sysScheduleJobService.updateAutoCouponsoverdue();
				
				flag = 0;//执行结束改成0
				logger.info("******* 优惠券自动失效的定时任务   >>>>>>>>>本次任务执行结束 *****");
			} catch (Exception e) {
				e.printStackTrace();
				flag = 0;//执行结束改成0
				logger.info("******* 优惠券自动失效的定时任务   >>>>>>>>>调用存储过程处理业务逻辑出现异常，任务执行结束  *****");
			}
		}else{
			logger.info("******* 优惠券自动失效的定时任务   >>>>>>>>>上一次的执行还没有结束，默认跳过本次执行  *****");
		}
	}

}
