package com.haizhi.mock.data.executor;

import org.springframework.lang.NonNull;

/**
 * @author xuguoqin
 * @date 2019/7/16 5:54 PM
 */
public interface MockDataExecutor {

    /**
     * 构建数据
     * @param totalMockDataNum 总共需要mock的数据量
     */
    void mockData(@NonNull Integer totalMockDataNum);

}
