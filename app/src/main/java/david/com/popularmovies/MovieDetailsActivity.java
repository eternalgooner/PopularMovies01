package david.com.popularmovies;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Class that shows the selected movie details
 * - movie details are retrieved as a bundle from the intent
 * - movie details are then retrieved as a HashMap from the bundle
 *
 * UI:
 * - constraint layout is used to layout the view objects
 *
 * STRING LITERALS:
 * - string literals have not been put into the strings.xml file as they are not user-facing
 *
 * ATTRIBUTION:
 * - some code was implemented with help from StackOverflow
 *
 */

public class MovieDetailsActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    private TextView movieTitle;
    private ImageView moviePoster;
    private TextView userRating;
    private TextView releaseDate;
    protected TextView moveSummary;
    private HashMap movieSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "entering onCreate");
        setContentView(R.layout.activity_movie_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.title_movie_details);

        movieTitle = (TextView) findViewById(R.id.txtMovieTitle);
        userRating = (TextView) findViewById(R.id.txtMovieUserRating);
        releaseDate = (TextView) findViewById(R.id.txtMovieReleaseDate);
        moveSummary = (TextView) findViewById(R.id.txtMovieSummary);
        moviePoster = (ImageView) findViewById(R.id.imgMoviePoster);

        Bundle bundle = this.getIntent().getExtras();
        movieSelected = (HashMap) bundle.getSerializable(getString(R.string.key_selectedMovie));

        displayMovieDetails(movieSelected);
        Log.d(TAG, "exiting onCreate");
    }

    private void displayMovieDetails(HashMap movie) {
        Log.d(TAG, "entering displayMovieDetails");
        StringBuilder movieYear = new StringBuilder((String) movie.get(getString(R.string.key_releaseDate)));
        String year = movieYear.substring(0,4);
        String posterPrefix = getString(R.string.url_poster_prefix);
        movieTitle.setText((String)movie.get(getString(R.string.key_title)));
        moveSummary.setText((String)movie.get(getString(R.string.key_overview)));
        userRating.setText((String)movie.get(getString(R.string.key_voteAverage)) + getString(R.string.user_rating_out_of_ten));
        releaseDate.setText(year);
        Picasso.with(getApplicationContext()).load(posterPrefix + (String) movie.get(getString(R.string.key_posterPath))).into(moviePoster);
        Log.d(TAG, "poster path is: " + movie.get(getString(R.string.key_posterPath)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
