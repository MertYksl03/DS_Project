import java.io.IOException;

//import data_structures.*;
import data_structures.BST;
import data_structures.LinkedList;
import data_structures.Queue;
import data_structures.Stack;
import data_structures.HashMap;

import utils.*;

public class FilmManagementSystem {
    private LinkedList<Film> allFilmsList = new LinkedList<Film>(); //this holds all the films in the system
    private LinkedList<Actor> allActorsList = new LinkedList<Actor>(); //this holds all the actors in the system
    private BST filmBST = new BST();
    private BST actorBST = new BST();
    
    private FilmRatingHeap filmRatingHeap = new FilmRatingHeap();
    private FilmRevenueHeap filmRevenueHeap = new FilmRevenueHeap();
    private Stack<Film> revenueStack = new Stack<Film>(); 
    private Queue<Film> screenings = new Queue<>();
    private HashMap filmIDMap = new HashMap(10);
    private HashMap actorIDMap = new HashMap(10);

    private boolean isRunning = true;
    
    public void Run() {
        // Initialize all the things
        Initialize();        
        
        //Main loop
        while (isRunning) {
            printMenu();
            int choice = InputHelper.getIntegerInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    System.out.println(allFilmsList);
                    break;
                case 2:
                    addFilm();
                    break;
                case 3:
                    deleteFilm();
                    break;
                case 4:
                    updateFilm();
                    break;
                case 5:
                    rateFilm();
                    break;
                case 6:
                    addManualRevenue();
                    break;
                case 7:
                    System.out.println(allActorsList);
                    break;
                case 8:
                    addActor();
                    break;
                case 9:
                    deleteActor();
                    break;  
                case 10:
                    searchFilmOrActor();
                    break;
                case 11:
                    displayFilmsByRevenue();
                    break;
                case 12:
                    displayFilmsByRanking();
                    break;
                case 13:
                    System.out.println(screenings);
                    break;
                case 14:
                    screenFilm();
                    break;
                case 15:
                    listRevenueCalculations();
                    break;
                case 16:
                    isRunning = false;
                    System.out.println("Exiting...");
                    sleep(0.5);
                    break;    
                default:
                    System.out.println("Invalid choice");
                    break;
                }
            sleep(0.5); // this one second sleep creates a fake program doing something effect
        }

