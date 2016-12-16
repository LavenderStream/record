package org.record.tiny.ui.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.ui.model.ViewModel;
import org.record.tiny.utils.DisplayUtil;
import org.record.tiny.utils.Error;
import org.record.tiny.utils.RealmUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

@SuppressWarnings("All")
public class EditPresenter extends BasePresenter<EditView> {

    private String mSharedFilePath;

    public EditPresenter(EditView context) {
        attachView(context);
    }

    public void start() {
        ViewModel viewModel = (ViewModel) RealmUtils.getInstance().queryObjects(ViewModel.class).first();

        mvpView.getLocal(viewModel.getAddress());
        mvpView.getTitle(viewModel.getYear() + "年 \n" + viewModel.getMonth() + "月 " + viewModel.getDay() + "日");
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
