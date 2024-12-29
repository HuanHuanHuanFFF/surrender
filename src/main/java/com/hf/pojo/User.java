package com.hf.pojo;

import com.hf.utlis.TimeUtils;
import org.springframework.beans.factory.DisposableBean;

import java.util.Date;

public class User implements DisposableBean {
    private String id;
    private String name;
    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time=" + TimeUtils.dateFormat(time) +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(TimeUtils.dateFormat(new Date()) + ":user{" + id + "," + name + "," + time
                + "}saved to MySQL and destroyed");
    }
}