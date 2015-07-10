package org.luapp.blog.domain;

/**
 * Created by lum on 2015/7/10.
 */
public class MenuItem {
    // 显示文字
    private String label;
    // 地址
    private String url;

    public MenuItem() {
    }

    public MenuItem(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
