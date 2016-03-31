package abderrazak.com.recycleviewcardview.ui.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import abderrazak.com.recycleviewcardview.data.DataManager;
import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.ui.Presenter;
import abderrazak.com.recycleviewcardview.util.CustomComparator;
import abderrazak.com.recycleviewcardview.util.NetworkUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by abderrazak on 23/03/2016.
 */
public class MainPresenter implements Presenter<MainMvpView> {

    private MainMvpView mainMvpView;
    private Subscription subscription;
    private DataManager mDataManager;
    private List<Movie> movieArrayList = null;


    @Override
    public void atachView(MainMvpView view) {
        this.mainMvpView = view;
        this.mDataManager = new DataManager();
    }

    @Override
    public void detachView() {
        this.mainMvpView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void sortList() {
        if (movieArrayList != null) {
            Collections.sort(movieArrayList,new CustomComparator());
            mainMvpView.setItems(movieArrayList);
        }
    }

    public void initiateViews() { mainMvpView.initializeViews();}

    public void startContentAnimation() { mainMvpView.startContentAnimation(); }

    public void startIntroAnimation() { mainMvpView.startIntroAnimation(); }

    public void showSnackbar(String message) {
        mainMvpView.showSnack(message);
    }

    /** Filter without RxJava **/
    public List<Movie> filter(String query) {
        query = query.toLowerCase();

        final List<Movie> filteredModelList = new ArrayList<>();
        for (Movie model : movieArrayList) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public List<Movie> filterWithRx(String query){

        final List<Movie> filteredModelList = new ArrayList<>();

        Observable.from(movieArrayList)
                  .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()))
                  .subscribe(new Subscriber<Movie>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Movie movie) {
                            filteredModelList.add(movie);
                        }
                    });

        return filteredModelList;
    }
    public void onItemClick(Movie movie) { mainMvpView.navigateToDetail(movie); }

    public void animateFAB() {
        mainMvpView.animateFAB();
    }

    public void loadMovies() {
        mainMvpView.showDialog();
        if (subscription != null) subscription.unsubscribe();
        subscription = mDataManager.syncData()
                                   .subscribe(new Subscriber<List<Movie>>() {
                                        @Override
                                        public void onCompleted() {
                                            if (!movieArrayList.isEmpty()) mainMvpView.setItems(movieArrayList);
                                            else mainMvpView.showSnack("Oops! Empty list movies");
                                            mainMvpView.hideDialog();
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            mainMvpView.hideDialog();
                                            if (NetworkUtil.isHttpStatusCode(e, 404)) mainMvpView.showSnack("Oops! error 404");
                                            else mainMvpView.showSnack("Oops! something went wrong");
                                        }

                                        @Override
                                        public void onNext(List<Movie> movies) {
                                            MainPresenter.this.movieArrayList = movies;
                                        }
                                    });
        /** USE SyncService **/
        /*subscription = mDataManager.getMoviesFromDB()
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribeOn(Schedulers.io())
                                   .subscribe(new Subscriber<List<Movie>>() {
                                        @Override
                                        public void onCompleted() {
                                            if (!movieArrayList.isEmpty()) mainMvpView.setItems(movieArrayList);
                                            else mainMvpView.showSnack("Oops! Empty list movies");
                                            mainMvpView.hideDialog();
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            mainMvpView.hideDialog();
                                            if (NetworkUtil.isHttpStatusCode(e, 404)) mainMvpView.showSnack("Oops! error 404");
                                            else mainMvpView.showSnack("Oops! something went wrong");
                                        }

                                        @Override
                                        public void onNext(List<Movie> movies) {
                                            MainPresenter.this.movieArrayList = movies;
                                        }
                                   });*/
    }

}
