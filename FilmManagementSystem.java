// Purpose: This is the main class of the Film Management System. It contains the main loop of the program and the main menu.

// import data_structures.BST;
// import data_structures.LinkedList;
// import data_structures.Queue;
// import data_structures.Stack;
// import data_structures.HashMap;
import data_structures.*;

// import source.Actor;
// import source.Film;
// import source.FilmRatingHeap;
// import source.FilmRevenueHeap;
// import source.Initializer;
import source.*;

// import utils.InputHelper;
// import utils.Utils;
// import utils.MainMenu;
import utils.*;

public class FilmManagementSystem {
    private LinkedList<Film> allFilmsList = new LinkedList<Film>(); //this holds all the films in the system
    private LinkedList<Actor> allActorsList = new LinkedList<Actor>(); //this holds all the actors in the system

    private BST filmBST = new BST();
    private BST actorBST = new BST();

    private Stack<Film> revenueStack = new Stack<Film>(); 
    
    private Queue<Film> screeningSchedule = new Queue<>();
    
    private HashMap filmIDMap = new HashMap(10);
    private HashMap actorIDMap = new HashMap(10);
    
    private FilmRatingHeap filmRatingHeap = new FilmRatingHeap();
    private FilmRevenueHeap filmRevenueHeap = new FilmRevenueHeap();

    private boolean isRunning = true;
    
    public void Run() {
        // Initialize all the things
        Initialize();        
        
        //Main loop
        while (isRunning) {
            MainMenu.print();
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
                    System.out.println(screeningSchedule);
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
                    Utils.sleep(0.5);
                    break;    
                default:
                    System.out.println("Invalid choice");
                    break;
                }
            Utils.sleep(0.5); // this Utils.sleep creates a fake program doing something effect
        }

        // To dont forget to close the scanner object, we have to call the close method
        InputHelper.close();
    }

    private void screenFilm() {
        if (screeningSchedule.isEmpty()) {
            System.out.println("No films to screen");
            return;
        }
        System.out.println();
        Film film = screeningSchedule.dequeue();
        System.out.println("Screening film: " + film.getFilmName());
        Utils.sleep(1);
        simulateRevenue(film);

    }

    private void simulateRevenue(Film film) {
        int ticketPrice = (int)(Math.random() * 19) + 1;
        int numScreenings = (int)(Math.random() * 100000);
        int totalRevenue = (int)(ticketPrice * numScreenings);

        System.out.println("Number of screenings: " + numScreenings);
        Utils.sleep(0.05);
        System.out.println("Ticket price: " + ticketPrice);
        Utils.sleep(0.05);
        System.out.println("Total revenue: " + totalRevenue);
        System.out.println();
        film.addRevenue(totalRevenue);
        revenueStack.push(film);
        Utils.sleep(0.05);
    }

    private void Initialize() {
        Utils.clearScreen();
        String welcomeMessage = 
            "*************************************\n" +
            "Welcome to the Film Management System\n"+
            "*************************************\n";

        System.out.println(welcomeMessage);

        //fake loading screen
        System.out.println("Loading all the films and actors...");
        System.out.println("Please wait...");
        for (int i = 0; i < 35; i++) {
            System.out.print("#");
            Utils.sleep(0.1);
        }

        Utils.clearScreen();

        InputHelper.Initialize();
        Initializer init = new Initializer();
        allFilmsList = init.getFilms();
        allActorsList = init.getActors();

        // Add the films and actors to the data structures
        for (Film film : allFilmsList) {
            filmBST.insert(film.getFilmName());
            filmIDMap.put(film.getUniqueFilmID(), film.getUniqueFilmID());
            
            filmRevenueHeap.addFilm(film);
            filmRatingHeap.addFilm(film);
        }

        for (Actor actor: allActorsList) {
            actorBST.insert(actor.getActorName());
            actorIDMap.put(actor.getUniqueActorID(), actor.getUniqueActorID());
        }

        // Add films to the screeningSchedule queue randomly to simulate the screenings
        boolean[] visited = new boolean[allFilmsList.length()];
        for (int i = 0; i < allFilmsList.length(); i++) {
            while (true) {
                int randomIndex = (int)(Math.random() * allFilmsList.length());
                if (!visited[randomIndex]) {
                    screeningSchedule.enqueue(allFilmsList.get(randomIndex));
                    visited[randomIndex] = true;
                    break;
                }
            }
        }

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
                actor = Actor.getActorByName(allActorsList, actor.getActorName());
                film.addActor(actor);
                actor.addFilm(film);
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
        filmIDMap.put(film.getUniqueFilmID(), film.getUniqueFilmID());
        
        filmRevenueHeap.addFilm(film);
        filmRatingHeap.addFilm(film);


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
            System.out.println("---------------------------------");
            System.out.println("Deleting the film: ");
            System.out.println(tempFilm);
            //remove the film from the actors
            LinkedList<Actor> actors = tempFilm.getActorList();
            for (Actor actor : actors) {
                actor.getFilmsParticipated().remove(tempFilm);
            }
            //remove the film from the system
            allFilmsList.remove(tempFilm);
            filmBST.delete(tempFilm.getFilmName());
            filmIDMap.remove(id);

            filmRevenueHeap.removeFilm(tempFilm);
            filmRatingHeap.removeFilm(tempFilm);

            System.out.println("Film deleted");
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

                    filmRevenueHeap.updateFilmRating(tempFilm,updatedFilm);
                    filmRatingHeap.updateFilmRating(tempFilm, updatedFilm);
                    
                    return;
                }            
            }
            
            Utils.clearScreen();
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
                filmRatingHeap.updateFilmRating(tempFilm);
                System.out.println("Rating added successfully");
                System.out.println("\nHere is the updated rating for the film: ");
                System.out.println(tempFilm);
                System.out.println("All Ratings: " + tempFilm.getRatings());
                System.out.println("Average Rating: " + tempFilm.getAverageRating());
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

            //update the revenue heap
            filmRevenueHeap.updateFilmRating(tempFilm);

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
            if (allFilmsList.contains(film) || filmBST.search(film.getFilmName()) != null) {
                System.out.println("Film already exists in the system");
                film = Film.getFilmByName(allFilmsList, film.getFilmName());
                film.addActor(actor);
                actor.addFilm(film);
                continue;
            } 
            allFilmsList.add(film);
            filmBST.insert(film.getFilmName());
            filmIDMap.put(film.getUniqueFilmID(), film.getUniqueFilmID());
            film.addActor(actor);
            actor.addFilm(film);

            filmRevenueHeap.addFilm(film);
            filmRatingHeap.addFilm(film);
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
        Utils.clearScreen();
        System.out.println("ALL ACTORS: " + allActorsList);
        System.out.println("Enter the id of the actor you want to delete");
        int id = InputHelper.getIntegerInput("Actor id: ");

        Actor tempActor = Actor.getActorByID(allActorsList, actorIDMap, id);

        if (tempActor == null) {
            System.out.println("Cant find the actor#" + id );
            return;
        } else {
            System.out.println("---------------------------------");
            System.out.println("Deleting the actor: ");
            System.out.println(tempActor);
            // remove the actor from the films
            LinkedList<Film> films = tempActor.getFilmsParticipated();
            for (Film film : films) {
                film.getActorList().remove(tempActor);
            }
            // remove the actor from the system
            allActorsList.remove(tempActor);
            actorBST.delete(tempActor.getActorName());
            actorIDMap.remove(id);

            System.out.println("Actor deleted");
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

}
