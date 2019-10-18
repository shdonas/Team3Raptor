package com.example.styles1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

/**
 * hard Coded
 * author Shakhawat Hossain
 */
public class SQLite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SQLiteDatabase db = null;
    try{

        // Creates MyDB Database
        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);

        db.execSQL("DROP TABLE IF EXISTS MyTable");

        //Creating Table MyTable, Column name LastName, FirstName, Age
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable(LastName VARCHAR, FirstName VARCHAR, Age INT(3));");
        // Adding Values
        db.execSQL("INSERT INTO MyTable VALUES ('Hossain', 'Shakhawat', 30);");

        Cursor C = db.rawQuery("Select * From MyTable", null);
        C.moveToFirst();

        System.out.println(C.getString(C.getColumnIndex("FirstName")));
        System.out.println(C.getString(C.getColumnIndex("LastName")));
        System.out.println(C.getString(C.getColumnIndex("Age")));

        Log.d("Shakhawat", C.getString(C.getColumnIndex("FirstName")));

    } catch (Exception e){
        Log.d("Error", e.toString());
    }finally {
        db.close();
    }
}
}
