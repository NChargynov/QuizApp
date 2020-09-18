package com.geektech.quizapp.models;

import java.util.Date;

public class History {

    private String category;
    private int id;
    private String difficulty;
    private int correctAnswer;
    private int amount;
    private Date createdAt;

    public History(int id, String category, String difficulty, int correctAnswer, int amount, Date createdAt) {
        this.category = category;
        this.id = id;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.amount = amount;
        this.createdAt = createdAt;
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

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

