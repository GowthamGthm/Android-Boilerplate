package abderrazak.com.recycleviewcardview.util;

import java.util.Comparator;

import abderrazak.com.recycleviewcardview.data.model.Movie;

/**
 * Created by abderrazak on 09/03/2016.
 */
public class CustomComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        return m1.getTitle().compareTo(m2.getTitle());
    }
}
