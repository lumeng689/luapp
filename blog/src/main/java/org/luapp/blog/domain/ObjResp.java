package org.luapp.blog.domain;

/**
 * Created by lum on 2015/7/21.
 */
public class ObjResp<T> extends BaseResp {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
