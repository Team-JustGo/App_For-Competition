package com.justgo.util;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable= new CompositeDisposable();

    public void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
