package org.luapp.blog.domain;

/**
 * Created by lum on 2015/7/20.
 */
public class TreeNode {
    private String id;
    private String name;
    private boolean isParent;

    public TreeNode() {
    }

    public TreeNode(String id, String name, boolean isParent) {
        this.id = id;
        this.name = name;
        this.isParent = isParent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }
}
