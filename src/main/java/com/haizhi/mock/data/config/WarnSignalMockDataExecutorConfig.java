package com.haizhi.mock.data.config;

import com.haizhi.mock.data.entity.PrivateWarnSignalDO;
import com.haizhi.mock.data.entity.PublicWarnSignalDO;
import com.haizhi.mock.data.framework.Cleaner;
import com.haizhi.mock.data.framework.Mocker;
import com.haizhi.mock.data.framework.Writer;
import com.haizhi.mock.data.framework.executor.impl.DefaultMockDataExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

/**
 * @author xuguoqin
 * @date 2019/7/17 5:08 PM
 */
@Configuration
public class WarnSignalMockDataExecutorConfig {

    @Value("${warnSignal.perThreadProcessDataNum:500}")
    private Integer perThreadProcessDataNum;

    @Bean
    public DefaultMockDataExecutor publicWarnSignalMockDataExecutor(@Qualifier("publicWarnSignalMocker") Mocker<PublicWarnSignalDO> mocker
            , @Qualifier("publicWarnSignalWriter") Writer<PublicWarnSignalDO> publicWarnSignalWriter
            , @Qualifier("publicWarnSignalCleaner") Cleaner warnSignalCleaner
            , @Qualifier("warnSignalExecutor") ExecutorService warnSignalExecutor) {
        DefaultMockDataExecutor<PublicWarnSignalDO> publicWarnSignalMockDataExecutor = new DefaultMockDataExecutor<>();
        publicWarnSignalMockDataExecutor.setMocker(mocker);
        publicWarnSignalMockDataExecutor.setWriter(publicWarnSignalWriter);
        publicWarnSignalMockDataExecutor.setCleaner(warnSignalCleaner);
        publicWarnSignalMockDataExecutor.setPerThreadProcessDataNum(perThreadProcessDataNum);
        publicWarnSignalMockDataExecutor.setDefaultExecutorService(warnSignalExecutor);
        return publicWarnSignalMockDataExecutor;
    }

    @Bean
    public DefaultMockDataExecutor privateWarnSignalMockDataExecutor(@Qualifier("privateWarnSignalMocker") Mocker<PrivateWarnSignalDO> privateWarnSignalMocker
            , @Qualifier("privateWarnSignalWriter") Writer<PrivateWarnSignalDO> privateWarnSignalWriter
            , @Qualifier("privateWarnSignalCleaner") Cleaner privateWarnSignalCleaner
            , @Qualifier("warnSignalExecutor") ExecutorService warnSignalExecutor) {
        DefaultMockDataExecutor<PrivateWarnSignalDO> privateWarnSignalMockDataExecutor = new DefaultMockDataExecutor<>();
        privateWarnSignalMockDataExecutor.setMocker(privateWarnSignalMocker);
        privateWarnSignalMockDataExecutor.setWriter(privateWarnSignalWriter);
        privateWarnSignalMockDataExecutor.setCleaner(privateWarnSignalCleaner);
        privateWarnSignalMockDataExecutor.setDefaultExecutorService(warnSignalExecutor);
        privateWarnSignalMockDataExecutor.setPerThreadProcessDataNum(perThreadProcessDataNum);
        return privateWarnSignalMockDataExecutor;
    }
}
