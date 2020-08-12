package com.geektech.quizapp;

import android.widget.SeekBar;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {



    public MutableLiveData<String> message = new MutableLiveData<>();

    public MainViewModel(){
        message.setValue("First ");
//        message.postValue("First ");
    }

}