package com.haizhi.mock.data.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author xuguoqin
 * @date 2019/7/16 5:08 PM
 */
@Configuration
@Slf4j
public class WarnSignalExecutorConfig {

    @Value("${warnSignal.corePoolSize:}")
    private Integer warnSignalCorePoolSize;

    @Value("${warnSignal.maxPoolSize:}")
    private Integer warnSignalMaxPoolSize;

    @Value("${warnSignal.keepAliveTime:60}")
    private Integer warnSignalKeepAliveTime;

    @Bean
    public ExecutorService warnSignalExecutor() {

        warnSignalCorePoolSize = warnSignalMaxPoolSize == null ? Runtime.getRuntime().availableProcessors() * 2 + 1 : warnSignalMaxPoolSize;
        warnSignalMaxPoolSize = warnSignalCorePoolSize;

        log.info("warnSignalCorePoolSize: {}  warnSignalMaxPoolSize: {}", warnSignalCorePoolSize, warnSignalMaxPoolSize);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("pool-warn-signal-%d")
                .build();

        return new ThreadPoolExecutor(warnSignalCorePoolSize, warnSignalMaxPoolSize, warnSignalKeepAliveTime, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(), namedThreadFactory);
    }
}
