package abderrazak.com.recycleviewcardview.views.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import abderrazak.com.recycleviewcardview.R;
import abderrazak.com.recycleviewcardview.data.model.Movie;
import abderrazak.com.recycleviewcardview.util.ArraysUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by abderrazak on 07/03/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> implements View.OnClickListener{

    private List<Movie> movies;
    private final LayoutInflater mInflater;
    private OnRVItemClickListener<Movie> itemClickListener;
    private Context context;


    public RVAdapter(Context context, List<Movie> movies) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.movies = movies;

    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        v.setOnClickListener(this);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        final Movie item = movies.get(position);
        holder.itemView.setTag(item);
        holder.bind(item);

        Picasso.with(context).load(movies.get(position).getThumbnailUrl()).into(holder.thumbNail);
        //Glide.with(context).load(movies.get(position).getThumbnailUrl()).asBitmap().into(holder.thumbNail);

        Timber.i("Movie -> "+ item.toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public void animateTo(List<Movie> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Movie> newModels) {
        for (int i = movies.size() - 1; i >= 0; i--) {
            final Movie model = movies.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Movie> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Movie model = newModels.get(i);
            if (!movies.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Movie> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Movie model = newModels.get(toPosition);
            final int fromPosition = movies.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public void moveItem(int fromPosition, int toPosition){
        final Movie movieItem = movies.remove(fromPosition);
        movies.add(toPosition, movieItem);
        notifyItemMoved(fromPosition, toPosition);
    }
    public Movie removeItem(int position){
        final Movie movieItem = movies.remove(position);
        notifyItemRemoved(position);
        return movieItem;
    }
    public void addItem(int position, Movie movieAdd){
        movies.add(position, movieAdd);
        notifyItemInserted(position);
    }

    @Override
    public void onClick(View v) {
       if (itemClickListener != null) {
           Movie movie = (Movie) v.getTag();
           itemClickListener.onItemClick(v, movie);
       }
    }

    public void setOnItemClickListener(OnRVItemClickListener<Movie> listener){
        this.itemClickListener = listener;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title) TextView title;
        @Bind(R.id.rating) TextView rating;
        @Bind(R.id.genre) TextView genre;
        @Bind(R.id.releaseYear) TextView releaseYear;
        @Bind(R.id.thumbnail) ImageView thumbNail;

        PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Movie movie) {
            title.setText(movie.getTitle());
            rating.setText("Rating: " + String.valueOf(movie.getRating()));
            if (movie.getGenre() != null) {
                genre.setText("Genre: "+ ArraysUtil.convertArrayListToString(movie.getGenre()));
            }
            releaseYear.setText(String.valueOf(movie.getYear()));
        }

    }

    public interface OnRVItemClickListener <Model>{

        void onItemClick(View view, Model movie);

    }
}
