package utils;
public class MainMenu {
    public static void print() {
        // Instead of calling System.out.println() multiple times, you can use a single call with a concatenated string
        String menu = 
        "*************************************\n" +
        "--------------MAIN MENU--------------\n" +
        "*************************************\n" +
        "1. Display all films\n" +
        "2. Add Film\n" +
        "3. Delete Film\n" +
        "4. Update Film\n" +
        "5. Rate a Film\n" +
        "6. Manually add the revenue for a film\n" +
        "7. Display all actors\n" +
        "8. Add Actor\n" +
        "9. Delete Actor\n" +
        "10. Search Film or Actor\n" +
        "11. Display all films by revenue\n" +
        "12. Display all films by ranking\n" +
        "13. Display the screening schedule\n" +
        "14. Screen a film\n" +
        "15. List the last revenue calculations\n" +
        "16. Exit\n" +
        "*************************************";
    
    System.out.println(menu);

    }
}
