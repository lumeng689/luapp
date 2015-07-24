package org.luapp.blog.domain;

/**
 * Created by lum on 2015/7/21.
 */
public class BaseResp {

    public static final int RET_FAIL = -1;
    public static final int RET_SUCCESS = 0;

    // 返回码
    private int code;
    // 提示消息
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
