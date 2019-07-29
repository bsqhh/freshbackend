package com.bsq.fresh.dto;


import com.bsq.fresh.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
public class AddRessDTo {

    private String byId;
    private String byName;
    private String byPhone;
    private String byProvince;
    private String byCity;
    private String byCityname;
    private String byAddress;
    private String byOpenid;


    /** 创建时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
