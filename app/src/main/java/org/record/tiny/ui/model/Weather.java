package org.record.tiny.ui.model;

import io.realm.RealmObject;

@SuppressWarnings("All")
public class Weather extends RealmObject {
    private String weather;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weather='" + weather + '\'' +
                '}';
    }
}
