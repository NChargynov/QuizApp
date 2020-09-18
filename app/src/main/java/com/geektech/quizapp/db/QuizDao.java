package com.geektech.quizapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.quizapp.models.QuizResult;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    long insert(QuizResult quizResult);

    @Delete
    void delete(QuizResult quizResult);

    @Query("SELECT * FROM quizresult WHERE id = :id")
    QuizResult getById(int id);

    @Query("DELETE FROM quizresult")
    void deleteAll();

    @Query("DELETE FROM quizresult WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM quizresult")
    LiveData<List<QuizResult>> getAll();
}
