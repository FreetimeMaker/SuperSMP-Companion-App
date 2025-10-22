package com.freetime.ssmpc.ui.scmd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServerCMDViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ServerCMDViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Server Commands fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}