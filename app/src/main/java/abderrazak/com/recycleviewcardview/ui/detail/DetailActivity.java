package abderrazak.com.recycleviewcardview.ui.detail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import abderrazak.com.recycleviewcardview.BaseApplication;
import abderrazak.com.recycleviewcardview.R;
import abderrazak.com.recycleviewcardview.util.EventBusHelper;
import abderrazak.com.recycleviewcardview.util.UiUtils;
import abderrazak.com.recycleviewcardview.views.widgets.HeaderView;
import abderrazak.com.recycleviewcardview.views.widgets.SnackBarFactory;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailMvpView, AppBarLayout.OnOffsetChangedListener {

    public static final String EXTRA_IMAGE = "abderrazak.com.recycleviewcardview.extraImage";
    public static final String EXTRA_TITLE = "abderrazak.com.recycleviewcardview.extraTitle";
    public static final String EXTRA_GENRE = "abderrazak.com.recycleviewcardview.extraGenre";

    @Bind(R.id.content)
    View content;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.title)
    TextView paraTitle;
    @Bind(R.id.thumbnail)
    ImageView mThumbnail;
    @Bind(R.id.fab)
    FloatingActionButton mFloatActionButton;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appBarLayout)
    AppBarLayout mAppbar;
    @Bind(R.id.toolbar_header_view)
    protected HeaderView toolbarHeaderView;
    @Bind(R.id.float_header_view)
    protected HeaderView floatHeaderView;
    private DetailPresenter presenter;
    private boolean isHideToolbarView = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailPresenter();
        presenter.atachView(this);
        presenter.initTransition();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter.initiateViews();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Register ourselves so that we can provide the initial value.
        BaseApplication.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Always unregister when an object no longer should be on the bus.
        BaseApplication.getBus().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:
                presenter.createISharedIntent();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void initActivityTransitions() {
        if (UiUtils.isAndroid5()) {
            Slide transition = new Slide();
            transition.setDuration(1000);
            //transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);

            Fade fade = new Fade();
            fade.setDuration(2000);
            getWindow().setReturnTransition(fade);
            getWindow().setReenterTransition(fade);
        }
    }

    @Override
    public void createShareIntent() {
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType("text/plain")
                    .setText("whatever text you want to send!")
                    .startChooser();
    }

    @Override
    public void showSnack(String message) {
        SnackBarFactory.createSimpleSnackbar(getApplicationContext(), content, message).show();
    }

    @Override
    public void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
    }

    @Override
    public void initializeViews() {
        ViewCompat.setTransitionName(mAppbar, EXTRA_IMAGE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        String extraGenre = getIntent().getStringExtra(EXTRA_GENRE);
        paraTitle.setText(extraGenre);

        mAppbar.addOnOffsetChangedListener(this);

        toolbarHeaderView.bindTo(itemTitle);
        floatHeaderView.bindTo(itemTitle);

        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        /* TODO: uncomment it if you wanna work with Glide */
        /*
        Glide.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(mImageview);
        new Thread(() -> {
            try {
                Bitmap bitmap = Glide.with(DetailActivity.this).load(getIntent().getStringExtra(EXTRA_IMAGE)).asBitmap().into(100,100).get();
                Palette.from(bitmap).generate(palette -> {
                    applyPalette(palette);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
        */

        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(mThumbnail, new Callback() {
            @Override
            public void onSuccess() {
                presenter.scheduleStartPostponedTransition(mThumbnail);
                Bitmap bitmap = ((BitmapDrawable) mThumbnail.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(palette -> {
                    applyPalette(palette);
                });
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void applyPalette(Palette palette) {
        int primaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);
        int primary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));

        /* TODO: uncomment it if you wanna work with Glide */
        /*collapsingToolbarLayout.post(() -> {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        });*/

        updateBackground(mFloatActionButton, palette);
        supportStartPostponedEnterTransition();
    }

    @Subscribe
    @Override
    public void onEventBusHelper(@NonNull EventBusHelper event) {
        presenter.showSnackbar(event.message);
    }

    @Override
    public void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
        int vibrantColor = palette.getVibrantColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
