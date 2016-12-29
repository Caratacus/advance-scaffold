package com.advance.scaffold.service;

import com.advance.scaffold.model.SysScheduleJob;
import com.baomidou.mybatisplus.service.IService;

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
	 */
	void saveScheduleJob(SysScheduleJob scheduleJob);

	/**
	 * 更新定时任务
	 */
	void updateScheduleJob(SysScheduleJob scheduleJob);

	/**
	 * 批量删除定时任务
	 */
	void deleteBatchScheduleJob(Long[] jobIds);

	/**
	 * 批量更新定时任务状态
	 */
	void updateBatchScheduleJob(Long[] jobIds, int status);

	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);

	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);

	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
}
