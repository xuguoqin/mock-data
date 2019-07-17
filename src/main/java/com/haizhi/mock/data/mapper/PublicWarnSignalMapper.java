package com.haizhi.mock.data.mapper;

import com.haizhi.mock.data.entity.PublicWarnSignalDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuguoqin
 * @date 2019/7/17 11:24 AM
 */
@Mapper
public interface PublicWarnSignalMapper {

    void batchInsert(List<PublicWarnSignalDO> warnSignalDOList);

    void truncate();
}
