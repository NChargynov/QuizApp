package com.geektech.quizapp;

import android.widget.SeekBar;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> message = new MutableLiveData<>();
//    public MutableLiveData<Integer> counterData = new MutableLiveData<>();

//    int counter;

    public MainViewModel(){
        message.setValue("First ");
    }

//    public void plusOnClick(){
//        counter += 1;
//        counterData.setValue(counter);
//    }
//
//    public void minusOnClick(){
//        counter -= 1;
//        counterData.setValue(counter);
//    }
}