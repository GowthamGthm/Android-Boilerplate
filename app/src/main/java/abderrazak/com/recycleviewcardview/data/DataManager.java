package abderrazak.com.recycleviewcardview.data;

import java.util.List;

import abderrazak.com.recycleviewcardview.BaseApplication;
import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.data.remote.RestAPI;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by abderrazak on 28/03/2016.
 *
 *  This Class Is the middle man between Back-end & front-end
 *  you have just instanciate within presenter and subscribe
 *  to it to get data from server
 */
public class DataManager {

    private final RestAPI mRestAPI;


    public DataManager() {
        mRestAPI = BaseApplication.getInstance().getRestInterface();
    }

    /***  Sync Data : just Subscribe to it to get Data ***/
    public Observable<List<Movie>> syncData() {
        Timber.i("Starting sync...");
        Observable<List<Movie>> observable = mRestAPI.loadMovies()
                                                      // all the computation will happen in a background thread
                                                     .observeOn(AndroidSchedulers.mainThread())
                                                      // the result subscription will happen in the UI Thread
                                                     .subscribeOn(Schedulers.io());
        return observable;
    }

    public Observable<List<Movie>> syncDataToCache() {
        return this.syncData().cache();
    }
}
