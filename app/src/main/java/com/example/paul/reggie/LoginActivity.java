package com.example.paul.reggie;

import android.content.ContentValues;
import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.example.paul.reggie.model.Users;
import com.example.paul.reggie.model.DataSource;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {//implements LoaderCallbacks<Cursor> {
    private Users mUser;
    private String mUserId;
    private DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public boolean isEmpty(EditText et) {
        if (et.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setLoginView();
    }

    public void setLoginView() {
        mDataSource = new DataSource(this);
        mDataSource.open();


        //Setting views and buttons
        TextView textViewLoginLabel = findViewById(R.id.login_label);
        EditText editTextUserName = findViewById(R.id.username_edittext);

        EditText editTextPin = findViewById(R.id.pin_edittext);
        editTextPin.setHint("Please enter a PIN");

        EditText editTextCPin = findViewById(R.id.confirmpin_edittext);
        editTextCPin.setHint("Please re-enter the PIN");

        Button buttonLogin = findViewById(R.id.login_button);
        //Toast.makeText(this,"Is my table empty ")
        if (mDataSource.isEmpty("users") == true) {
            //change value of login_label to read "Create New User Login Name"
            textViewLoginLabel.setText("Create New User Login");

            //Set edit text box Username to visible and provide hint line
            editTextUserName.setVisibility(View.VISIBLE);
            editTextUserName.setHint("Please enter a user name");

            //Set edit text box Confirm Pin to visible
            editTextCPin.setVisibility(View.VISIBLE);

            //Set Button Text
            buttonLogin.setText("Create User");
        } else {
            //Change value of login_label to read "Enter Pin to Login"
            textViewLoginLabel.setText("Enter Pin to Login");

            //Set edit text box Username to GONE
            editTextUserName.setVisibility(View.GONE);

            //Set edit text box Confirm Pin to GONE
            editTextCPin.setVisibility(View.GONE);

            //Set Button Text
            buttonLogin.setText("Login");
        }
    }

    public boolean onClickLogin(View view) {
        //Setting Views and buttons
        EditText editTextUserName = findViewById(R.id.username_edittext);
        EditText editTextPin = findViewById(R.id.pin_edittext);
        EditText editTextCPin = findViewById(R.id.confirmpin_edittext);

        //Check if form is in Login State or  Create User State
        //Create User State
        //Checks if user name has been populated
        //Checks if Pin and pin confirmation have values and match
        if (mDataSource.isEmpty("users") == true) {
            //check if edit text box user name is empty
            if (isEmpty(editTextUserName)) {
                Toast.makeText(this, "Please enter a user name", Toast.LENGTH_SHORT).show();
                return false;
            }
            //Check if edit text box for pin is empty
            if (isEmpty(editTextPin)) {
                Toast.makeText(this, "Please enter a Pin", Toast.LENGTH_SHORT).show();
                return false;
            }
            //Check if pins match
            //Pulls values from edit box and converts to string
            String PinS = editTextPin.getText().toString();
            String PinCS = editTextCPin.getText().toString();

            //Converts Strings into Integers for Compare
            Integer PinI = Integer.parseInt(PinS);
            Integer PinCI = Integer.parseInt(PinCS);

            //Compares integers to ensure they match
            if (PinI.compareTo(PinCI) != 0) {
                //Pins do not match
                Toast.makeText(this, "The Pins entered do not match", Toast.LENGTH_SHORT).show();
                return false;
            }

            //Converting edit text User name into String
            String UserName = editTextUserName.toString();

            //Objects for table insertion
            Users aUser = new Users(UserName, PinS);
            ContentValues contentValues;
            contentValues = aUser.toUsersValues();

            mDataSource.onInsert(contentValues, "users");

            Toast.makeText(this, "New User Created!", Toast.LENGTH_SHORT).show();
        } else {

            //Check Entered Pin value against Pin value in database
            Users thisUser;
            String password;
            Integer PinEntered, PinDatabase;

            //Gets Pin from activity, converts to string and then to an integer
            //EditText pin = findViewById(R.id.pin_edittext);
            String sPin = editTextPin.getText().toString();
            PinEntered = Integer.parseInt(sPin);

            //Creates User object and pulls user from database
            thisUser = mDataSource.getUser();

            //Retreive password
            password = thisUser.getPassword();
            PinDatabase = Integer.parseInt(password);


            //If Pin entered does not match Pin in database
            if (PinEntered.compareTo(PinDatabase) != 0) {
                Toast.makeText(this, "Incorrect Pin Entered. Try Again.", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        //Moving to Accounts Summary xml file
        Intent intent = new Intent(this, AccountSummaryActivity.class);
        startActivity(intent);

//        mDataSource.close();
        return true;
    }

}