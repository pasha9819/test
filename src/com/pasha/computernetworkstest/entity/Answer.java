package com.pasha.computernetworkstest.entity;

public class Answer {
    private Boolean correct;
    private String text;

    public Answer(Boolean correct, String text) {
        this.correct = correct;
        this.text = text;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public String getText() {
        return text;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
