package com.geektech.quizapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.quizapp.models.QuizResult;

@Database(entities = {QuizResult.class}, version = 1, exportSchema = false)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
