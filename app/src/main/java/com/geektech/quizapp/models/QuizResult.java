package com.geektech.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.geektech.quizapp.db.converter.DateConverter;
import com.geektech.quizapp.db.converter.QuestionConverter;

import java.util.Date;
import java.util.List;

@Entity
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correct_answer_result")
    private int correctAnswerResult;
    @ColumnInfo(name = "questions")
    @TypeConverters({QuestionConverter.class})
    private List<Question> questions;
    @ColumnInfo(name = "created_at")
    @TypeConverters({DateConverter.class})
    private Date createdAt;

    public QuizResult(int id, String category, String difficulty, int correctAnswerResult, List<Question> questions, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.questions = questions;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswerResult() {
        return correctAnswerResult;
    }

    public void setCorrectAnswerResult(int correctAnswerResult) {
        this.correctAnswerResult = correctAnswerResult;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
