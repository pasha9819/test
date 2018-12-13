package com.pasha.computernetworkstest;

import com.pasha.computernetworkstest.entity.Question;
import com.pasha.computernetworkstest.gui.PreparedEmptyPane;
import com.pasha.computernetworkstest.gui.QuestionFragment;
import com.pasha.computernetworkstest.utils.QuestionList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class Main extends Application {
    private  Pane pane = PreparedEmptyPane.createAnchorPane(PreparedEmptyPane.SETTINGS);
    private  Scene mainScene = new Scene(pane);
    private  Spinner<Integer> spinner = new Spinner<>();
    private static Stage stage;
    public static int spinnerValue = 10;
    public static ArrayList<Question> questionList;
    public static ArrayList<String> selectedThemes = new ArrayList<>();
    private static ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    questionList = QuestionList.getFromJSON("LabAndDop.json");
                } catch (IOException e) {
                    questionList = null;
                    e.printStackTrace();
                }
            }
        }).start();
    }

    ;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        System.out.println("start");
        SpinnerValueFactory<Integer> factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(5,500,15,5);
        factory.increment(5);
        factory.decrement(5);
        spinner.setValueFactory(factory);
        spinner.setPrefSize(pane.getPrefWidth() - 20,40);

        spinner.setLayoutX(10);


        pane.getChildren().addAll(spinner);

        HashSet<String> set = new HashSet<>();
        for(Question q : questionList){
            set.add(q.getTheme());
        }
        ArrayList<String> themes = new ArrayList<>(set);
        for(int i = 0; i < set.size(); i++){
            String str = themes.get(i);
            CheckBox b = new CheckBox(str.replace('_', ' '));
            b.setLayoutX(10);
            b.setLayoutY(10 + 20*i);
            checkBoxes.add(b);
            pane.getChildren().addAll(b);
        }
        spinner.setLayoutY(10 + 20*set.size());

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        spinner.setOnKeyPressed(event -> {

            if(event.getCode().equals(KeyCode.ENTER)){
                for(CheckBox b : checkBoxes){
                    if(b.isSelected()){
                        selectedThemes.add(b.getText().replace(' ', '_'));
                        System.out.println("add");
                    }
                }
                if(selectedThemes.isEmpty()){
                    return;
                }
                goClick();
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }

    private void goClick(){
        spinnerValue = spinner.getValue();
        try{
            pane = PreparedEmptyPane.createAnchorPane(PreparedEmptyPane.TEST);
            mainScene = new Scene(pane);
            stage.setScene(mainScene);
            QuestionFragment fragment = new QuestionFragment(pane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


