import data_structures.LinkedList;
import data_structures.HashMap;

import utils.InputHelper;

public class Film {
    private String filmName;
    private int uniqueFilmID;
    private String genre;
    private int releaseYear;
    private double totalRevenue;
    private LinkedList<Actor> actorList;
    private LinkedList<Integer> ratings; //this holds all the ratings for a film
    private double averageRating; // Dont forget to update this when a new rating is added
    //TODO: Implement ratings

    private static int lastID = 0;
    
    public Film(String filmName,String genre, int releaseYear) {
        this.filmName = filmName;
        this.uniqueFilmID = lastID + 1;
        lastID++;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.actorList = new LinkedList<>();
        this.totalRevenue = 0;
        this.ratings = new LinkedList<>();
        this.averageRating = 0;
    }

    public Film(String filmName,int id, String genre, int releaseYear) {
        this.filmName = filmName;
        this.uniqueFilmID = id;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.actorList = new LinkedList<>();
        this.totalRevenue = 0;
        this.ratings = new LinkedList<>();
        this.averageRating = 0;
    }

    // Getters and setters
    public String getFilmName() { return filmName; }
    public void setFilmName(String filmName) { this.filmName = filmName; }
    public int getUniqueFilmID() { return uniqueFilmID; }
    public String getGenre() { return genre; }
    public int getReleaseYear() { return releaseYear; }
    public double getTotalRevenue() { return totalRevenue; }
    public void addRevenue(double revenue) { this.totalRevenue += revenue; }
    public double getAverageRating() { return averageRating; }
    
    //adds an actor to a film
    public void addActor(Actor actor) {
        actorList.add(actor);
    }

    public LinkedList<Actor> getActorList() {
        return actorList;
    }

    public void addRating(int rating) {
        ratings.add(rating);
        averageRating = getAverage(ratings);
    }

    private double getAverage(LinkedList<Integer> ratings) {
        double sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum / (double)(ratings.length());
    }

    //ask user for film details create new film
    public static Film addFilm() {
        String filmName = InputHelper.getStringInput("Enter film name: ");
        String genre = InputHelper.getStringInput("Enter genre: ");
        int releaseYear = InputHelper.getIntegerInput("Enter release year: ");
        
        int filmID = lastID + 1;
        lastID++;
        
        Film film = new Film(filmName, genre, releaseYear);
        film.uniqueFilmID = filmID;

        return film;
    }

    public static Film addFilm(int id) {
        String filmName = InputHelper.getStringInput("Enter film name: ");
        String genre = InputHelper.getStringInput("Enter genre: ");
        int releaseYear = InputHelper.getIntegerInput("Enter release year: ");

        Film film = new Film(filmName, genre, releaseYear);
        film.uniqueFilmID = id;

        return film;
    }

    public static Film getFilmByID(LinkedList<Film> allFilms, HashMap filMap, int id) {
        if (filMap.containsKey(id)) {
            for (Film film : allFilms) {
                if (film.getUniqueFilmID() == id) {
                    return film;
                }
            }
        }
        return null;
    }

    public static Film getFilmByName(LinkedList<Film> allFilms, String name) {
        for (Film film : allFilms) {
            if (film.getFilmName().equalsIgnoreCase(name)) {
                return film;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
          .append("Name=").append(filmName).append('\n')
          .append("ID=").append(uniqueFilmID).append("\n")
          .append("Genre=").append(genre).append('\n')
          .append("Release Year=").append(releaseYear).append("\n")
          .append("Revenue=").append(totalRevenue).append('\n')
          .append("Average Rating=").append(averageRating).append('\n')
          .append("Actors=");
        for (Actor actor : actorList) {
            sb.append(actor.getActorName()).append(", ");
        }
        sb.append('\n');
        return sb.toString();
    }
}
