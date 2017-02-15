package com.wxsoft.xyd.quartz.service.impl;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wxsoft.xyd.quartz.model.SysScheduleJob;
import com.wxsoft.xyd.quartz.mapper.SysScheduleJobMapper;
import com.wxsoft.xyd.quartz.service.QuartzManagerService;
import com.wxsoft.xyd.quartz.service.SysScheduleJobService;

@Service("sysScheduleJobService")
public class SysScheduleJobServiceImpl implements SysScheduleJobService {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SysScheduleJobMapper sysScheduleJobMapper;
	
	@Autowired
	private QuartzManagerService quartzManagerService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int flag = 0 ;
		
		SysScheduleJob job = sysScheduleJobMapper.selectByPrimaryKey(id);
		if(null != job){
			try {
				quartzManagerService.deleteJob(job);//删除一个job
				//定时任务添加成功再记录在数据库中
				flag = sysScheduleJobMapper.deleteByPrimaryKey(id);
				
			}catch (Exception e) {
				e.printStackTrace();
				flag = 0;
			}
		}else{
			flag = 0;
		}
		
		return flag;
		
	}
	
	@Override
	public int updatePauseOne(Integer id) {
		int flag = 0 ;
		
		SysScheduleJob job = sysScheduleJobMapper.selectByPrimaryKey(id);
		if(null != job){
			try {
				quartzManagerService.pauseJob(job);//暂停一个任务
				
				job.setJobStatus("2");//暂停状态
				job.setUpdateTime(sdf.format(new Date()));
				//定时任务添加成功再记录在数据库中
				flag = sysScheduleJobMapper.updateByPrimaryKeySelective(job);
				
			}catch (Exception e) {
				e.printStackTrace();
				flag = 0;
			}
		}else{
			flag = 0;
		}
		
		return flag;
		
	}
	
	@Override
	public int updateresumeOne(Integer id) {
		int flag = 0 ;
		
		SysScheduleJob job = sysScheduleJobMapper.selectByPrimaryKey(id);
		if(null != job){
			try {
				quartzManagerService.resumeJob(job);//恢复一个Job
				
				job.setJobStatus("1");//运行状态
				job.setUpdateTime(sdf.format(new Date()));
				//定时任务添加成功再记录在数据库中
				flag = sysScheduleJobMapper.updateByPrimaryKeySelective(job);
				
			}catch (Exception e) {
				e.printStackTrace();
				flag = 0;
			}
		}else{
			flag = 0;
		}
		
		return flag;
		
	}
	
	@Override
	public int updateRestartOne(Integer id) {
		int flag = 0 ;
		
		SysScheduleJob job = sysScheduleJobMapper.selectByPrimaryKey(id);
		if(null != job){
			try {
				quartzManagerService.updateJobCron(job);
				
				job.setJobStatus("1");//运行状态
				job.setUpdateTime(sdf.format(new Date()));
				//定时任务添加成功再记录在数据库中
				flag = sysScheduleJobMapper.updateByPrimaryKeySelective(job);
				
			}catch (Exception e) {
				e.printStackTrace();
				flag = 0;
			}
		}else{
			flag = 0;
		}
		
		return flag;
		
	}
	
	//添加任务
	@Override
	public int insertSelective(SysScheduleJob record) {
		int flag = 0;
		record.setCreateTime(sdf.format(new Date()));
		record.setJobName(record.getJobGroupName());//把分组名也作为任务名
		record.setBeanClass("com.wxsoft.xyd.quartz.jobs."+record.getJobGroupName());//分组名即具体的任务类名 +要加上包名 作为执行类
		
		try {
			if("1".equals(record.getJobStatus())){
				quartzManagerService.addJob(record);//添加定时任务
			}
			//定时任务添加成功再记录在数据库中
			flag = sysScheduleJobMapper.insertSelective(record);
			
		}catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		}
		
		return flag;
	}

	@Override
	public int updateByPrimaryKeySelective(SysScheduleJob record) {
		int flag = 0;
		record.setUpdateTime(sdf.format(new Date()));
		record.setJobName(record.getJobGroupName());//把分组名也作为任务名
		record.setBeanClass("com.wxsoft.xyd.quartz.jobs."+record.getJobGroupName());//分组名即具体的任务类名 +要加上包名 作为执行类
		
		try {
			if("1".equals(record.getJobStatus())){
				quartzManagerService.updateJobCron(record);//更新任务是表达式
			}else{
				quartzManagerService.pauseJob(record);//暂停一个任务
			}
			//定时任务修改成功再记录在数据库中
			flag = sysScheduleJobMapper.updateByPrimaryKeySelective(record);
			
		}catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		}
		
		return flag;
	}

	@Override
	public SysScheduleJob selectByPrimaryKey(Integer id) {
		return sysScheduleJobMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysScheduleJob selectBySysScheduleJob(SysScheduleJob record) {
		return sysScheduleJobMapper.selectBySysScheduleJob(record);
	}

	@Override
	public List<SysScheduleJob> listPageBySysScheduleJob(SysScheduleJob clssname) {
		return sysScheduleJobMapper.listPageBySysScheduleJob(clssname);
	}

	@Override
	public List<SysScheduleJob> getAllBySysScheduleJob(SysScheduleJob clssname) {
		return sysScheduleJobMapper.getAllBySysScheduleJob(clssname);
	}

	@Override
	public void updateAutoAcceptOrder() {
		sysScheduleJobMapper.updateAutoAcceptOrder();
	}

	@Override
	public void updateAutoCancelOrder() {
		sysScheduleJobMapper.updateAutoCancelOrder();
	}
	@Override
	public List<Map<String,Integer>> updateAutoOverOrder() {
		return sysScheduleJobMapper.updateAutoOverOrder();
	}
	
	@Override
	public void updateAutoClearSms() {
		sysScheduleJobMapper.updateAutoClearSms();
	}
	
	@Override
	public void updateAutoCouponsoverdue() {
		sysScheduleJobMapper.updateAutoCouponsoverdue();
	}
}