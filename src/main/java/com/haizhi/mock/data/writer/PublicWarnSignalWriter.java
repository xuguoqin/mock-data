package com.haizhi.mock.data.writer;

import com.haizhi.mock.data.entity.PublicWarnSignalDO;
import com.haizhi.mock.data.framework.Writer;
import com.haizhi.mock.data.mapper.PublicWarnSignalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuguoqin
 * @date 2019/7/17 5:11 PM
 */
@Component
public class PublicWarnSignalWriter implements Writer<PublicWarnSignalDO> {

    @Autowired
    private PublicWarnSignalMapper warnSignalMapper;

    @Override
    public void write(List<PublicWarnSignalDO> dataList) {
        warnSignalMapper.batchInsert(dataList);
    }
}
