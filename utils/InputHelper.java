package utils;

import java.util.Scanner;

public class InputHelper {
    private static Scanner input;

    public static void Initialize() {
        input = new Scanner(System.in);
    }

    public static int getIntegerInput(String prompt) {
        
        int ans = 0;
        boolean inputValid;
        do {
            System.out.print(prompt);
            String s = input.nextLine();
            s = s.trim(); // Remove leading and trailing whitespaces for better input validation
            // Convert string input to integer
            try {
                ans = Integer.parseInt(s);
                inputValid = true;
            } catch (Exception e) {
                inputValid = false;
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        } while (!inputValid);
        return ans;
    }

    public static double getDoubleInput(String prompt) {
        double ans = 0;
        boolean inputValid;
        do {
            System.out.print(prompt);
            String s = input.nextLine();
            // Convert string input to double
            try {
                ans = Double.parseDouble(s);
                inputValid = true;
            } catch (Exception e) {
                inputValid = false;
                System.out.println("Invalid input. Please enter a valid double.");
            }
        } while (!inputValid);
        return ans;
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    public static char getCharInput(String prompt) {
        System.out.print(prompt);
        String temp = input.nextLine();
        if (temp.length() == 0) {
            return ' ';
        } else {
            char ch = temp.charAt(0);
            return ch;
        }
    }

    public static void close() {
        input.close();
    }
}
