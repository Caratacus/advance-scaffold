package com.advance.scaffold.service;

import com.advance.scaffold.model.SysScheduleJob;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
public interface SysScheduleJobService extends IService<SysScheduleJob> {

	/**
	 * 保存定时任务
	 * 
	 * @param scheduleJob
	 */
	public void insertJob(SysScheduleJob scheduleJob);

	/**
	 * 更新定时任务
	 * 
	 * @param scheduleJob
	 */
	public void updateJob(SysScheduleJob scheduleJob);

	/**
	 * 批量删除定时任务
	 * 
	 * @param jobIds
	 */
	public void deleteBatchJob(List<Long> jobIds);

	/**
	 * 批量更新定时任务状态
	 * 
	 * @param jobIds
	 * @param status
	 */
	public void updateBatchJob(List<Long> jobIds, int status);

	/**
	 * 立即执行
	 * 
	 * @param jobIds
	 */
	public void runJob(List<Long> jobIds);

	/**
	 * 暂停运行
	 * 
	 * @param jobIds
	 */
	public void pauseJob(List<Long> jobIds);

	/**
	 * 恢复运行
	 * 
	 * @param jobIds
	 */
	public void resumeJob(List<Long> jobIds);
}
