// Purpose: This class is used to store the top films based on their total revenue and release year.

import java.util.PriorityQueue;

public class FilmRevenueHeap {
    private PriorityQueue<Film> rankingHeap;

    public FilmRevenueHeap() {
        rankingHeap = new PriorityQueue<>((f1, f2) -> {
            if (Double.compare(f2.getTotalRevenue(), f1.getTotalRevenue()) == 0) {
                return Integer.compare(f1.getReleaseYear(), f2.getReleaseYear());
            }
            return Double.compare(f2.getTotalRevenue(), f1.getTotalRevenue());
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
