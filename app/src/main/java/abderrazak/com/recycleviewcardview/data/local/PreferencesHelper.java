package abderrazak.com.recycleviewcardview.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abderrazak on 31/03/2016.
 */
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";

    private final SharedPreferences mPref;

    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear(){ mPref.edit().clear().apply();}
}
