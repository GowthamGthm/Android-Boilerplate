package abderrazak.com.recycleviewcardview.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.ui.MvpView;
import abderrazak.com.recycleviewcardview.util.EventBusHelper;

/**
 * Created by abderrazak on 23/03/2016.
 */
public interface MainMvpView extends MvpView {

    void initializeViews();

    void setItems(@Nullable List<Movie> movieItems);

    void showDialog();

    void hideDialog();

    void animateFAB();

    void showSnack(String message);

    void startIntroAnimation();

    void startContentAnimation();

    void navigateToDetail(@NonNull Movie movie);

    void onEventBusHelper(@NonNull EventBusHelper event);
}
