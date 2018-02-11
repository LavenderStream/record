package org.tiny.lib.database;

import android.content.Context;

import org.tiny.lib.database.generate.DaoMaster;
import org.tiny.lib.database.generate.DaoSession;

/**
 * Created by tiny on 1/24/2018
 */
public class DbHelper {
    private static final String DB_NAME = "record.db";
    private static DbHelper instance;
    private DaoMaster.OpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static void init(Context context) {
        DbHelper.getInstance().initDb(context);
    }

    public static DaoSession getSession() {
        return DbHelper.getInstance().getDaoSession();
    }

    private DbHelper() {
    }

    private static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper();
                }
            }
        }
        return instance;
    }

    private void initDb(Context context) {
        mHelper = new DbOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    private DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    private void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }
}
