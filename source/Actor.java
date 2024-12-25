package source;

import data_structures.HashMap;
import data_structures.LinkedList;

import utils.InputHelper;

public class Actor {
    private String actorName;
    private int uniqueActorID;
    private LinkedList<Film> filmsParticipated;

    private static int lastID = 0;

    public Actor(String actorName) {
        this.actorName = actorName;
        this.uniqueActorID = lastID + 1;
        lastID++;
        this.filmsParticipated = new LinkedList<>();
    }

    public Actor(String actorName, int uniqueActorID) {
        this.actorName = actorName;
        this.uniqueActorID = uniqueActorID;
        this.filmsParticipated = new LinkedList<>();
        if (uniqueActorID > lastID) {
            lastID = uniqueActorID;
        }
    }

    // Getters and setters
    public String getActorName() { return actorName; }
    public void setActorName(String actorName) { this.actorName = actorName; }
    public int getUniqueActorID() { return uniqueActorID; }

    // Add film to an actor
    public void addFilm(Film film) {
        filmsParticipated.add(film);
    }

    public LinkedList<Film> getFilmsParticipated() {
        return filmsParticipated;
    }

    public static Actor addActor() {
        String actorName = InputHelper.getStringInput("Enter Actor name: ");

        int actorID = lastID + 1;
        lastID++;

        Actor actor = new Actor(actorName, actorID);
        
        return actor;
    }

    public static Actor getActorByID(LinkedList<Actor> allActors, HashMap actorMap, int id) {
        if (actorMap.containsKey(id)) {
            for (Actor actor : allActors) {
                if (actor.getUniqueActorID() == id) {
                    return actor;
                }
            }
        }
        return null;
    }

    public static Actor getActorByName(LinkedList<Actor> allActors, String name) {
        for (Actor actor : allActors) {
            if (actor.getActorName().equalsIgnoreCase(name)) {
                return actor;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nName=").append(actorName).append('\n');
        sb.append("ID=").append(uniqueActorID).append('\n');
        sb.append("Films Participated=");
        for (Film film : filmsParticipated) {
            sb.append(film.getFilmName()).append(", ");
        }
        sb.append('\n');
        return sb.toString();
    }
}
