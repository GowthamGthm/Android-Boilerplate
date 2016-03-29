package abderrazak.com.recycleviewcardview.util;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by abderrazak on 18/03/2016.
 */
public class ViewHelper {

    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1.0F);
        ViewCompat.setScaleY(v, 1.0F);
        ViewCompat.setScaleX(v, 1.0F);
        ViewCompat.setTranslationY(v, 0.0F);
        ViewCompat.setTranslationX(v, 0.0F);
        ViewCompat.setRotation(v, 0.0F);
        ViewCompat.setRotationY(v, 0.0F);
        ViewCompat.setRotationX(v, 0.0F);
        v.setPivotY((float)(v.getMeasuredHeight() / 2));
        ViewCompat.setPivotX(v, (float)(v.getMeasuredWidth() / 2));
        ViewCompat.animate(v).setInterpolator((Interpolator)null);
    }
}
