package com.hsbc.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 〈功能概述〉<br>
 *
 * @className: CommonConfig
 * @package: com.hsbc.springboot.config
 * @author: bruce
 * @date: 2025/1/24 14:40
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "syncfraudDetectionExecutor")
    public Executor syncfraudDetectionExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(10);
        // 队列容量
        executor.setQueueCapacity(25);
        // 线程名称前缀
        executor.setThreadNamePrefix("SyncThread-");
        // 线程池关闭时等待任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 初始化线程池
        executor.initialize();
        return executor;
    }
}
