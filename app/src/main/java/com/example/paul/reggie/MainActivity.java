package com.example.paul.reggie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
//Adding a change for git test
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Check if a userId exists in database.
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        //If id does not exist--move to create login screen

        //If id exists -- move to login screen

        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


    }


}
