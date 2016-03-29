package abderrazak.com.recycleviewcardview.views.widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import abderrazak.com.recycleviewcardview.R;

/**
 * Created by abderrazak on 29/03/2016.
 */
public class SnackBarFactory {

    public static Snackbar createCustomSnackbar(Context context, View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction("RETRY", v -> {
                    // do Something here
                });
        ViewGroup group = (ViewGroup) snackbar.getView();
        /** Changing Snackbar background **/
        group.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        /** Changing message text color**/
        snackbar.setActionTextColor(Color.RED);
        /** Changing action button text color**/
        TextView textView = (TextView) group.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        return snackbar;
    }

    public static Snackbar createSimpleSnackbar(Context context, View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);

        return snackbar;
    }

}
