package com.bsq.fresh.Vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultFoodVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T searchResults;
}
