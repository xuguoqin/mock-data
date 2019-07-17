package com.haizhi.mock.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * 对公风险信号实体
 *
 * @author xuguoqin
 * @date 2019/7/17 9:24 AM
 */
@Data
@Accessors(fluent = true)
public class PublicWarnSignalDO {

    private Long id;
    private String sigId;
    private String sigName;
    private String sigType;
    private String sigCat;
    private String sigGrd;
    private String sigCfmStu;
    private String sigCfmTime;
    private String sigDesc;
    private String sigHdlStu;
    private String sigHdlDate;
    private String sigSource;
    private String custId;
    private String ecifCustId;
    private String custName;
    private String modId;
    private String modName;
    private String tchTime;
    private String warnRst;
    private String ruleId;
    private String ruleDesc;
    private String etlDate;
    private String etlOper;
    private String sigOutDesc;
    private String etlDt;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

}
