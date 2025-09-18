package com.learning.question_service.model.wrapper;

public class QuizResponse {

    private int id;
    private String response;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public QuizResponse(int id, String response) {
        this.id = id;
        this.response = response;
    }
}