        // To dont forget to close the scanner object, we have to call the close method
        close();
    }

    private void printMenu() {
        System.out.println("*************************************");
        System.out.println("--------------MAIN MENU--------------");
        System.out.println("*************************************");
        System.out.println("1. Display all films");
        System.out.println("2. Add Film");
        System.out.println("3. Delete Film");
        System.out.println("4. Update Film");
        System.out.println("5. Rate a Film");
        System.out.println("6. Manually add the revenue for a film");
        System.out.println("7. Display all actors");
        System.out.println("8. Add Actor");
        System.out.println("9. Delete Actor");
        System.out.println("10. Search Film or Actor");
        System.out.println("11. Display all films by revenue");
        System.out.println("12. Display all films by ranking");
        System.out.println("13. Display all screenings");
        System.out.println("14. Screen a film");
        System.out.println("15. List the last revenue calculations");
        System.out.println("16. Exit");
        System.out.println("*************************************");
    }

    private void sleep(double seconds) {
       int milliseconds = (int)(seconds * 1000);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    }

    private void screenFilm() {
        if (screenings.isEmpty()) {
            System.out.println("No films to screen");
            return;
        }
        System.out.println();
        Film film = screenings.dequeue();
        System.out.println("Screening film: " + film.getFilmName());
        sleep(1);
        simulateRevenue(film);

    }

    private void simulateRevenue(Film film) {
        int ticketPrice = (int)(Math.random() * 19) + 1;
        int numScreenings = (int)(Math.random() * 100000);
        int totalRevenue = (int)(ticketPrice * numScreenings);

        System.out.println(film.getFilmName() + " has " + numScreenings + " screenings");
        sleep(0.05);
        System.out.println("Ticket price: " + ticketPrice);
        sleep(0.05);
        System.out.println("Total revenue: " + totalRevenue);
        System.out.println();
        film.addRevenue(totalRevenue);
        revenueStack.push(film);
        sleep(0.05);
    }

    private void Initialize() {
        clearScreen();
        System.out.println("*************************************");
        System.out.println("Welcome to the Film Management System");
        System.out.println("*************************************");

        //fake loading screen
        System.out.println("Loading all the films and actors...");
        System.out.println("Please wait...");
        for (int i = 0; i < 35; i++) {
            System.out.print("#");
            sleep(0.1);
        }

        clearScreen();

        InputHelper.Initialize();
        Initialize init = new Initialize();
        allFilmsList = init.getFilms();
        allActorsList = init.getActors();

        //initialize the film ranking heap
        for (Film film : allFilmsList) {
            filmRatingHeap.addFilm(film);
            filmRevenueHeap.addFilm(film);
            filmBST.insert(film.getFilmName());
            screenings.enqueue(film);
            filmIDMap.put(film.getUniqueFilmID(), film.getUniqueFilmID());
        }

        for (Actor actor: allActorsList) {
            actorBST.insert(actor.getActorName());
            actorIDMap.put(actor.getUniqueActorID(), actor.getUniqueActorID());
        }

        
    }

    private void close() {
        InputHelper.close();
    }

    private void addFilm() {
        Film film = Film.addFilm();

        //check if the film already exists
        if (allFilmsList.contains(film) || filmBST.search(film.getFilmName()) != null) {
            System.out.println("Film already exists in the system");
            return;
        }

        System.out.println("Enter the number of actors in the film: ");
        int numActors = InputHelper.getIntegerInput("Enter number of actors: ");
        for (int i = 0; i < numActors; i++) {
            Actor actor = Actor.addActor();
            if (allActorsList.contains(actor) || actorBST.search(actor.getActorName()) != null) {
                System.out.println("Actor already exists in the system");
                continue;
            } 
            film.addActor(actor);
            actor.addFilm(film);
            allActorsList.add(actor);
            actorBST.insert(actor.getActorName());
            actorIDMap.put(actor.getUniqueActorID(), actor.getUniqueActorID());
        }

        allFilmsList.add(film);
        filmBST.insert(film.getFilmName());
        filmRatingHeap.addFilm(film);
        filmRevenueHeap.addFilm(film);
        filmIDMap.put(film.getUniqueFilmID(), film.getUniqueFilmID());


    }

    private void deleteFilm() {
    	if (allFilmsList.empty()) {
    		System.out.println("No films in the system");
    		return;
    	}
        System.out.println("ALL FILMS: " + allFilmsList);
        System.out.println("Enter the id of the film you want to delete: ");
        int id = InputHelper.getIntegerInput("Film id: ");
        Film tempFilm = Film.getFilmByID(allFilmsList, filmIDMap, id);

        if (tempFilm == null) {
            System.out.println("Cant find the film#" + id );
            return;
        } else {
            //remove the film from the actors
            LinkedList<Actor> actors = tempFilm.getActorList();
            for (Actor actor : actors) {
                actor.getFilmsParticipated().remove(tempFilm);
            }
            //remove the film from the system
            allFilmsList.remove(tempFilm);
            filmBST.delete(tempFilm.getFilmName());
            filmRatingHeap.removeFilm(tempFilm);
            filmRevenueHeap.removeFilm(tempFilm);
            filmIDMap.remove(id);
        }
    }

    private void updateFilm() {
        if (allFilmsList.empty()) {
            System.out.println("No films in the system");
            return;
        }
        while (true) {
            System.out.println(allFilmsList);
            int id = InputHelper.getIntegerInput("Enter the id of the film you want to update: ");
            
            Film tempFilm = Film.getFilmByID(allFilmsList, filmIDMap, id);

            if (tempFilm == null) {
                System.out.println("Cant find the film#" + id );
                break;
            } else {
                System.out.println(tempFilm);
                System.out.println("Is this the film you want to update?(y/n)");
                char choice = InputHelper.getCharInput("Y or N: ");
                if (choice == 'Y' || choice == 'y' || choice == ' ') {
                    LinkedList<Actor> actors = tempFilm.getActorList(); //get the actors of the film before updating
                    allFilmsList.remove(tempFilm);

                    Film updatedFilm = Film.addFilm(id);

                    for (Actor actor : actors) {
                        updatedFilm.addActor(actor);
                    }

                    //update the film in data structures
                    allFilmsList.add(updatedFilm);
                    filmBST.insert(updatedFilm.getFilmName());
                    filmRatingHeap.addFilm(updatedFilm);
                    filmRevenueHeap.addFilm(updatedFilm);
                    
                    return;
                }            
            }
            
            clearScreen();
        }
        
    }

    private void rateFilm() {
        if (allFilmsList.empty()) {
            System.out.println("No films in the system");
            return;
        }
        System.out.println(allFilmsList);
        int id = InputHelper.getIntegerInput("Enter the id of the film you want to rate: ");

        Film tempFilm = Film.getFilmByID(allFilmsList, filmIDMap, id);

        if (tempFilm == null) {
            System.out.println("Cant find the film#" + id );
            return;
        } else {
            System.out.println(tempFilm);
            System.out.println("Enter the rating for the film (1-10)");
            int rating = InputHelper.getIntegerInput("Enter the rating for the film: ");
            if (rating < 1 || rating > 10) {
                System.out.println("Invalid rating");
            } else {
                tempFilm.addRating(rating);
                filmRatingHeap.updateRanking(tempFilm);
            }
        }
    }

    private void addManualRevenue() {
        if (allFilmsList.empty()) {
            System.out.println("No films in the system");
            return;
        }
        System.out.println(allFilmsList);
        int id = InputHelper.getIntegerInput("Enter the id of the film you want to add revenue: ");

        Film tempFilm = Film.getFilmByID(allFilmsList, filmIDMap, id);

        if (tempFilm == null) {
            System.out.println("Cant find the film#" + id );
            return;
        } else {
            System.out.println(tempFilm);
            double ticketPrice = InputHelper.getDoubleInput("Enter the ticket price: ");
            double numScreenings = InputHelper.getDoubleInput("Enter the number of screenings: ");

            double revenue = (int)(ticketPrice * numScreenings);
            
            tempFilm.addRevenue(revenue);
            revenueStack.push(tempFilm);
            filmRevenueHeap.updateRanking(tempFilm);

            System.out.println("Revenue added successfully");
            System.out.println("\nHere is the updated revenue for the film: ");
            System.out.println(tempFilm);

        }
    }

    private void addActor() {
        Actor actor = Actor.addActor();

        //check if the actor already exists
        if (allActorsList.contains(actor) || actorBST.search(actor.getActorName()) != null) {
            System.out.println("Actor already exists in the system");
            return;
        }

        System.out.println("Enter the number of films that this actor paricipated: ");
        int numFilms = InputHelper.getIntegerInput("Enter number of films: ");
        for (int i = 0; i < numFilms; i++) {
            Film film = Film.addFilm();
            film.addActor(actor);
            actor.addFilm(film);
            if (allFilmsList.contains(film) || filmBST.search(film.getFilmName()) != null) {
                System.out.println("Film already exists in the system");
            } else {
                allFilmsList.add(film);
                filmBST.insert(film.getFilmName());
                filmRatingHeap.addFilm(film);
                filmRevenueHeap.addFilm(film);
                filmIDMap.put(film.getUniqueFilmID(), film.getUniqueFilmID());
            }
        }

        allActorsList.add(actor);
        actorBST.insert(actor.getActorName());
        actorIDMap.put(actor.getUniqueActorID(), actor.getUniqueActorID());
    }

    private void deleteActor() {
		if (allActorsList.empty()) {
			System.out.println("No actors in the system");
			return;
		}
        clearScreen();
        System.out.println("ALL ACTORS: " + allActorsList);
        System.out.println("Enter the id of the actor you want to delete");
        int id = InputHelper.getIntegerInput("Actor id: ");

        Actor tempActor = Actor.getActorByID(allActorsList, actorIDMap, id);

        if (tempActor == null) {
            System.out.println("Cant find the actor#" + id );
            return;
        } else {
            // remove the actor from the films
            LinkedList<Film> films = tempActor.getFilmsParticipated();
            for (Film film : films) {
                film.getActorList().remove(tempActor);
            }
            // remove the actor from the system
            allActorsList.remove(tempActor);
            actorBST.delete(tempActor.getActorName());
            actorIDMap.remove(id);
        }
    }

    private void searchFilmOrActor() {
        String search = InputHelper.getStringInput("Enter the name of the film or actor you want to search: ");
        //search in the BST
        String filmName = filmBST.search(search);
        String actorName = actorBST.search(search);

        if (filmName != null) {
            Film film = Film.getFilmByName(allFilmsList, filmName);
            System.out.println("\nFilm found: ");
            System.out.println(film);
            return;
        } else if (actorName != null) {
            Actor actor = Actor.getActorByName(allActorsList, actorName);
            System.out.println("\nActor found: ");
            System.out.println(actor);
            return;
        } else {
            System.out.println("\nCant find the film or actor with the name: " + search);
        }

    }

    private void displayFilmsByRanking() {
        System.out.println("All films by ranking: ");
        System.out.println(filmRatingHeap);
    }

    private void displayFilmsByRevenue() {
        System.out.println("All films by revenue: ");
        System.out.println(filmRevenueHeap);
    }

    private void listRevenueCalculations() {
        int number = InputHelper.getIntegerInput("Enter the number of revenue calculations you want to list: ");
        System.out.println("Last " + number + " revenue calculations: ");
        revenueStack.printStacktoN(number);
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
