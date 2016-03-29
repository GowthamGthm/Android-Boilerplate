package abderrazak.com.recycleviewcardview.ui.detail;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.view.View;

import abderrazak.com.recycleviewcardview.ui.MvpView;
import abderrazak.com.recycleviewcardview.util.EventBusHelper;

/**
 * Created by abderrazak on 23/03/2016.
 */
public interface DetailMvpView extends MvpView {

    void initializeViews();

    void initActivityTransitions();

    void createShareIntent();

    void showSnack(String message);

    void scheduleStartPostponedTransition(@NonNull final View sharedElement);

    void applyPalette(Palette palette);

    void onEventBusHelper(@NonNull EventBusHelper event);

    void updateBackground(@NonNull FloatingActionButton fab, Palette palette);
}
