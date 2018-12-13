package com.pasha.computernetworkstest.gui;

import com.pasha.computernetworkstest.Main;
import com.pasha.computernetworkstest.entity.Answer;
import com.pasha.computernetworkstest.entity.Question;
import com.pasha.computernetworkstest.exceptions.IllegalAnswerException;
import com.pasha.computernetworkstest.gui.utils.ButtonModel;
import com.pasha.computernetworkstest.gui.utils.LabelModel;
import com.pasha.computernetworkstest.gui.utils.RectangleModel;
import com.pasha.computernetworkstest.utils.QuestionList;
import com.pasha.computernetworkstest.utils.RandomShape;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionFragment {

    private  ArrayList<Question> questionList;
    private  ArrayList<Integer> sequence;

    private  Question currentQuestion;
    private  ArrayList<Answer> currentAnswers;


    private  Pane pane;
    private  Label qLabel;
    private  ArrayList<Rectangle> rectangles = new ArrayList<>();
    private  ArrayList<ButtonBase> buttons = new ArrayList<>();

    private static int max;
    private static Boolean checked = true;
    private static int sum = 0;


    /**
     * index of current question from questionList
     */
    private  int index = 0;

    @FXML
    private  Button check;

    @FXML
    private  Button goToNext;

    public QuestionFragment(Pane pane)  {
        this.pane = pane;
        this.pane.setOnKeyPressed(this::enterClick);
        check = new Button("Проверить");
        goToNext = new Button("Дальше");
        setConstantButtonsParameters();
        loadJSON();
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }


    private  void setConstantButtonsParameters(){
        goToNext.setLayoutX(650);
        goToNext.setLayoutY(440);
        goToNext.setDisable(true);

        check.setLayoutX(550);
        check.setLayoutY(440);
        check.setOnAction(event -> check());
        goToNext.setOnAction(event -> {
            try {
                goToNextQuestion();
            } catch (IllegalAnswerException e) {
                e.printStackTrace();
            }
        });
    }

    private  void setUpdatableItemsParameters() throws IllegalAnswerException {
        final int START_Y = 110;
        final int STEP_Y = 45;
        /*
        SET ANSWER-BUTTONS PARAMETERS
         */
        buttons.clear();
        int type = currentQuestion.isSingleAnswer() ? ButtonFactory.RADIO : ButtonFactory.CHECK;

        ToggleGroup group = new ToggleGroup();

        for (int i = 0; i < currentAnswers.size(); i++){
            ButtonBase b = ButtonModel.getButton(type);

            b.setLayoutY(START_Y + i * STEP_Y);
            b.setText(currentAnswers.get(i).getText());

            if(b instanceof RadioButton) {
                RadioButton r = (RadioButton) b;
                r.setToggleGroup(group);
            }

            buttons.add(b);
        }

        /*
        SET RECTANGLE PARAMETERS
         */

        rectangles.clear();

        for (int i = 0; i < currentAnswers.size(); i++){
            Rectangle rec = RectangleModel.getRectangle();
            rec.setLayoutY(START_Y + i * STEP_Y);
            rectangles.add(rec);
        }

        /*
        SET LABEL PARAMETERS
         */

        qLabel = LabelModel.getLabel();
        qLabel.setText(currentQuestion.getQuestionText());
    }

    private  ArrayList<Node> getAllNodes(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(rectangles);
        nodes.addAll(buttons);
        nodes.add(qLabel);
        nodes.add(check);
        nodes.add(goToNext);
        return nodes;
    }

    private void loadJSON(){
        new Thread(() -> {
            try{
                questionList = new ArrayList<>();
                System.out.println(Arrays.toString(Main.selectedThemes.toArray()));
                for(Question q : Main.questionList){
                    if(Main.selectedThemes.contains(q.getTheme())) {
                        questionList.add(q);
                    }
                }
                System.out.println("загрузил");
                sequence = RandomShape.getRandomShape(questionList.size());
                max = Math.min(questionList.size(), Main.spinnerValue);
                Platform.runLater(() -> {
                    try {
                        goToNextQuestion();
                    } catch (IllegalAnswerException e) {
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                try {
                    Platform.runLater(() -> {
                        Label label = LabelModel.getLabel();
                        label.setText("Не могу загрузить базу вопросов. Файл data.json должен" +
                                " лежать рядом с jar-файлом!!!");
                        pane.getChildren().add(label);
                    });
                }catch (Exception ignored){}
            }
        }).start();
    }

    private void enterClick(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            if(checked){
                try{
                    goToNextQuestion();
                }catch (Exception ignored){}
            }else {
                check();
            }
        }
        System.out.println("click");
    }

    private  void check(){
        goToNext.setDisable(false);
        check.setDisable(true);
        checked = !checked;
        int mark = 0, correctCount = 0;
        for(int i = 0; i < currentAnswers.size(); i++){
            if(currentAnswers.get(i).isCorrect()){
                rectangles.get(i).setFill(Color.FORESTGREEN);
                correctCount++;
                ButtonBase b = buttons.get(i);
                if( b instanceof RadioButton && ((RadioButton)b).isSelected()
                        || b instanceof CheckBox && ((CheckBox)b).isSelected()){
                    System.out.println("dfnsfn");
                    mark++;
                }
            }else{
                rectangles.get(i).setFill(Color.ORANGERED);
            }
        }
        if(mark == correctCount){
            sum++;
        }
    }

    private  void goToNextQuestion() throws IllegalAnswerException {
        ObservableList<Node> list = pane.getChildren();
        if (index == max ) {
            goToNext.setDisable(true);
            goToNext.setText("Конец");
            list.removeAll(getAllNodes());
            Label end = LabelModel.getLabel();
            end.setText("Тест завершен! Вы набрали " + sum + '/' + max);
            list.add(end);
        }else {
            if(!checked){
                check();
                check.setDisable(true);
                goToNext.setDisable(false);
                checked = true;
                return;
            }

            checked = false;
            currentQuestion = questionList.get(sequence.get(index));
            currentAnswers = currentQuestion.getAnswersRandomly();

            setUpdatableItemsParameters();

            check.setDisable(false);
            goToNext.setDisable(true);

            list.clear();
            list.addAll(getAllNodes());
        }

        index++;
    }

}
