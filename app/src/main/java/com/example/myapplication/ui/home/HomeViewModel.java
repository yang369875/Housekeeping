package com.example.myapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// ViewModel用于管理UI的数据，确保UI的数据在屏幕旋转等情况下不会丢失
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment"); // 设置默认值
    }

    // LiveData用于在数据更改时通知观察者
    public LiveData<String> getText() {
        return mText;
    }
}