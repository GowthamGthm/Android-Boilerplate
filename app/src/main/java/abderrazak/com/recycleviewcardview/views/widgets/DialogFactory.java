package abderrazak.com.recycleviewcardview.views.widgets;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by abderrazak on 29/03/2016.
 */
public class DialogFactory {


    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        return pDialog;
    }
}
