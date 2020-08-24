package com.geektech.quizapp;

import android.app.Application;

import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.data.remote.QuizApiClient;

public class QuizApp extends Application {

    public static IQuizApiClient iQuizApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        iQuizApiClient = new QuizApiClient();
    }
}
