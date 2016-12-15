package org.record.tiny.utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static org.record.tiny.utils.Config.DATABASE_NAME;

@SuppressWarnings("All")
public class RealmUtils {
    private static RealmUtils instance;

    private RealmUtils() {
        initRealm();
    }

    public static RealmUtils getInstance() {
        if (instance == null) {
            synchronized (RealmUtils.class) {
                if (instance == null) {
                    instance = new RealmUtils();
                }
            }
        }
        return instance;
    }

    private void initRealm() {
        Realm.getInstance(new RealmConfiguration.Builder().name(DATABASE_NAME).build());
    }


    public RealmResults<? extends RealmObject> queryObjects(Class<? extends RealmObject> clazz) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(clazz).findAll();
    }

    public void insertObject(final RealmObject object) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
            }
        });
        realm.close();
    }

    public void insertObjects(final List<? extends RealmObject> objects) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(objects);
            }
        });
        realm.close();
    }
}
