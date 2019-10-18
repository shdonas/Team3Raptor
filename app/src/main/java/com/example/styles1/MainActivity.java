package com.example.styles1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_missionstatus, R.id.nav_gps,
                R.id.nav_data)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        /**
        *SQLite
        *hard coded
        *WIP , separate class needs to be called
        * @author Shakhawat Hossain
         **/

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
