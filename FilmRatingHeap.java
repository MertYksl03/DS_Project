
import java.util.PriorityQueue;

public class FilmRatingHeap {
    private PriorityQueue<Film> rankingHeap;

    public FilmRatingHeap() {
        rankingHeap = new PriorityQueue<>((f1, f2) -> {
            if (Double.compare(f2.getAverageRating(), f1.getAverageRating()) == 0) {
                return Integer.compare(f1.getReleaseYear(), f2.getReleaseYear());
            }
            return Double.compare(f2.getAverageRating(), f1.getAverageRating());
        });
    }

    public void updateRanking(Film film) {
        rankingHeap.remove(film);
        rankingHeap.add(film);
    }

    public void addFilm(Film film) {
        rankingHeap.add(film);
    }

    public Film getTopFilm() {
        return rankingHeap.peek();
    }

    public void removeFilm(Film film) {
        rankingHeap.remove(film);
    }

    @Override
    public String toString() {
        return rankingHeap.toString();
    }
}
