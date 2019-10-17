package com.example.styles1.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class MissionStatusViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MissionStatusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mission Status Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
