package org.record.tiny.ui.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@SuppressWarnings("All")
public class ViewModel extends RealmObject {
    @PrimaryKey
    private int id;

    private Weather weather;
    private String address;
    private String year;
    private String month;
    private String day;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ViewModel{" +
                "id=" + id +
                ", weather=" + weather +
                ", address='" + address + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
