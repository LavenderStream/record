package org.tiny.lib.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tiny on 2/11/2018
 */
@Entity
public class Article {
    @Id
    private long id;
    private String title;
    private long time;
    private String content;
    private String location;
    @Generated(hash = 1056310756)
    public Article(long id, String title, long time, String content,
            String location) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
        this.location = location;
    }
    @Generated(hash = 742516792)
    public Article() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
