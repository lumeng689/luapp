package org.luapp.blog.domain;

/**
 * Created by lum on 2015/7/20.
 */
public class User {
    private long id;
    private String name;
    private String job;
    private String desc;

    public User() {
    }

    public User(long id, String name, String job, String desc) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
