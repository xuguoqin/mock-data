package com.haizhi.mock.data.framework.executor;

/**
 * @author xuguoqin
 * @date 2019/7/16 5:54 PM
 */
public interface MockDataExecutor<T> {

    /**
     * 构建数据
     *
     * @param totalMockDataNum 总共需要mock的数据量
     * @param truncate         是否需要删除之前的数据 true表示删除 false表示不需要哦删除
     */
    void mockData(int totalMockDataNum, boolean truncate);

}
