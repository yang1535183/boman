package com.boman.upms.rpc.factory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * 异步任务管理器
 * @author Administrator 2019/3/06 16:32
 */
public class AsyncManager {

	// 延迟10毫秒操作
	private final int OPERATE_DELAY_TIME = 4000;
	
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
	
	private static AsyncManager me = new AsyncManager();
	
	public static AsyncManager me() {
		return me;
	}
	
	/**
	 * 开始执行异步任务
	 */
	public void execute(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}
}