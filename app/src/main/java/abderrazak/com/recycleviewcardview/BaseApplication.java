package abderrazak.com.recycleviewcardview;

import android.app.Application;
import android.graphics.Typeface;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.squareup.otto.Bus;

import abderrazak.com.recycleviewcardview.data.remote.RestAPI;
import abderrazak.com.recycleviewcardview.util.FontFactory;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by abderrazak on 15/03/2016.
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance;
    private RestAPI restInterface;
    private FontFactory mFontFactory;
    private static Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        bus = new Bus();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        CrashlyticsCore core = new CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static synchronized BaseApplication getInstance() { return  mInstance;}

    public static Bus getBus(){ return bus; }

    public RestAPI getRestInterface() {
        if (restInterface == null) {
            restInterface = RestAPI.Factory.create();
        }
        return restInterface;
    }


    /** A tree which logs important information for crash reporting. */
    /**private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }**/

    public Typeface getTypeFace(int font){
        if (mFontFactory == null)
            mFontFactory = new FontFactory(this);

        switch (font){
            case  Constants.REGULAR:
                mFontFactory.getRegular();
            case Constants.BOLD_ITALIC:
                mFontFactory.getBoldItalic();
            case Constants.LIGHT:
                return mFontFactory.getLight();
            case Constants.THINITALIC:
                return mFontFactory.getThinItalic();
            case Constants.ITALIC:
                return mFontFactory.getItalic();
            default: return mFontFactory.getRegular();
        }
    }



    public interface Constants {

        int REGULAR = 1;
        int BOLD_ITALIC = 2;
        int LIGHT = 3;
        int THINITALIC = 4;
        int ITALIC = 5;
    }
}
