import data_structures.MaxHeap; 

public class FilmRatingHeap {
    private MaxHeap ratingHeap;
    private int maxSize;

    public FilmRatingHeap() {
        this.maxSize = 10;
        this.ratingHeap = new MaxHeap(maxSize);
    }

    public FilmRatingHeap(int maxSize) {
        this.maxSize = maxSize;
        this.ratingHeap = new MaxHeap(maxSize);
    }

    public void addFilm(Film film) {
        if (ratingHeap.isFull()) { // if the heap is full, double the size
            doubleSize();
        }
        ratingHeap.insert(film.getFilmName(), film.getAverageRating(), film.getReleaseYear());
    }

    private void doubleSize() {
        int oldSize = maxSize;
        maxSize = maxSize * 2;
        MaxHeap newHeap = new MaxHeap(maxSize);
        for (int i = 0; i < oldSize; i++) {
            newHeap.insert(ratingHeap.extractMax());
        }
        ratingHeap = newHeap;
        newHeap = null;
    }

    public void removeFilm(Film film) {
        ratingHeap.delete(film.getFilmName());
    }

    public void updateFilmRating(Film oldFilm, Film updatedFilm) {
        ratingHeap.delete(oldFilm.getFilmName());
        ratingHeap.insert(updatedFilm.getFilmName(), updatedFilm.getAverageRating(), updatedFilm.getReleaseYear());
    }

    public void updateFilmRating(Film film) {
        ratingHeap.delete(film.getFilmName());
        ratingHeap.insert(film.getFilmName(), film.getAverageRating(), film.getReleaseYear());
    }

    public void printTopFilms(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(ratingHeap.getMax());
        }
    }

    @Override
    public String toString() {
        return ratingHeap.toString();
    }
}