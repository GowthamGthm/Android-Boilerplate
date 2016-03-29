package abderrazak.com.recycleviewcardview.ui.detail;

import android.view.View;

import abderrazak.com.recycleviewcardview.ui.Presenter;

/**
 * Created by abderrazak on 23/03/2016.
 */
public class DetailPresenter implements Presenter<DetailMvpView> {

    private DetailMvpView detailMvpView;

    @Override
    public void atachView(DetailMvpView view) { this.detailMvpView = view; }

    @Override
    public void detachView() { this.detailMvpView = null; }

    public void initiateViews() { detailMvpView.initializeViews(); }

    public void initTransition() { detailMvpView.initActivityTransitions(); }

    public void createISharedIntent() { detailMvpView.createShareIntent(); }

    public void showSnackbar(String message) { detailMvpView.showSnack(message); }

    public void scheduleStartPostponedTransition(View sharedElement) { detailMvpView.scheduleStartPostponedTransition(sharedElement); }

}
