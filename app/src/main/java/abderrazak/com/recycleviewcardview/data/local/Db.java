package abderrazak.com.recycleviewcardview.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Arrays;

import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.util.ArraysUtil;

/**
 * Created by abderrazak on 29/03/2016.
 */
public class Db {

    public Db() {}

    public abstract static class MovieTable {

        public static final String TABLE_NAME = "boiler_plate_movie";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_RELASEYEAR = "release_year";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_GENRE = "genre";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_TITLE + " TEXT PRIMARY KEY, " +
                        COLUMN_IMAGE + " TEXT NOT NULL, " +
                        COLUMN_RELASEYEAR + " TEXT NOT NULL, " +
                        COLUMN_RATING + " TEXT NOT NULL, " +
                        COLUMN_GENRE + " TEXT NOT NULL, " +
                        " ); ";

        public static ContentValues toContentValues(Movie movie) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, movie.getTitle());
            values.put(COLUMN_IMAGE, movie.getThumbnailUrl());
            values.put(COLUMN_RELASEYEAR, movie.getYear());
            values.put(COLUMN_RATING, movie.getRating());
            values.put(COLUMN_GENRE, ArraysUtil.convertArrayListToString(movie.getGenre()));

            return values;
        }

        public static Movie parseCursor(Cursor cursor) {
            Movie movie = new Movie();
            movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
            movie.setThumbnailUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)));

            ArrayList<String> names = new ArrayList<>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENRE)).split(",")));
            movie.setGenre(names);
            movie.setRating(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_RATING)));
            movie.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RELASEYEAR)));

            return movie;
        }
    }
}
