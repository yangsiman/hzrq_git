package com.hzvanke.report.commons;

public class ResultUtil {

    public static Result success() {
        return success(null);
    }

    public static Result success(String msg) {
        return success(msg, null);
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setData(data);
        if (StringUtil.isEmpty(msg)) msg = "操作成功";
        result.setMsg(msg);
        return result;
    }

    public static Result error() {
        return error("未知错误");
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
