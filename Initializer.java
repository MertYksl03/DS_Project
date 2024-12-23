//Purpose: Initialize the data structures with some random data

import data_structures.*;

public class Initializer {
    private LinkedList<Film> films;
    private LinkedList<Actor> actors;


    public Initializer() {
        this.films = new LinkedList<>();
        this.actors = new LinkedList<>();

        initializeFilms();
        initializeActors();

        addRelations();
    }

    public LinkedList<Film> getFilms() {
        return films;
    }

    public LinkedList<Actor> getActors() {
        return actors;
    }


    private void initializeFilms() {
        films.add(new Film("The Shawshank Redemption", "Drama", 1994));
        films.add(new Film("The Godfather", "Crime", 1972));
        films.add(new Film("The Godfather: Part II", "Crime", 1974));
        films.add(new Film("The Dark Knight", "Action", 2008));
        films.add(new Film("12 Angry Men", "Drama", 1957));
        films.add(new Film("Schindler's List", "Biography", 1993));
        films.add(new Film("The Lord of the Rings: The Return of the King", "Adventure", 2003));
        films.add(new Film("Pulp Fiction", "Crime", 1994));
        films.add(new Film("The Lord of the Rings: The Fellowship of the Ring", "Adventure", 2001));
        films.add(new Film("Forrest Gump", "Drama", 1994));
        films.add(new Film("Fight Club", "Drama", 1999));
        films.add(new Film("Inception", "Action", 2010));
        films.add(new Film("The Lord of the Rings: The Two Towers", "Adventure", 2002));
        films.add(new Film("Star Wars: Episode V - The Empire Strikes Back", "Action", 1980));
        films.add(new Film("The Matrix", "Action", 1999));
        films.add(new Film("Goodfellas", "Biography", 1990));
        films.add(new Film("One Flew Over the Cuckoo's Nest", "Drama", 1975));
        films.add(new Film("Seven Samurai", "Adventure", 1954));
        films.add(new Film("Se7en", "Crime", 1995));
        films.add(new Film("City of God", "Crime", 2002));
        films.add(new Film("Life Is Beautiful", "Comedy", 1997));
        films.add(new Film("The Silence of the Lambs", "Crime", 1991));

        // Add random ratings to films
        for (Film film : films) {
            film.addRating((int)(Math.random() * 10));;
        }

        // Add random revenue to films
        for (Film film : films) {
            film.addRevenue((int)(Math.random() * 1000000));
        }
    }

    private void initializeActors() {
        actors.add(new Actor("Tim Robbins"));
        actors.add(new Actor("Morgan Freeman"));
        actors.add(new Actor("Marlon Brando"));
        actors.add(new Actor("Al Pacino"));
        actors.add(new Actor("Robert De Niro"));
        actors.add(new Actor("Christian Bale"));
        actors.add(new Actor("Heath Ledger"));
        actors.add(new Actor("Jack Nicholson"));
        actors.add(new Actor("Liam Neeson"));
        actors.add(new Actor("Elijah Wood"));
        actors.add(new Actor("Viggo Mortensen"));
        actors.add(new Actor("John Travolta"));
        actors.add(new Actor("Samuel L. Jackson"));
        actors.add(new Actor("Tom Hanks"));
        actors.add(new Actor("Edward Norton"));
        actors.add(new Actor("Brad Pitt"));
        actors.add(new Actor("Leonardo DiCaprio"));
        actors.add(new Actor("Harrison Ford"));
        actors.add(new Actor("Keanu Reeves"));
        actors.add(new Actor("Ray Liotta"));
        actors.add(new Actor("Joe Pesci"));
        actors.add(new Actor("Jack Nicholson"));
        actors.add(new Actor("Anthony Hopkins"));
        actors.add(new Actor("Jodie Foster"));
        actors.add(new Actor("Toshirô Mifune"));
        actors.add(new Actor("Takashi Shimura"));
        actors.add(new Actor("Morgan Freeman"));
        actors.add(new Actor("Edward Norton"));
        actors.add(new Actor("Brad Pitt"));
        actors.add(new Actor("Helena Bonham Carter"));
    }

    private void addRelations() {
        // Add random actors to films
        for (Film film : films) {
            film.addActor(actors.get((int)(Math.random() * actors.length())));
            film.addActor(actors.get((int)(Math.random() * actors.length())));
        }

        // Add films to actors
        for (Actor actor : actors) {
            actor.addFilm(films.get((int)(Math.random() * films.length())));
            actor.addFilm(films.get((int)(Math.random() * films.length())));
        }
    }
}
