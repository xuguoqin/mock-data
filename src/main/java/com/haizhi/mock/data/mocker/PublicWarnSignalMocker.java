package com.haizhi.mock.data.mocker;

import com.haizhi.mock.data.entity.PublicWarnSignalDO;
import com.haizhi.mock.data.framework.Mocker;
import com.haizhi.mock.data.util.RandomUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xuguoqin
 * @date 2019/7/17 4:39 PM
 */
@Component
public class PublicWarnSignalMocker implements Mocker<PublicWarnSignalDO> {

    @Override
    public PublicWarnSignalDO mockData(int i) {
        return new PublicWarnSignalDO()
                .sigId(String.format("crw2019sigid%s", i))
                .sigName("经营严重下滑")
                .sigType(String.valueOf(RandomUtils.nextIntClosed(1, 2)))
                .sigCat(String.valueOf(RandomUtils.nextIntClosed(1, 2)))
                .sigGrd(String.format("0%s", RandomUtils.nextIntClosed(1, 3)))
                .sigCfmStu(String.format("0%s", RandomUtils.nextIntClosed(2)))
                .sigCfmTime(LocalDate.now().toString())
                .sigDesc(String.format("<clob>%s", i))
                .sigHdlStu(String.valueOf(RandomUtils.nextIntClosed(1, 2)))
                .sigHdlDate("")
                .sigSource(String.format("0%s", RandomUtils.nextIntClosed(1, 7)))
                .custId(String.format("cust_id_%s", i))
                .ecifCustId(String.format("ecif_cust_id_%s", i))
                .custName(String.format("cust_name_%s", i))
                .modId("com04")
                .modName("客户经营异常情况")
                .tchTime(LocalDate.now().toString())
                .warnRst(String.valueOf(RandomUtils.nextIntClosed(1, 2)))
                .ruleId("com0401")
                .ruleDesc("销售收入或主要产品销售量较同期下滑30%以上，或出现亏损，或经营现金净流入连续两年为负以及其他经营严重下滑的信息")
                .etlDate(LocalDate.now().toString())
                .etlOper("etl")
                .sigOutDesc("客户经营严重下滑")
                .etlDt(LocalDate.now().toString())
                .gmtCreate(Timestamp.valueOf(LocalDateTime.now()))
                .gmtModified(null);
    }
}
