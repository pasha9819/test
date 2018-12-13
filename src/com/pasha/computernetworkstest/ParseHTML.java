package com.pasha.computernetworkstest;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import com.pasha.computernetworkstest.entity.Answer;
import com.pasha.computernetworkstest.entity.Question;
import com.pasha.computernetworkstest.utils.QuestionList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHTML {
    public static void main(String[] args) throws Exception {


        Console console = System.console();
        if (console == null || System.out == null){
            File jar = new File(WithThemeSchemaConverter.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String jarFullName = jar.getAbsolutePath();

            FileWriter writer = new FileWriter("executeCNS.bat");
            writer.write("java -jar \"" + jarFullName + "\"\npause");
            writer.close();
            writer.close();
            Desktop.getDesktop().open(new File("executeCNS.bat"));


        }else {
            code();
            FileWriter fw = new FileWriter("test" + System.currentTimeMillis()  +".txt");
            fw.write("hello");
            fw.flush();
            fw.close();
        }
    }

    private static void code(){
        System.out.println("start");

        String fileName = ".";
        String path = new File(fileName).getAbsolutePath();

        path = path.substring(0, path.lastIndexOf('\\')) + '\\';
        System.out.println("path --- " + path);

        File dir = new File(path + "html\\");

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
                return type.equals("html");
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
            //String html = getPageFromFile(f.getName());
            //System.out.println(html);
            // String name = f.getName().substring(0, f.getName().lastIndexOf('.'));
            // convertHtmlToJSON(html, name);
        }
    }

    private static String getPageFromFile(String fileName) throws IOException{
        StringBuilder builder = new StringBuilder(30000);

        BufferedReader reader
                = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "UTF-8"));
        String temp;

        while ((temp = reader.readLine()) != null){
            builder.append(temp).append('\n');
        }

        return builder.toString();
    }

    private static void convertHtmlToJSON(String html, String outFile){
        Document document = Jsoup.parse(html);

        /*
          Node of question
         */
        Elements elements = document.select("div.que");

        ArrayList<Question> correctList = new ArrayList<>();
        ArrayList<Question> wrongList = new ArrayList<>();

        for(Element el : elements){
            Element questionText = el.selectFirst("div.qtext");
            Question q = new Question(questionText.text());
            ArrayList<Answer> answers = new ArrayList<>();
            String correctness = el.selectFirst("div.correctness").text().toLowerCase();
            Boolean correct;
            correct = !correctness.contains("част") && !correctness.contains("нев");

            Element table = el.selectFirst("table");

            table.getElementsByTag("span").remove();

            for( Element tr : table.select("tr")){
                String inputAttribute = tr.selectFirst("input").attr("checked");
                if (inputAttribute != null && !inputAttribute.equals("")) {
                    Answer ans = new Answer(correct, tr.selectFirst("label").text().trim());
                    answers.add(ans);
                }else {
                    Answer ans = new Answer(false, tr.selectFirst("label").text().trim());
                    answers.add(ans);
                }
            }

            q.setAnswers(answers);
            /*HashSet<String> set = new HashSet<>();
            set.add(outFile);
            q.setTheme(set);*/

            if (correct){
                correctList.add(q);
            }else{
                wrongList.add(q);
            }

        }

        QuestionList.outQuestionList(correctList, "correct" + outFile +".json");
        QuestionList.outQuestionList(wrongList, "wrong" + outFile +".json");

    }
}
