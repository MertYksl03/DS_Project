package utils;

import java.io.IOException;

public class Utils {
    public static void sleep(double seconds) {
           int milliseconds = (int)(seconds * 1000);
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted: " + e.getMessage());
           }
        }
    
        public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Windows-specific command
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix/Linux/Mac command
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
