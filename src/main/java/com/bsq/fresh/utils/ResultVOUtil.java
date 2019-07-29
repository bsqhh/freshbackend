package com.bsq.fresh.utils;



import com.bsq.fresh.Vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO ro = new ResultVO();
        ro.setCategoryConts(object);
        //ro.setData(object);
        ro.setMsg("成功");
        return ro;
    }
    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO ro = new ResultVO();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }
}
