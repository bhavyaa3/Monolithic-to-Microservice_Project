package com.learning.quiz_service.model;

public class QuizDTO {

    private String category;
    private String difficultyLevel;
    private int numQ;
    private String title;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getNumQ() {
        return numQ;
    }

    public void setNumQ(int numQ) {
        this.numQ = numQ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuizDTO(String category, String difficultyLevel, int numQ, String title) {
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.numQ = numQ;
        this.title = title;
    }

    public QuizDTO() {
    }
}
