package com.haizhi.mock.data.controller;

import com.haizhi.mock.data.framework.executor.MockDataExecutor;
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
    @Qualifier("publicWarnSignalMockDataExecutor")
    private MockDataExecutor publicWarnSignalMockDataExecutor;

    @Autowired
    @Qualifier("privateWarnSignalMockDataExecutor")
    private MockDataExecutor privateWarnSignalMockDataExecutor;

    @RequestMapping("/mockPublicWarnSignalData")
    public void mockPublicWarnSignalData(@RequestParam Integer totalMockDataNum, @RequestParam(defaultValue = "false") Boolean truncate) {
        publicWarnSignalMockDataExecutor.mockData(totalMockDataNum, truncate);
    }

    @RequestMapping("/mockPrivateWarnSignalData")
    public void mockPrivateWarnSignalData(@RequestParam Integer totalMockDataNum, @RequestParam(defaultValue = "false") Boolean truncate) {
        privateWarnSignalMockDataExecutor.mockData(totalMockDataNum, truncate);
    }

}
