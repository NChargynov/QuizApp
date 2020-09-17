package com.geektech.quizapp.core;

public interface IBaseCallBack<T> {

    void onSuccess(T result);
    void onFailure(Exception e);
}
