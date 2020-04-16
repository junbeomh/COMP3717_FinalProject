package ca.bcit.android.finalproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MovieAdapter extends FirestoreRecyclerAdapter<Movie, MovieAdapter.MovieHolder> {

    private static final String LOGTAG = "Movie_Adapter";

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MovieAdapter(@NonNull FirestoreRecyclerOptions<Movie> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieHolder holder, int position, @NonNull Movie model) {
        holder.mTitle.setText(model.getName());
        holder.mDescription.setText(model.getDescription());
        holder.mUrl.setText(model.getUrl());

        Log.d(LOGTAG, "Name: " + model.getName());
        Log.d(LOGTAG, "Description: " + model.getDescription());
        Log.d(LOGTAG, "Url: " + model.getUrl());

    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(v);
    }

    class MovieHolder extends RecyclerView.ViewHolder{

        TextView mTitle, mDescription, mUrl;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.movie_name);
            mDescription = itemView.findViewById(R.id.description);
            mUrl = itemView.findViewById(R.id.movie_url);

        }
    }
}
