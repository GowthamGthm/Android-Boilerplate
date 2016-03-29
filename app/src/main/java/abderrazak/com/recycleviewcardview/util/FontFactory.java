package abderrazak.com.recycleviewcardview.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by abderrazak on 16/03/2016.
 */
public class FontFactory {

    final String ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";
    final String ROBOTO_LIGHT = "fonts/Roboto-Light.ttf";
    final String ROBOTO_BOLD_ITALIC = "fonts/Roboto-BoldItalic.ttf";
    final String ROBOTO_THIN_ITALIC = "fonts/Roboto-ThinItalic.ttf";
    final String ROBOTO_ITALIC = "fonts/Roboto-Italic.ttf";

    Typeface regular;
    Typeface light;
    Typeface boldItalic;
    Typeface italic;
    Typeface thinItalic;

    public FontFactory(Context context) {

        regular = Typeface.createFromAsset(context.getAssets(), ROBOTO_REGULAR);
        light = Typeface.createFromAsset(context.getAssets(), ROBOTO_LIGHT);
        italic = Typeface.createFromAsset(context.getAssets(), ROBOTO_ITALIC);
        boldItalic = Typeface.createFromAsset(context.getAssets(), ROBOTO_BOLD_ITALIC);
        thinItalic = Typeface.createFromAsset(context.getAssets(), ROBOTO_THIN_ITALIC);
    }

    public Typeface getRegular() { return regular; }

    public Typeface getLight() {
        return light;
    }

    public Typeface getItalic() {
        return italic;
    }

    public Typeface getBoldItalic() {
        return boldItalic;
    }

    public Typeface getThinItalic() {
        return thinItalic;
    }


}
