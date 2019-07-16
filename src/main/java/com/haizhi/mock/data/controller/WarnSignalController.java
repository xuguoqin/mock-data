package com.haizhi.mock.data.controller;

import com.haizhi.mock.data.executor.MockDataExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 风险信号
 *
 * @author xuguoqin
 * @date 2019/7/16 5:20 PM
 */
@RestController
@RequestMapping("/warnSignal")
@Slf4j
public class WarnSignalController {

    @Autowired
    @Qualifier("warnSignalJobExecutor")
    private MockDataExecutor warnSignalJobExecutor;


    @RequestMapping("/mockData")
    public void mockData(@RequestParam Integer totalMockDataNum) {
        warnSignalJobExecutor.mockData(totalMockDataNum);
    }

}
