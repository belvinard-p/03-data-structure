package org.example;

public class Main {
    public static void main(String[] args) {
        String[] dayOfMonth = new String[31];

        // ✅ CORRECT: Use < not <=
        for (int i = 0; i < dayOfMonth.length; i++) {
            //System.out.println("Day " + (i + 1) + dayOfMonth[i]);
            dayOfMonth[i] = "Day " + (i + 1);
            System.out.println(dayOfMonth[i]);
        }
    }
}