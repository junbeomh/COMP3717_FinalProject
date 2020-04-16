package ca.bcit.android.finalproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewMovieActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription, editTextUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Add Movie");
        editTextTitle = (EditText) findViewById(R.id.edit_text_title);
        editTextDescription = (EditText) findViewById(R.id.edit_text_description);
        editTextUrl = (EditText) findViewById(R.id.edit_text_url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_new_movie, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveMovie() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String url = editTextUrl.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty() || url.trim().isEmpty()) {
            Toast.makeText(this, "Enter name, description, and url of movie.", Toast.LENGTH_LONG).show();
            return;
        }

        CollectionReference movieRef = FirebaseFirestore.getInstance().collection("Movies");
        movieRef.add(new Movie(title, description, url));
        Toast.makeText(this, "Movie added successfully.", Toast.LENGTH_LONG).show();
        finish();
    }
}
