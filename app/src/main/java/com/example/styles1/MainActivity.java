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
         * calling the SQLite db to store data
         * @author SH
         */
        System.out.println(SQLiteDB(10.123456, 20.123456, 30.123456));

    }


    /**
     * This SQLite method will take input and store it into a Team3Raptor
     * table name MyTable
     * input data type double
     * returns string
     *
     * @return column_1 string data
     *
     * @param input1
     * @param input2
     * @param input3
     *
     * @author Shakhawat Hossain
     */
    public String SQLiteDB(double input1, double input2, double input3) {
        SQLiteDatabase db;
        db = openOrCreateDatabase("Team3Raptor", MODE_PRIVATE, null);
        try{
            db.execSQL("Drop Table If Exists MyTable");
            Log.d("Info", "Table Deleted");

            db.execSQL("Create Table If Not Exists MyTable(Column_1 INT(10), Column_2 INT(10), Column_3 INT(10));" );
            Log.d("Info", "Table Created");

            db.execSQL("INSERT INTO MyTable VALUES ("+ input1 + "," + input2 + "," + input3 + ");");

            Log.d("Info", "Data inserted");
        } catch (Exception e){
            Log.e("Error", e.toString());
        }

        Cursor C = db.rawQuery("Select * From MyTable", null);
        C.moveToFirst();

//        System.out.println(C.getString(C.getColumnIndex("Column_1")));
//        System.out.println(C.getString(C.getColumnIndex("Column_2")));
//        System.out.println(C.getString(C.getColumnIndex("Column_3")));

        Log.d("Column 1 data", C.getString(C.getColumnIndex("Column_1")));
        return C.getString(C.getColumnIndex("Column_1"));
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
