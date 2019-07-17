package com.haizhi.mock.data.framework.executor.impl;

import com.haizhi.mock.data.framework.Cleaner;
import com.haizhi.mock.data.framework.Mocker;
import com.haizhi.mock.data.framework.Writer;
import com.haizhi.mock.data.framework.executor.MockDataExecutor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author xuguoqin
 * @date 2019/7/16 5:49 PM
 */
@Slf4j
@Setter
public class DefaultMockDataExecutor<T> implements MockDataExecutor {

    private ExecutorService defaultExecutorService;
    private Cleaner cleaner;
    private Integer perThreadProcessDataNum;
    private Mocker<T> mocker;
    private Writer<T> writer;

    public DefaultMockDataExecutor() {
        perThreadProcessDataNum = 500;
    }

    @Override
    public void mockData(int totalMockDataNum, boolean truncate) {

        Assert.notNull(mocker, "mocker doesn't init");
        Assert.notNull(writer, "writer doesn't init");
        Assert.notNull(defaultExecutorService, "defaultExecutorService doesn't init");
        Assert.notNull(cleaner, "cleaner doesn't init");

        long start = System.currentTimeMillis();
        int maximumPoolSize = ((ThreadPoolExecutor) defaultExecutorService).getMaximumPoolSize();
        int perLoopMockDataNum = maximumPoolSize * perThreadProcessDataNum;
        List<T> mockDataList = new ArrayList<>(totalMockDataNum < perLoopMockDataNum ? totalMockDataNum : perLoopMockDataNum);
        AtomicInteger executeTime = new AtomicInteger();

        logBatchTaskInfo(totalMockDataNum, maximumPoolSize, perLoopMockDataNum, truncate);

        truncateTableIfNeed(truncate);

        IntStream.rangeClosed(1, totalMockDataNum)
                .forEach(i -> {
                    T mockData = mocker.mockData(i);
                    mockDataList.add(mockData);

                    if (mockDataList.size() == perLoopMockDataNum) {
                        log.info("executeTime: {}", executeTime.addAndGet(1));
                        writer.write(mockDataList);
                        mockDataList.clear();
                    }
                });

        if (mockDataList.size() > 0) {
            log.info("chunk executeTime: {}", executeTime.addAndGet(1));
            writer.write(mockDataList);
            mockDataList.clear();
        }

        log.info("单次任务总共耗时: {}ms", System.currentTimeMillis() - start);

    }

    private void logBatchTaskInfo(int totalMockDataNum, int maximumPoolSize, int perLoopMockDataNum, boolean truncate) {
        log.info("");
        log.info("---------- batch info ----------");
        log.info("totalMockDataNum: {}", totalMockDataNum);
        log.info("是否需要truncate: {}", truncate);
        log.info("maximumPoolSize: {}", maximumPoolSize);
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
}
