package ca.bcit.android.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SEARCH_FRAGMENT";
    private ExpandableRelativeLayout filterOptionsLayout;
    private EditText searchText;
    private ListView locationsListView;
    private RecyclerView mMainList;
    private FirebaseFirestore db;
    private CollectionReference movieRef;
    private MovieAdapter adapter;
    private List<Movie> movielist = new ArrayList<>();
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        movielist = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        movieRef = db.collection("Movies");

        FloatingActionButton buttonAddMovie = rootView.findViewById(R.id.add_movie);
        buttonAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewMovieActivity.class));
            }
        });


        setUpRecyclerView();
        return rootView;
    }

    private void setUpRecyclerView(){
        Query query = movieRef;
        FirestoreRecyclerOptions<Movie> options = new FirestoreRecyclerOptions.Builder<Movie>()
                .setQuery(query, Movie.class)
                .build();
        adapter = new MovieAdapter(options);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}