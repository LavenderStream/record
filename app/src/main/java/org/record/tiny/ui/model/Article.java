package org.record.tiny.ui.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("All")
public class Article extends RealmObject {
    @PrimaryKey
    private long id;
    // 年
    private String year;
    // 月
    private String month;
    // 日
    private String day;
    // 天气
    private String weather;
    // 正文
    private String context;
    // 位置
    private String local;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", weather='" + weather + '\'' +
                ", context='" + context + '\'' +
                ", local='" + local + '\'' +
                '}';
    }
}
