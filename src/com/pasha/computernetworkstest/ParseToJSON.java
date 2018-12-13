package com.pasha.computernetworkstest;

public class ParseToJSON {
    /*public static void main(String[] args) {
        //parse("all.txt");
        /*ArrayList<Question> list = getFromJSON("71218_unanswered.json");
        ArrayList<Question> unique = getFromJSON("UniqueQuestion.json");
        ArrayList<Question> forgotten = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(Question q : unique){
            set.add(q.question.toLowerCase().substring(0, Math.min(200, q.question.length())));
        }
        for (Question q : list){
            if(!set.contains(q.question.toLowerCase().substring(0, Math.min(200, q.question.length())))) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(q);
                Scanner scanner = new Scanner(System.in);
                int t = scanner.nextInt();
                if(t == 1){
                    forgotten.add(q);
                }
            }
        }

        outQuestionList(forgotten, "forgotten.json");*/
        //unique();


    /*}
/*
    public static void unique(){
            ArrayList<Question> list = QuestionList.getFromJSON("__UniqueQuestion.json");
            int counter=0;
            if (list == null){
                System.out.println("snfidsbfjsbfjwebfiwbgwbgfuewgbewbgewbgjewgbjkewbgjkbvjwebfw");
                return;
            }
            System.out.println("Было " + list.size());
            for(int i = 0; i < list.size(); i++){
                for (int j = i + 1; j < list.size(); j++){
                    if(list.get(i).getQuestionText().toLowerCase()
                            .substring(0,-1+Math.min(30,list.get(i).getQuestionText().length())).
                            equals(list.get(j).getQuestionText().toLowerCase()
                                    .substring(0,-1+Math.min(30,list.get(j).getQuestionText().length())))){
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("----------------i----------------");
                        System.out.println(list.get(i));
                        System.out.println("----------------j----------------");
                        System.out.println(list.get(j));
                        Scanner scanner = new Scanner(System.in);
                        int q = scanner.nextInt();
                        if(q == 1){
                            list.remove(j);
                            j--;
                            counter++;
                        }else if(q==0){
                            list.remove(i);
                            j = list.size();
                            counter++;
                        }

                    }
                }
            }
            QuestionList.outQuestionList(list, "UniqueQuestion.json");
            System.out.println("удалено " + counter);
            System.out.println("осталось " + list.size());
    }

    private static void parse(String file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // pre-JSON-object for output to "FullQuestion.json"
            ArrayList<Question> fullQuestion = new ArrayList<>();
            //pre-JSON-object for output to "Unanswered.json"
            ArrayList<Question> unanswered = new ArrayList<>();

            String question;
            int counter = 0;
            int notSure = 0;
            Set<String> set = new HashSet<>();
            while ((question = reader.readLine()) != null){
                counter++;
                String correctString = reader.readLine();

                String[] correctAnswers = correctString.split(";");
                String[] wrongAnswers = reader.readLine().split(";");
                String empty = reader.readLine();

                if(empty != null && !empty.isEmpty()){
                    System.out.println("не пусто около " + 4 * counter);
                    System.out.println(question);
                    System.out.println(correctString);
                    System.out.println("~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
                }

                // temp objects for copy information from "all.txt"
                Question temp = new Question(question);


                if(!correctString.isEmpty()){

                    set.add(question);

                    //pw.println(question);
                    //pw.println(correctString);
                    //pw.println();
                    for (String str: correctAnswers) {
                        if(!str.isEmpty()){
                            Answer a = new Answer(true, str);
                            temp.getAnswers().add(a);
                        }else{
                            System.out.println("[ EXCEPTION ]");
                            System.out.println(question);
                            System.out.println(correctString);
                            //System.out.println(wrongAnswers.toString());
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


                if(correctString.isEmpty() ){
                    unanswered.add(temp);
                }else{
                    fullQuestion.add(temp);
                    if(correctString.contains("??")){
                        notSure++;
                    }
                }

            }
            reader.close();
            System.out.println("Полных - " + fullQuestion.size());
            System.out.println("Неполных - " + unanswered.size());
            System.out.println("Не уверен - " + notSure);
            System.out.println("set " + set.size());

            PrintWriter pwFull = new PrintWriter(new File("71218_full.json"));
            PrintWriter pwUnanswered = new PrintWriter(new File("71218_unanswered.json"));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String full = gson.toJson(new QuestionList().setEntity(fullQuestion));
            String unansweredStr = gson.toJson(new QuestionList().setEntity(unanswered));

            pwFull.println(full);
            pwUnanswered.println(unansweredStr);

            pwFull.close();
            pwUnanswered.close();


        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }*/

}

