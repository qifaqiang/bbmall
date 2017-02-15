package com.wxsoft.xyd.quartz.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.wxsoft.xyd.quartz.model.SysScheduleJob;

/**
 * 定时任务的总管理器，控制增加任务、删除任务、暂停任务等等。。。。
 * 
 * @author fafa
 *
 */
public interface QuartzManagerService {

	/** 
     * 增加一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */ 
	public void addJob(SysScheduleJob scheduleJob) throws Exception;
	
	/** 
     * 暂停一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void pauseJob(SysScheduleJob scheduleJob) throws SchedulerException;
    
    /** 
     * 恢复一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void resumeJob(SysScheduleJob scheduleJob) throws SchedulerException;
  
    /** 
     * 删除一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void deleteJob(SysScheduleJob scheduleJob) throws SchedulerException;
  
    /** 
     * 立即执行job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void runAJobNow(SysScheduleJob scheduleJob) throws SchedulerException;
  
    /** 
     * 更新job时间表达式 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void updateJobCron(SysScheduleJob scheduleJob) throws Exception;
    
    /**  
    * 获取所有计划中的任务列表  
    *   
    * @return  
    * @throws SchedulerException  
    */  
   public List<SysScheduleJob> getAllJob() throws SchedulerException;
 
   /** 
    * 所有正在运行的job 
    *  
    * @return 
    * @throws SchedulerException 
    */  
   public List<SysScheduleJob> getRunningJob() throws SchedulerException;
   
   /** 
    * @Description:启动所有定时任务 
    *  
    *  
    */  
   public void startAllJobs() throws SchedulerException;
 
   /** 
    * @Description:关闭所有定时任务 
    *  
    */  
   public void shutdownAllJobs() throws SchedulerException;
	
}
