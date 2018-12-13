package com.pasha.computernetworkstest.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pasha.computernetworkstest.entity.Question;

import java.io.*;
import java.util.ArrayList;

public class QuestionList {
    private ArrayList<Question> entity;

    public ArrayList<Question> getEntity() {
        return entity;
    }

    public QuestionList setEntity(ArrayList<Question> entity) {
        this.entity = entity;
        return this;
    }

    public static void outQuestionList(ArrayList<Question> list, String file){
        try(PrintWriter pw = new PrintWriter(new File(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String full = gson.toJson(new QuestionList().setEntity(list));
            pw.println(full);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    public static ArrayList<Question> getFromJSON(String fileName ) throws IOException {
        BufferedReader reader
                = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(fileName), "UTF-8"));
        String temp;
        StringBuilder jsonString = new StringBuilder();

        while ((temp = reader.readLine()) != null){
            jsonString.append(temp);
        }
        QuestionList gson = new Gson().fromJson(jsonString.toString(), QuestionList.class);

        return gson.getEntity();
    }
}
