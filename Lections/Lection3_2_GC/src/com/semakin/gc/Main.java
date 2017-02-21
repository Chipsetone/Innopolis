package com.semakin.gc;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Семакин Виктор
 */

public class Main {
    private static List<Object> cache = new LinkedList<>();
    private static final int LOCK_COUNTER = 10000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            switch (sc.next()) {
                case "collectable":
                    System.out.println("collectable");
                    createBigObject();
                    break;
                case "clickable":
                    System.out.println("clickable");
                    cache.add(createBigObject());
                    break;
            }
        }
    }

    public static List<Object> createBigObject(){
        Random rand = new Random();
        List<Object> result = new LinkedList<>();

        for (int i = 0; i < LOCK_COUNTER; i++) {
            String tmp = "currentString" + rand.nextInt();
            result.add(tmp);
            System.out.println(tmp);
        }

        return result;
    }
}
