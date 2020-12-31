//Movie list page
package com.example.moviedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ListView movie_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        movie_list = findViewById(R.id.movie_list);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        String genre = intent.getStringExtra("Genre");

        ArrayList<String> movies = new ArrayList<String>();
        movies = dbHelper.getMovieNames(genre,database);

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,movies);
        movie_list.setAdapter(arrayAdapter);

        ArrayList<String> finalMovies = movies;
        movie_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity4.this,MainActivity5.class);
                i.putExtra("movie_name",finalMovies.get(position));
                startActivity(i);
            }
        });

    }


}