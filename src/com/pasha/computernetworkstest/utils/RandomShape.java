package com.pasha.computernetworkstest.utils;

import java.util.ArrayList;
import java.util.Random;

public class RandomShape {
    public static ArrayList<Integer> getRandomShape(int limit){
        Random random = new Random();

        ArrayList<Integer> list = new ArrayList<>();

        while (list.size() != limit){
            int r = random.nextInt(limit);
            if(!list.contains(r))
                list.add(r);
        }
        return list;
    }
}
