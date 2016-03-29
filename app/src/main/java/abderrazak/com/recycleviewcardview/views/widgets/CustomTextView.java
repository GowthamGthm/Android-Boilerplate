package abderrazak.com.recycleviewcardview.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import abderrazak.com.recycleviewcardview.BaseApplication;
import abderrazak.com.recycleviewcardview.R;

/**
 * Created by abderrazak on 16/03/2016.
 */
public class CustomTextView extends TextView {


    private int typefaceType;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView,0, 0);

        try {
            typefaceType = array.getInteger(R.styleable.CustomTextView_font_name, 0);
        } finally {
            array.recycle();
        }

        if (!isInEditMode())
            setTypeface(BaseApplication.getInstance().getTypeFace(typefaceType));
    }

    public CustomTextView(Context context) {
        super(context);
    }
}
