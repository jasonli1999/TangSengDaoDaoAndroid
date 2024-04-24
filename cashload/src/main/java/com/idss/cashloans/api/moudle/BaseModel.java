package com.idss.cashloans.api.moudle;

import java.io.Serializable;

/**
 * 描述：Model基类 其他model需继承该类
 */

public class BaseModel implements Serializable {

    /**
     * 状态码
     */
    protected int code;

    /**
     * 错误信息
     */
    protected String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
