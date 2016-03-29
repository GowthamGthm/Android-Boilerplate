package abderrazak.com.recycleviewcardview.data.model;

import java.util.ArrayList;

/**
 * Created by abderrazak on 07/03/2016.
 */
public class Movie {

    private String title, image;
    private int releaseYear;
    private double rating;
    private ArrayList<String> genre;

    public Movie() {
    }

    public Movie(String title, String image, int releaseYear, double rating, ArrayList<String> genre) {
        this.title = title;
        this.image = image;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return image;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.image = thumbnailUrl;
    }

    public int getYear() {
        return releaseYear;
    }

    public void setYear(int year) {
        this.releaseYear = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie {" +
                "title='" + title + '\'' +
                ", thumbnailUrl='" + image + '\'' +
                ", year=" + releaseYear +
                ", rating=" + rating +
                ", genre=" + genre +
                '}';
    }
}
