package com.haizhi.mock.data.framework;

/**
 * @author xuguoqin
 * @date 2019/7/17 4:37 PM
 */
public interface Mocker<T> {

    /**
     * mock数据
     *
     * @return
     */
    T mockData(int i);
}
