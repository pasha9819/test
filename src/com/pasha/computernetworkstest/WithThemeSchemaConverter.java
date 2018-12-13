package com.pasha.computernetworkstest;

import com.pasha.computernetworkstest.entity.Answer;
import com.pasha.computernetworkstest.entity.Question;
import com.pasha.computernetworkstest.utils.QuestionList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class WithThemeSchemaConverter {
    private static HashSet<String> qSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        addNewQuestions();
        System.out.println(qSet.size());
    }

    public static void setTheme() throws IOException {
        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, String> themes = new HashMap<>();

        themes.put(1, "знакомство_с_PT");
        themes.put(2, "маршрутизация_");
        themes.put(3, "сетевые технологии_");
        themes.put(4, "кабели_");
        themes.put(5, "Ethernet_");
        themes.put(6, "адресация_");
        themes.put(7, "wifi_");
        themes.put(8, "vlan_");

        ArrayList<Question> questions = QuestionList.getFromJSON("withThemes");
//        System.out.println(questions.get(1).getTheme().toString());

        for(Question q : questions){
            /*if(q.getTheme() == null){
                q.setTheme(new HashSet<>());
            }
            if (q.getTheme().size() > 1){
                System.out.println("\n\n\n\n\n");
                System.out.println(q.getQuestionText());
                System.out.println("[" + q.getTheme().toString() + "]");
                String t = scanner.nextLine();
                for(Integer c = 1; c <= 8; c++){
                    if(t.contains(c.toString())){
                        q.getTheme().add(themes.get(c));
                    }
                }
            }*/
            //q.setTheme(q.getTema().toArray()[0].toString());
            //q.setTema(null);

        }

        QuestionList.outQuestionList(questions, "withThemes.json");


    }

    private static void addNewQuestions() throws IOException {
        ArrayList<Question> questions = QuestionList.getFromJSON("withThemes.json");
        for (Question q:questions) {
            qSet.add(q.getQuestionText().toLowerCase());
        }

        String fileName = "DopVoprosy/.";
        String path = new File(fileName).getAbsolutePath();

        path = path.substring(0, path.lastIndexOf('\\')) + '\\';
        System.out.println("path --- " + path);

        File dir = new File(path);

        FileFilter filter = pathname -> {
            try {
                /*
                    condition for directories(folders)
                 */
                if (!pathname.isFile()){
                    return false;
                }
                String name = pathname.getName();
                String type = name.substring(name.lastIndexOf('.') + 1, name.length());
                return type.equals("txt");
            }catch (Exception e){
                System.out.println(pathname.getAbsolutePath());
                return false;
            }
        };

        File[] files = dir.listFiles(filter);

        if(files == null){
            return;
        }
        System.out.println("\nFiles: " + '(' + filter.toString() + ')');
        for(File f : files){
            System.out.println(f.getName());
            ArrayList<Question> t = parseFromFourStrings(f);
            questions.addAll(t);
        }

        QuestionList.outQuestionList(questions, "LabAndDop.json");
    }

    private static ArrayList<Question> parseFromFourStrings(File file) throws IOException {
        ArrayList<Question> questions = new ArrayList<>();
        String question;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String theme = file.getName().substring(0, file.getName().lastIndexOf('.'));
        while ((question = reader.readLine()) != null){
            String correctString = reader.readLine();

            String[] correctAnswers = correctString.split(";");
            String[] wrongAnswers = reader.readLine().split(";");
            String empty = reader.readLine();

            if(empty != null && !empty.isEmpty()){
                System.out.println("не пусто около ");
                System.out.println(question);
                System.out.println(correctString);
                System.out.println("~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
            }

            // temp objects for copy information from "all.txt"
            Question temp = new Question(question);


            if(!correctString.isEmpty()){
                for (String str: correctAnswers) {
                    if(!str.isEmpty()){
                        Answer a = new Answer(true, str);
                        temp.getAnswers().add(a);
                    }else{
                        System.out.println("[ EXCEPTION ]");
                        System.out.println(question);
                        System.out.println(correctString);
                        System.out.println();
                    }
                }
            }
            for(String str : wrongAnswers){
                if(!str.isEmpty()){
                    Answer a = new Answer(false, str);
                    temp.getAnswers().add(a);
                }else{
                    System.out.println("[ EXCEPTION ]");
                    System.out.println(question);
                    System.out.println(correctString);
                    //System.out.println(wrongAnswers.toString());
                    System.out.println();
                }
            }

            temp.setTheme(theme);
            if(!qSet.contains(temp.getQuestionText().toLowerCase())){
                questions.add(temp);
                qSet.add(temp.getQuestionText().toLowerCase());
            }else {
                System.out.println("\t___contains___");
                System.out.println(temp);
            }
        }
        return questions;
    }

}
