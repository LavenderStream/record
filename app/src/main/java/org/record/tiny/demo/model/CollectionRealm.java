package org.record.tiny.demo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class CollectionRealm extends RealmObject {
    // 文章id
    @PrimaryKey
    private int id;
    // 该文章是否已被收藏
    private boolean isCollection = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    @Override
    public String toString() {
        return "CollectionRealm{" +
                "id=" + id +
                ", isCollection=" + isCollection +
                '}';
    }
}
