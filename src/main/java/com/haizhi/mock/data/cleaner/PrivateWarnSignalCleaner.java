package com.haizhi.mock.data.cleaner;

import com.haizhi.mock.data.framework.Cleaner;
import com.haizhi.mock.data.mapper.PrivateWarnSignalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuguoqin
 * @date 2019/7/17 4:02 PM
 */
@Component
public class PrivateWarnSignalCleaner implements Cleaner {

    @Autowired
    private PrivateWarnSignalMapper privateWarnSignalMapper;

    @Override
    public void clean() {
        privateWarnSignalMapper.truncate();
    }
}
