import data_structures.MaxHeap; 

public class FilmRevenueHeap {
    private MaxHeap revenueHeap;
    private int maxSize;

    public FilmRevenueHeap() {
        this.maxSize = 10;
        this.revenueHeap = new MaxHeap(maxSize);
    }

    public FilmRevenueHeap(int maxSize) {
        this.maxSize = maxSize;
        this.revenueHeap = new MaxHeap(maxSize);
    }

    public void addFilm(Film film) {
        if (revenueHeap.isFull()) {
            int oldSize = maxSize;
            maxSize = maxSize * 2;
            MaxHeap newHeap = new MaxHeap(maxSize);
            for (int i = 0; i < oldSize; i++) {
                if (revenueHeap.isEmpty()) {
                    break;
                }
                newHeap.insert(revenueHeap.extractMax());
            }
            revenueHeap = newHeap;
            newHeap = null;
        } else {
            revenueHeap.insert(film.getFilmName(), film.getTotalRevenue(), film.getReleaseYear());
        }
    }

    public void removeFilm(Film film) {
        revenueHeap.delete(film.getFilmName());
    }

    public void updateFilmRating(Film film) {
        revenueHeap.delete(film.getFilmName());
        revenueHeap.insert(film.getFilmName(), film.getTotalRevenue(), film.getReleaseYear());
    }

    public void printTopFilms(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(revenueHeap.getMax());
        }
    }

    @Override
    public String toString() {
        return revenueHeap.toString();
    }
}