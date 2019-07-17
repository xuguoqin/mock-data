package com.haizhi.mock.data.framework.executor.impl;

import com.google.common.collect.Lists;
import com.haizhi.mock.data.framework.Cleaner;
import com.haizhi.mock.data.framework.Mocker;
import com.haizhi.mock.data.framework.Writer;
import com.haizhi.mock.data.framework.executor.MockDataExecutor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author xuguoqin
 * @date 2019/7/16 5:49 PM
 */
@Slf4j
@Setter
public class DefaultMockDataExecutor<T> implements MockDataExecutor<T>, InitializingBean {

    private Executor defaultExecutorService;
    private Cleaner cleaner;
    private Integer perThreadProcessDataNum;
    private Mocker<T> mocker;
    private Writer<T> writer;

    public DefaultMockDataExecutor() {
        perThreadProcessDataNum = 500;
    }

    @Override
    public void mockData(int totalMockDataNum, boolean truncate) {
        long start = System.currentTimeMillis();
        int executorThreadNum = this.getExecutorMaximumPoolSize();
        int perLoopMockDataNum = executorThreadNum * perThreadProcessDataNum;
        List<T> mockDataList = new ArrayList<>(totalMockDataNum < perLoopMockDataNum ? totalMockDataNum : perLoopMockDataNum);
        AtomicInteger executeTime = new AtomicInteger();

        this.logBatchTaskInfo(totalMockDataNum, executorThreadNum, perLoopMockDataNum, truncate);
        this.truncateTableIfNeed(truncate);
        IntStream.rangeClosed(1, totalMockDataNum)
                .forEach(i -> {
                    T mockData = mocker.mockData(i);
                    mockDataList.add(mockData);

                    if (mockDataList.size() == perLoopMockDataNum) {
                        log.info("executeTime: {}", executeTime.addAndGet(1));
                        List<List<T>> partitionMockDataList = Lists.partition(mockDataList, perThreadProcessDataNum);
                        CountDownLatch countDownLatch = new CountDownLatch(executorThreadNum);
                        partitionMockDataList.forEach(perThreadProcessDataList ->
                                defaultExecutorService.execute(() -> {
                                    try {
                                        writer.write(perThreadProcessDataList);
                                    } catch (Exception e) {
                                        log.error(e.getMessage(), e);
                                    } finally {
                                        countDownLatch.countDown();
                                    }
                                })
                        );

                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            log.error("批量任务线程中断: " + e.getMessage(), e);
                            Thread.currentThread().interrupt();
                        }
                        mockDataList.clear();
                    }
                });

        if (mockDataList.size() > 0) {
            log.info("chunk executeTime: {}", executeTime.addAndGet(1));
            CountDownLatch countDownLatch = new CountDownLatch(1);
            defaultExecutorService.execute(() -> {
                try {
                    writer.write(mockDataList);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                log.error("批量任务线程中断: " + e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
            mockDataList.clear();
        }

        log.info("单次任务总共耗时: {}ms", System.currentTimeMillis() - start);

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(mocker, "The Mocker has not been set");
        Assert.notNull(writer, "The Writer has not been set");
        Assert.notNull(cleaner, "The Cleaner has not been set");
        if (defaultExecutorService == null) {
            log.info("The DefaultExecutorService has been set to SyncTaskExecutor");
            defaultExecutorService = new SyncTaskExecutor();
        }
    }

    private void logBatchTaskInfo(int totalMockDataNum, int executorThreadNum, int perLoopMockDataNum, boolean truncate) {
        log.info("");
        log.info("---------- batch info ----------");
        log.info("totalMockDataNum: {}", totalMockDataNum);
        log.info("是否需要truncate: {}", truncate);
        log.info("executorThreadNum: {}", executorThreadNum);
        log.info("perLoopMockDataNum: {}", perLoopMockDataNum);
        log.info("loop_time: {}", (int) Math.ceil(totalMockDataNum * 1.0 / perLoopMockDataNum));
        log.info("--------------------------------");
        log.info("");
    }


    private void truncateTableIfNeed(boolean truncate) {
        if (truncate) {
            long start = System.currentTimeMillis();
            log.info("");
            log.info("---------- truncate info ----------");
            log.info("truncate table begin...");
            cleaner.clean();
            log.info("truncate table end... 耗时 {}ms", System.currentTimeMillis() - start);
            log.info("--------------------------------");
            log.info("");
        }
    }

    private int getExecutorMaximumPoolSize() {
        int maximumPoolSize = 1;
        if (defaultExecutorService instanceof ThreadPoolExecutor) {
            maximumPoolSize = ((ThreadPoolExecutor) defaultExecutorService).getMaximumPoolSize();
        }
        return maximumPoolSize;
    }
}
