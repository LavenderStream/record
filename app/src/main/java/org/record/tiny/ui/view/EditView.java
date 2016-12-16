package org.record.tiny.ui.view;

import org.record.tiny.base.BaseView;

@SuppressWarnings("All")
public interface EditView extends BaseView {

    /**
     * @param local 地理位置
     */
    void getLocal(String local);

    void getTitle(String title);

    /**
     * 开始分享
     *
     * @param FilePath 要分享文件的路径
     */
    void startShare(String FilePath);
}
