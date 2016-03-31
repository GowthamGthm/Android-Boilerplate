package abderrazak.com.recycleviewcardview.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by abderrazak on 07/03/2016.
 */
public class Movie implements Parcelable{

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

    public Movie(Parcel in) {
        title = in.readString();
        image = in.readString();
        releaseYear = in.readInt();
        rating = in.readDouble();
        genre = in.createStringArrayList();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) { return new Movie(in); }

        @Override
        public Movie[] newArray(int size) { return new Movie[size]; }
    };

    public Movie(Movie movie){
        this.title = movie.getTitle();
        this.image = movie.getThumbnailUrl();
        this.releaseYear = movie.getYear();
        this.genre = movie.getGenre();
        this.rating = movie.getRating();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getTitle());
        dest.writeString(this.getThumbnailUrl());
        dest.writeDouble(this.getRating());
        dest.writeInt(this.getYear());
        dest.writeArray(this.getGenre().toArray());
    }
}
