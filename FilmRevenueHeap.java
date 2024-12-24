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
        if (revenueHeap.isFull()) { // If the heap is full, double the size
            doubleSize();
        } 
        revenueHeap.insert(film.getFilmName(), film.getTotalRevenue(), film.getReleaseYear());
    }

    private void doubleSize() {
        int oldSize = maxSize;
        maxSize = maxSize * 2;
        MaxHeap newHeap = new MaxHeap(maxSize);
        for (int i = 0; i < oldSize; i++) {
            newHeap.insert(revenueHeap.extractMax());
        }
        revenueHeap = newHeap;
        newHeap = null;
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