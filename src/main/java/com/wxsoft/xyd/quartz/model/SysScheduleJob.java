package com.wxsoft.xyd.quartz.model;

import com.wxsoft.framework.bean.BaseBean;
import com.wxsoft.xyd.system.model.CommonPage;

/***
 * 计划任务信息
 * @author fafa
 *
 */
public class SysScheduleJob extends BaseBean{
	public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";
    
    private static final long serialVersionUID = 1L;
    
    private CommonPage page;
    private Integer jobId;  
    private String createTime;//创建时间
    private String updateTime;//更新时间
    /** 
     * 任务名称 
     */  
    private String jobName;
    private String name;//这个名字是用来在列表中显示的,防止中文不可以
    /** 
     * 任务分组名称 
     */  
    private String jobGroupName;  
    /** 
     * 任务状态 是否启动任务 
     */  
    private String jobStatus;//0 停止  1 正则运行  2 暂停
    /** 
     * cron表达式 
     */  
    private String cronExpression;  
    /** 
     * 描述 
     */  
    private String description;  
    /** 
     * 任务执行时调用哪个类的方法 包名+类名 
     */  
    private String beanClass;  
    /** 
     * 任务是否有状态 
     */  
    private String isConcurrent;  
    /** 
     * spring bean 
     */  
    private String springId;  
    /** 
     * 任务调用的方法名 
     */  
    private String methodName;
    
    private Integer companyId;
    
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroupName() {
		return jobGroupName;
	}
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public CommonPage getPage() {
		return page;
	}
	public void setPage(CommonPage page) {
		this.page = page;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsConcurrent() {
		return isConcurrent;
	}
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	public String getSpringId() {
		return springId;
	}
	public void setSpringId(String springId) {
		this.springId = springId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public static String getStatusRunning() {
		return STATUS_RUNNING;
	}
	public static String getStatusNotRunning() {
		return STATUS_NOT_RUNNING;
	}
	public static String getConcurrentIs() {
		return CONCURRENT_IS;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public static String getConcurrentNot() {
		return CONCURRENT_NOT;
	}
}
