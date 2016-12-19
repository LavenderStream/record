package org.record.tiny.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.ui.model.Article;
import org.record.tiny.ui.model.ViewModel;
import org.record.tiny.utils.DisplayUtil;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.EventIntent;
import org.record.tiny.utils.LogUtils;
import org.record.tiny.utils.RealmUtils;
import org.record.tiny.utils.RxBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

@SuppressWarnings("All")
public class EditPresenter extends BasePresenter<EditView> {

    private String mSharedFilePath;
    private ViewModel mViewModel;
    private Article mArticle;

    public EditPresenter(EditView context) {
        attachView(context);
    }

    private boolean flag = false;
    public void start() {
        mArticle = (Article) EventIntent.getInstance().get("intent_article");
        setView(mArticle);

        LogUtils.d("EditPresenter -> start: ");
        LogUtils.d("EditPresenter -> flag: " + flag);
        Observable observable = RxBus.getInstance().toObserverable(Article.class);

        Subscriber subscriber = new Subscriber<Article>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Article o) {
                LogUtils.d("EditPresenter -> onNext: " + o);
                flag = true;
            }
        };
        addSubscription(observable, subscriber);
    }

    private void setView(Article article) {
        if (article == null) {
            mViewModel = (ViewModel) RealmUtils.getInstance().queryObjects(ViewModel.class).first();
            mvpView.getLocal(mViewModel.getAddress());
            mvpView.getTitle(mViewModel.getYear() + "年 \n" + mViewModel.getMonth() + "月 " + mViewModel.getDay() + "日");
        } else {
            mvpView.getContent(article.getContext());
            mvpView.getLocal(article.getLocal());
            mvpView.getTitle(article.getYear() + "年 \n" + article.getMonth() + "月 " + article.getDay() + "日");
        }
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void saveArticle(String location, String content) {
        LogUtils.d("EditPresenter -> saveArticle: " + mArticle);
        LogUtils.d("EditPresenter -> saveArticle: " + content);
        LogUtils.d("EditPresenter -> saveArticle: " + location);
        if (mArticle == null) {
            mArticle = new Article();
            mArticle.setId(System.currentTimeMillis() / 1000);
            mArticle.setYear(mViewModel.getYear());
            mArticle.setMonth(mViewModel.getMonth());
            mArticle.setDay(mViewModel.getDay());
            mArticle.setContext(content);
            mArticle.setLocal(location);

            RealmUtils.getInstance().insertObject(mArticle);
        } else {
            Realm.getDefaultInstance().beginTransaction();
            mArticle.setContext(content);
            mArticle.setLocal(location);

            Realm.getDefaultInstance().commitTransaction();
        }

    }

    public void save2Bitmap(View view) {
        Observable observable = Observable.just(view)
                .map(new Func1<View, String>() {
                    @Override
                    public String call(View view) {
                        Bitmap bitmap = convertViewToBitmap(view);

                        return saveBitmap(bitmap);
                    }
                });

        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mvpView.startShare(mSharedFilePath);
            }

            @Override
            public void onError(Throwable e) {
                mvpView.error(Error.SAVE_BITMAP_ERROR);
            }

            @Override
            public void onNext(String filePath) {
                mSharedFilePath = filePath;
            }
        };
        addSubscription(observable, subscriber);
    }


    public Bitmap convertViewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(DisplayUtil.getScreenWidth(), DisplayUtil.getSceenHeight() - DisplayUtil.getStatusBarHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


    public boolean saveBitmap(Bitmap bm, String path) {
        File img = new File(path);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
            fOut.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fOut = null;
        }
    }

    public String saveBitmap(Bitmap bm) {
        String filePath = RecordApplication.getContext().getExternalCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
        boolean result = saveBitmap(bm, filePath);
        return result ? filePath : null;
    }
}
