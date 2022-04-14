
package com.hznu.xdd.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池装配类
 * @author Misaki
 */
@Slf4j
@Configuration
@Data
@EnableAsync
public class AsyncTaskExecutePool implements AsyncConfigurer {
    @Value("${task.pool.core-pool-size}")
    public static int corePoolSize;

    @Value("${task.pool.max-pool-size}")
    public static int maxPoolSize;

    @Value("${task.pool.keep-alive-seconds}")
    public static int keepAliveSeconds;

    @Value("${task.pool.queue-capacity}")
    public static int queueCapacity;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //队列容量
        executor.setQueueCapacity(queueCapacity);
        //活跃时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        //线程名字前缀
        executor.setThreadNamePrefix("xdd-async-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("===="+throwable.getMessage()+"====", throwable);
            log.error("exception method:"+method.getName());
        };
    }
}
