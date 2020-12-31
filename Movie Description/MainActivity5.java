//Trailer and description page
package com.example.moviedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;

public class MainActivity5 extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView trailer;
    TextView movieName,description,ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        trailer = (YouTubePlayerView) findViewById(R.id.trailer);
        trailer.initialize(Config.YOUTUBE_API_KEY,this);

        movieName = findViewById(R.id.movie_name);
        description = findViewById(R.id.description);
        ratings = findViewById(R.id.ratings);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored)
        {
            Intent intent = getIntent();
            String movie_name = intent.getStringExtra("movie_name");
            movieName.setText(movie_name);

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            description.setText("Description:"+dbHelper.getMovieDescription(movie_name,database));
            ratings.setText("Ratings:"+Double.toString(dbHelper.getMovieRatings(movie_name,database)));

            String trailer_link = dbHelper.getTrailerLink(movie_name,database);

            youTubePlayer.loadVideo(trailer_link);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if(errorReason.isUserRecoverableError())
        {
            errorReason.getErrorDialog(this,RECOVERY_REQUEST).show();
        }
        else
        {
            String error = String.format(getString(R.string.player_error),errorReason.toString());
            Toast.makeText(this,error,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return trailer;
    }
}