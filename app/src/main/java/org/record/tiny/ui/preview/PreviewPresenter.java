package org.record.tiny.ui.preview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import org.record.tiny.base.BasePresenter;
import org.record.tiny.net.RxSubscriber;
import org.record.tiny.ui.RecordApplication;
import org.record.tiny.utils.DisplayUtil;
import org.record.tiny.utils.Error;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


@SuppressWarnings("All")
public class PreviewPresenter extends BasePresenter<PreviewView> {
    public PreviewPresenter(PreviewView view) {
        super(view);
    }

    public void save2Bitmap(View view) {
        Flowable observable = Flowable.just(view)
                .map(new Function<View, String>() {
                    @Override
                    public String apply(View view) throws Exception {
                        Bitmap bitmap = convertViewToBitmap(view);
                        return saveBitmap(bitmap);
                    }
                });
        addSubscription(observable, new RxSubscriber<String>() {
            @Override
            public void onNext(String filePath) {
            }

            @Override
            public void onError(Throwable t) {
                mvpView.error(Error.SAVE_BITMAP_ERROR);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Bitmap convertViewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(DisplayUtil.getScreenWidth(), DisplayUtil.getSceenHeight() - DisplayUtil.getStatusBarHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


    private boolean saveBitmap(Bitmap bm, String path) {
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

    private String saveBitmap(Bitmap bm) {
        String filePath = RecordApplication.getContext().getExternalCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
        boolean result = saveBitmap(bm, filePath);
        return result ? filePath : null;
    }
}
