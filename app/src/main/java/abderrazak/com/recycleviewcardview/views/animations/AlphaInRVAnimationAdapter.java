package abderrazak.com.recycleviewcardview.views.animations;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abderrazak on 18/03/2016.
 */
public class AlphaInRVAnimationAdapter extends RVAnimationAdapter{

    private static final float DEFAULT_ALPHA_FROM = 0.0F;
    private final float mFrom;
    private RecyclerView recyclerView;

    public AlphaInRVAnimationAdapter(RecyclerView.Adapter adapter) {
        this(adapter, 0.0F);
    }

    public AlphaInRVAnimationAdapter(RecyclerView.Adapter adapter, float from) {
        super(adapter);
        this.mFrom = from;
    }

    @Override
    protected Animator[] getAnimators(View view) {
        view.setAlpha(0);
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", new float[]{this.mFrom, 1.0F})};
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
