package com.example.moviedescription;
//This java class will help to do task of database.
//All methods related to database, creation of database and tables are written in this class.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String database_name = "movie_description";
    private static final int version = 1;

    public DBHelper(Context context)
    {
        super(context,database_name,null,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //This method is called whenever app is installed.
        //Creating USER table.
        db.execSQL("create table USER(_id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Username TEXT,Password TEXT)");

        //Creating MOVIES table and giving its initial values.
        db.execSQL("create table MOVIES(_id INTEGER PRIMARY KEY AUTOINCREMENT,Movie_name TEXT,Movie_genre TEXT,Movie_Description TEXT,Ratings REAL,Trailer_link TEXT)");
        insertData("What Happened to Monday","Sci-Fi","In a world where families are allowed only one child due to overpopulation, resourceful identical septuplets.",6.9,"hOiWSWLt-NA",db);
        insertData("Bird Box","Sci-Fi","When a mysterious force decimates the population, only one thing is certain  takes the form of their worst fears.",6.6,"o2AsIXSh2xo",db);
        insertData("Inside Out","Animation","Eleven-year-old Riley moves to San Francisco, leaving behind her life in Minnesota.",8.1,"WIDYqBMFzfg",db);
        insertData("Minions","Animation","Minions Kevin, Stuart and Bob decide to find a new master.",6.4,"eisKxhjBnZ0",db);
        insertData("Boss Baby","Animation","The Boss Baby is a hilariously universal story about how a new baby's arrival impacts a family",6.3,"k397HRbTtWI",db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean insertData(String name,String username,String password,SQLiteDatabase db)
    {
        //For inserting data in USER table while signup.
        // It will return false if data is not inserted correctly.
        ContentValues values = new ContentValues();
        values.put("Name",name);
        values.put("Username",username);
        values.put("Password",password);
        long result = db.insert("USER",null,values);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean checkUsername(String username,SQLiteDatabase db)
    {
        //For checking username is present in the database.
        Cursor cursor = db.rawQuery("select * from USER where Username = ?",new String[]{username});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkUsernamePassword(String username,String password,SQLiteDatabase db)
    {
        //For checking password is correct for corresponding username.
        Cursor cursor = db.rawQuery("select * from USER where Username = ? and Password = ?",new String[]{username,password});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void insertData(String movie_name,String movie_genre,String movie_description,double ratings,String trailer_link,SQLiteDatabase db)
    {
        //For inserting data in MOVIES table in onCreate() method (Method Overloading).
        ContentValues values = new ContentValues();
        values.put("Movie_name",movie_name);
        values.put("Movie_genre",movie_genre);
        values.put("Movie_description",movie_description);
        values.put("Ratings",ratings);
        values.put("Trailer_link",trailer_link);
        db.insert("MOVIES",null,values);
    }

    public ArrayList<String> getMovieNames(String genre, SQLiteDatabase db)
    {
        ArrayList<String> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select Movie_name from MOVIES where Movie_genre = ?",new String[]{genre});
        if (cursor != null)
        {
            cursor.moveToFirst();
            //Cursor will come to column value from column name.

        }
        do{
            String movie_name = cursor.getString(0);
            arrayList.add(movie_name);
        }while(cursor.moveToNext());//Until last row.

        return arrayList;
    }

    public String getMovieDescription(String movie_name,SQLiteDatabase db)
    {
        Cursor cursor = db.rawQuery("select Movie_description from MOVIES where Movie_name = ?",new String[]{movie_name});
        if (cursor != null)
        {
            cursor.moveToFirst();
            //Cursor will come to column value from column name.
        }
        return cursor.getString(0);
    }

    public double getMovieRatings(String movie_name,SQLiteDatabase db)
    {
        Cursor cursor = db.rawQuery("select Ratings from MOVIES where Movie_name = ?",new String[]{movie_name});
        if (cursor != null)
        {
            cursor.moveToFirst();
            //Cursor will come to column value from column name.
        }
        return cursor.getDouble(0);
    }

    public String getTrailerLink(String movie_name,SQLiteDatabase db)
    {
        Cursor cursor = db.rawQuery("select Trailer_link from MOVIES where Movie_name = ?",new String[]{movie_name});
        if (cursor != null)
        {
            cursor.moveToFirst();
            //Cursor will come to column value from column name.
        }
        return cursor.getString(0);
    }
}
