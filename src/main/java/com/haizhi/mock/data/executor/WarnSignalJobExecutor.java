package com.haizhi.mock.data.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

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
@Component
@Slf4j
public class WarnSignalJobExecutor implements MockDataExecutor {

    @Autowired
    @Qualifier("warnSignalExecutor")
    private ExecutorService warnSignalExecutor;

    @Value("${per_thread_process_data_num:500}")
    private Integer perThreadProcessDataNum;

    @Override
    public void mockData(@NonNull Integer totalMockDataNum) {
        int maximumPoolSize = ((ThreadPoolExecutor) warnSignalExecutor).getMaximumPoolSize();
        int perLoopMockDataNum = maximumPoolSize * perThreadProcessDataNum;
        List<Integer> mockDataList = new ArrayList<>(totalMockDataNum < perLoopMockDataNum ? totalMockDataNum : perLoopMockDataNum);
        AtomicInteger executeTime = new AtomicInteger();
        log.info("perLoopMockDataNum: {}", perLoopMockDataNum);

        IntStream.rangeClosed(1, totalMockDataNum)
                .forEach(i -> {
                    mockDataList.add(i);

                    if (mockDataList.size() == perLoopMockDataNum) {
                        log.info("executeTime: {}", executeTime.addAndGet(1));
                        mockDataList.clear();
                    }
                });

        if (mockDataList.size() > 0) {
            log.info("chunk executeTime: {}", executeTime.addAndGet(1));
            mockDataList.clear();
        }
    }

}
