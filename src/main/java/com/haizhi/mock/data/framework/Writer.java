package com.haizhi.mock.data.framework;

import java.util.List;

/**
 * @author xuguoqin
 * @date 2019/7/17 4:47 PM
 */
public interface Writer<T> {

    void write(List<T> dataList);
}
