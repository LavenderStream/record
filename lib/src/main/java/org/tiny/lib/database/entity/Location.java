package org.tiny.lib.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tiny on 2/12/2018
 */
@Entity
public class Location {
    private String location;

    @Generated(hash = 763449240)
    public Location(String location) {
        this.location = location;
    }

    @Generated(hash = 375979639)
    public Location() {
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
