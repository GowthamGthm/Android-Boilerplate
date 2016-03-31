package abderrazak.com.recycleviewcardview.ui;

/**
 * Created by abderrazak on 23/03/2016.
 *
 * Every presenter in the app must either implement this interface
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V> {

    void atachView(V view);

    void detachView();
}
