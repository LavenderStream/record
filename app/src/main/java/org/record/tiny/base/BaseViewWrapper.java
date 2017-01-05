package org.record.tiny.base;


import android.databinding.ViewDataBinding;

public class BaseViewWrapper<B extends ViewDataBinding> {
    protected B binding;

    public BaseViewWrapper(B binding) {
        this.binding = binding;
    }
}
