package com.bsq.fresh.utils;


import com.bsq.fresh.Vo.ResultFoodVO;

public class ResultFoodVOUtil {

    public static ResultFoodVO success(Object object) {
        ResultFoodVO ro = new ResultFoodVO();
        ro.setSearchResults(object);
        //ro.setData(object);
        ro.setMsg("成功");
        return ro;
    }
    public static ResultFoodVO success() {
        return success(null);
    }

    public static ResultFoodVO error(Integer code, String msg) {
        ResultFoodVO ro = new ResultFoodVO();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }

}
