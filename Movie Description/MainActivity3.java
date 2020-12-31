//Genre selection page
package com.example.moviedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView ask_genre;
    Button scifi,animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ask_genre = findViewById(R.id.genre);
        scifi = findViewById(R.id.scifi);
        animation = findViewById(R.id.animation);

        scifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                intent.putExtra("Genre","Sci-Fi");
                startActivity(intent);
            }
        });

        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                intent.putExtra("Genre","Animation");
                startActivity(intent);
            }
        });
    }
}