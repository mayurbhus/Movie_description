//Signup page
package com.example.moviedescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText name,user_name,password;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = findViewById(R.id.name);
        user_name = findViewById(R.id.user_name);
        password = findViewById(R.id.password_for_signup);
        signup = findViewById(R.id.actual_signup);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean isInserted = dbHelper.insertData(name.getText().toString(),
                            user_name.getText().toString(), password.getText().toString(), database);
                    if (isInserted == true)
                    {
                        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                        Toast.makeText(MainActivity2.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity2.this, "Insert every field carefully", Toast.LENGTH_LONG).show();
                    }

            }
        });
    }
}