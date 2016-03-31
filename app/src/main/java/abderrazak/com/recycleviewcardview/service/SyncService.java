package abderrazak.com.recycleviewcardview.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import abderrazak.com.recycleviewcardview.data.DataManager;
import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.util.AndroidComponentUtil;
import abderrazak.com.recycleviewcardview.util.NetworkUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by abderrazak on 31/03/2016.
 */
public class SyncService extends Service {

    DataManager mDataManager;
    private Subscription mSubscription;

    public static Intent getStartIntent(Context context) { return new Intent(context, SyncService.class); }

    public static boolean isRunning(Context context) { return AndroidComponentUtil.isServiceRunning(context, SyncService.class); }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.i("Starting sync to database...");

        if (!NetworkUtil.isNetworkConnected(this)) {
            Timber.i("Sync canceled, connection not available");
            AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }

        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
        mSubscription = mDataManager.fetchDataToDb()
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(new Subscriber<Movie>() {
                                        @Override
                                        public void onCompleted() {
                                            Timber.i("Sync Successfully!");
                                            stopSelf(startId);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Timber.w(e, "Oops! Sync failed.");
                                        }

                                        @Override
                                        public void onNext(Movie movie) {

                                        }
                                    });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null) mSubscription.unsubscribe();
        super.onDestroy();
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)
                    && NetworkUtil.isNetworkConnected(context)) {
                Timber.i("Connection is now available, triggering sync...");
                AndroidComponentUtil.toggleComponent(context, this.getClass(), false);
                context.startService(getStartIntent(context));
            }
        }
    }
}
