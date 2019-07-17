package com.haizhi.mock.data.writer;

import com.haizhi.mock.data.entity.PrivateWarnSignalDO;
import com.haizhi.mock.data.framework.Writer;
import com.haizhi.mock.data.mapper.PrivateWarnSignalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuguoqin
 * @date 2019/7/17 5:11 PM
 */
@Component
public class PrivateWarnSignalWriter implements Writer<PrivateWarnSignalDO> {

    @Autowired
    private PrivateWarnSignalMapper privateWarnSignalMapper;

    @Override
    public void write(List<PrivateWarnSignalDO> dataList) {
        privateWarnSignalMapper.batchInsert(dataList);
    }
}
