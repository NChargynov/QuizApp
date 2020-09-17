package com.geektech.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.geektech.quizapp.data.QuizRepository;
import com.geektech.quizapp.data.remote.IHistoryStorage;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.data.remote.QuizApiClient;
import com.geektech.quizapp.db.QuizDataBase;
import com.geektech.quizapp.ui.history.HistoryStorage;

public class QuizApp extends Application {

    public static IQuizApiClient iQuizApiClient;
    private static IHistoryStorage iHistoryStorage;
    public static QuizDataBase quizDataBase;
    public static QuizRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        iQuizApiClient = new QuizApiClient();
        iHistoryStorage = new HistoryStorage();
        quizDataBase = Room.databaseBuilder(getApplicationContext(),
                QuizDataBase.class, "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        repository = new QuizRepository(iQuizApiClient, iHistoryStorage, quizDataBase.quizDao());
    }

}
