package org.record.tiny.utils;

import org.record.tiny.ui.RecordApplication;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static org.record.tiny.utils.Config.DATABASE_NAME;

@SuppressWarnings("All")
public class RealmUtils {
    private static RealmUtils instance;

    private RealmUtils() {
        Realm.init(RecordApplication.getContext());
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

    public <E extends RealmModel> RealmResults<E> queryObjects(Class<E> clazz) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(clazz).findAll();
    }

    public <E extends RealmModel> RealmQuery<E> queryObjectAlls(Class<E> clazz) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(clazz);
    }

    public void updateObject(final RealmObject object) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmObject obj = realm.where(object.getClass()).equalTo("age", 1).findFirst();
            }
        });
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
