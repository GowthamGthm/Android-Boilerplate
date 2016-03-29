package abderrazak.com.recycleviewcardview.data.remote;

import java.util.List;

import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.util.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by abderrazak on 07/03/2016.
 */
public interface RestAPI {


    @GET(BuildConfig.PATH_TO_MOVIES_SERVICE)
    Observable<List<Movie>> loadMovies();

    /**** Helper class that sets up a new services ****/

    class Factory {
        public static RestAPI create() {
            Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(BuildConfig.API_BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                            .build();
            return retrofit.create(RestAPI.class);
        }
    }
}
