package com.pasha.computernetworkstest.entity;


import com.pasha.computernetworkstest.exceptions.IllegalAnswerException;
import com.pasha.computernetworkstest.utils.RandomShape;

import java.util.*;

public class Question {
    private String questionText;

    private String theme;
    private ArrayList<Answer> answers = new ArrayList<>();


    public Question(String question) {
        this.questionText = question;
    }

    public void setAnswers(ArrayList<Answer> answers){
        this.answers.clear();
        this.answers.addAll(answers);
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setQuestionText(String question) {
        this.questionText = question;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getQuestionText() {
        return questionText;
    }

    /**
     * The method checks the number of answers to the question.
     * @return <code>True</code> if question has only one answer
     *          <p><code>False</code> if question has two and more answers</p>
     * @throws IllegalAnswerException if question has no correct answers
     */
    public Boolean isSingleAnswer() throws IllegalAnswerException {
        /*
            -1 - question has no correct answers
            0 - question has two and more answers
            1 - question has only one answer
         */
        int q = -1;
        for (Answer a : answers){
            if (a.isCorrect()){
                if (q == -1){
                    q = 1;
                }
                else if (q == 1){
                    q = 0;
                }
            }
        }
        if (q == -1){
            throw new IllegalAnswerException("Question has no correct answers");
        }
        return q == 1;
    }

    /**
     * The method gives answers to the question in a random sequence
     * @return answers to the question in a random sequence
     */
    public ArrayList<Answer> getAnswersRandomly() {

        ArrayList<Integer> list = RandomShape.getRandomShape(answers.size());
        ArrayList<Answer> temp = new ArrayList<>();
        System.out.println(list);

        for(Integer i : list){
            temp.add(answers.get(i));
        }

        return temp;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Question){
            Question q = (Question)obj;

            return q.getQuestionText().equals(questionText);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(questionText);
        b.append('\n');
        b.append(answers.toString());
        return b.toString();
    }
}
