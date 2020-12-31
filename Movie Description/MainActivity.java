//Login page
package com.example.moviedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView signup,valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        valid = findViewById(R.id.valid);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.checkUsername(username.getText().toString(),database) == true)
                {
                    if(dbHelper.checkUsernamePassword(username.getText().toString(),password.getText().toString(),database) == true)
                    {
                        Intent i = new Intent(MainActivity.this,MainActivity3.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"PLEASE ENTER CORRECT USERNAME AND PASSWORD",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"PLEASE ENTER CORRECT USERNAME AND PASSWORD",Toast.LENGTH_LONG).show();
                }
            }
        }
        );

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
}