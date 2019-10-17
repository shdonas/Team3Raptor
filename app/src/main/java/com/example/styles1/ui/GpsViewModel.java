package com.example.styles1.ui;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class GpsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GpsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("GPS Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}