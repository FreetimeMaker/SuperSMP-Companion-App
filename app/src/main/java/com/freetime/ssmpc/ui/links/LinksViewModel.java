package com.freetime.ssmpc.ui.links;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LinksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LinksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Links fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}