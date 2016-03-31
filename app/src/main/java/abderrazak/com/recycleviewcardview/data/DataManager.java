package abderrazak.com.recycleviewcardview.data;

import java.util.List;

import abderrazak.com.recycleviewcardview.BaseApplication;
import abderrazak.com.recycleviewcardview.data.local.DataBaseHelper;
import abderrazak.com.recycleviewcardview.data.local.PreferencesHelper;
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
 *  to it to get data from Server or Database
 */
public class DataManager {

    private final RestAPI mRestAPI;
    private  DataBaseHelper mDataBaseHelper;
    private  PreferencesHelper mPreferencesHelper;


    public DataManager() {
        mRestAPI = BaseApplication.getInstance().getRestInterface();
    }

    public DataManager(DataBaseHelper dataBaseHelper, PreferencesHelper preferencesHelper) {
        mRestAPI = BaseApplication.getInstance().getRestInterface();
        mDataBaseHelper = dataBaseHelper;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper(){ return mPreferencesHelper;}

    /***  Sync Data : just Subscribe to it to get Data ***/
    public Observable<List<Movie>> syncData() {
        Timber.i("Starting sync from server ...");
        Observable<List<Movie>> observable = mRestAPI.loadMovies()
                                                      // all the computation will happen in a background thread
                                                     .observeOn(AndroidSchedulers.mainThread())
                                                      // the result subscription will happen in the UI Thread
                                                     .subscribeOn(Schedulers.io());
        return observable;
    }

    /** Sync Data from Server and put them to local Database **/
    public Observable<Movie> fetchDataToDb() { return mRestAPI.loadMovies().concatMap(movies -> mDataBaseHelper.setMovies(movies)); }

    /** Fetch Data from DB **/
    public Observable<List<Movie>> getMoviesFromDB () { return mDataBaseHelper.getMovies().distinct(); }
}
