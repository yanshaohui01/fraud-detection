package com.hsbc.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 〈功能概述〉<br>
 *
 * @className: AsyncThreadPoolConfig
 * @package: com.hsbc.springboot.config
 * @author: bruce
 * @date: 2025/1/24 14:45
 */
@Configuration
@EnableAsync
public class AsyncThreadPoolConfig {
    @Bean(name = "asyncfraudDetectionExecutor")
    public Executor asyncfraudDetectionExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(5);
        // 最大线程数
        executor.setMaxPoolSize(10);
        // 队列容量
        executor.setQueueCapacity(25);
        // 线程名称前缀
        executor.setThreadNamePrefix("AsyncThread-");
        // 线程池关闭时等待任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 初始化线程池
        executor.initialize();
        return executor;
    }
}
