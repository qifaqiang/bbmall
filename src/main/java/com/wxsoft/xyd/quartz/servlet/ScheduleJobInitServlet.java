package com.wxsoft.xyd.quartz.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxsoft.xyd.quartz.model.SysScheduleJob;
import com.wxsoft.xyd.quartz.service.QuartzManagerService;
import com.wxsoft.xyd.quartz.service.SysScheduleJobService;

public class ScheduleJobInitServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SysScheduleJobService sysScheduleJobService;
	
	private QuartzManagerService quartzManagerService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext appCtx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		sysScheduleJobService = (SysScheduleJobService) BeanFactoryUtils
				.beanOfTypeIncludingAncestors(appCtx, SysScheduleJobService.class);
		quartzManagerService = (QuartzManagerService) BeanFactoryUtils
				.beanOfTypeIncludingAncestors(appCtx, QuartzManagerService.class);
		SysScheduleJob paraJob = new SysScheduleJob();
		paraJob.setJobStatus("(1)");
		List<SysScheduleJob> scheduleJobList = sysScheduleJobService.getAllBySysScheduleJob(paraJob);
		if(null != scheduleJobList && scheduleJobList.size()>0){
			for (SysScheduleJob sysScheduleJob : scheduleJobList) {
				try {
					quartzManagerService.addJob(sysScheduleJob);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("初始化定时任务存在异常。。。。。。。。。。");
				}
			}
		}
		
	}
}
