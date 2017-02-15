/**   
 * @文件名称: CompanyServiceImpl.java
 * @类路径: com.wxltsoft.fxshop.common.service.impl
 * @描述: TODO
 * @作者：kyz
 * @时间：2013-3-29 下午05:50:09  
 */

package com.wxsoft.xyd.quartz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.wxsoft.xyd.quartz.model.SysScheduleJob;
import com.wxsoft.xyd.quartz.service.QuartzManagerService;

@Service("quartzManagerService")
public class QuartzManagerServiceImpl implements QuartzManagerService {
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	private static final Logger logger = LoggerFactory
			.getLogger(QuartzManagerServiceImpl.class);
	private static String JOB_GROUP_NAME = "EMALL_JOBGROUP_NAME";// 默认的任务分组名称
	private static String TRIGGER_GROUP_NAME = "EMALL_TRIGGERGROUP_NAME";// 默认的触发器组名

	/***
	 * 添加任务
	 * 
	 * @throws ClassNotFoundException
	 */
	@Override
	public void addJob(SysScheduleJob job) throws Exception {
		if (job == null
				|| !SysScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
			return;
		}
		if (StringUtils.isBlank(job.getJobGroupName())
				|| "null".equals(job.getJobGroupName())) {
			job.setJobGroupName(this.TRIGGER_GROUP_NAME);// 把默认的触发器分组名付给
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		logger.info(scheduler + "............添加定时任务");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
				job.getJobGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = null;
			Object object = null;
			if (StringUtils.isNotBlank(job.getBeanClass())) {
				clazz = Class.forName(job.getBeanClass());
				object = clazz.newInstance();

				if (object == null) {
					logger.info("任务名称 = [" + job.getName() + "---"
							+ job.getJobName()
							+ "]---------------未启动成功，请检查是否配置正确！！！");
					return;
				}

				clazz = object.getClass();
				JobDetail jobDetail = JobBuilder.newJob(clazz)
						.withIdentity(job.getJobName(), job.getJobGroupName())
						.build();

				// jobDetail.getJobDataMap().put("scheduleJob", job);

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());

				trigger = TriggerBuilder.newTrigger()
						.withIdentity(job.getJobName(), job.getJobGroupName())
						.withSchedule(scheduleBuilder).build();

				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				logger.info("任务没有指定具体的任务类---------------未启动成功，请检查是否配置正确！！！");
				return;
			}
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(SysScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if (StringUtils.isBlank(scheduleJob.getJobGroupName())
				|| "null".equals(scheduleJob.getJobGroupName())) {
			scheduleJob.setJobGroupName(this.TRIGGER_GROUP_NAME);// 把默认的触发器分组名付给
		}

		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroupName());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(SysScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if (StringUtils.isBlank(scheduleJob.getJobGroupName())
				|| "null".equals(scheduleJob.getJobGroupName())) {
			scheduleJob.setJobGroupName(this.TRIGGER_GROUP_NAME);// 把默认的触发器分组名付给
		}
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroupName());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(SysScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if (StringUtils.isBlank(scheduleJob.getJobGroupName())
				|| "null".equals(scheduleJob.getJobGroupName())) {
			scheduleJob.setJobGroupName(this.TRIGGER_GROUP_NAME);// 把默认的触发器分组名付给
		}
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroupName());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runAJobNow(SysScheduleJob scheduleJob)
			throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if (StringUtils.isBlank(scheduleJob.getJobGroupName())
				|| "null".equals(scheduleJob.getJobGroupName())) {
			scheduleJob.setJobGroupName(this.TRIGGER_GROUP_NAME);// 把默认的触发器分组名付给
		}
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroupName());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(SysScheduleJob scheduleJob)
			throws Exception {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if (StringUtils.isBlank(scheduleJob.getJobGroupName())
				|| "null".equals(scheduleJob.getJobGroupName())) {
			scheduleJob.setJobGroupName(this.TRIGGER_GROUP_NAME);// 把默认的触发器分组名付给
		}
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = null;
			Object object = null;
			if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();

				if (object == null) {
					logger.info("任务名称 = [" + scheduleJob.getName() + "---"
							+ scheduleJob.getJobName()
							+ "]---------------未启动成功，请检查是否配置正确！！！");
					return;
				}

				clazz = object.getClass();
				JobDetail jobDetail = JobBuilder.newJob(clazz)
						.withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroupName())
						.build();

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(scheduleJob.getCronExpression());

				trigger = TriggerBuilder.newTrigger()
						.withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroupName())
						.withSchedule(scheduleBuilder).build();

				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				logger.info("任务没有指定具体的任务类---------------未启动成功，请检查是否配置正确！！！");
			}
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(scheduleJob.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<SysScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<SysScheduleJob> jobList = new ArrayList<SysScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler
					.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				SysScheduleJob job = new SysScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroupName(jobKey.getGroup());
				;
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler
						.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<SysScheduleJob> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler
				.getCurrentlyExecutingJobs();
		List<SysScheduleJob> jobList = new ArrayList<SysScheduleJob>(
				executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			SysScheduleJob job = new SysScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroupName(jobKey.getGroup());
			;
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler
					.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * @Description:启动所有定时任务
	 * 
	 */
	public void startAllJobs() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.start();
	}

	/**
	 * @Description:关闭所有定时任务
	 * 
	 */
	public void shutdownAllJobs() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.shutdown();
	}
}