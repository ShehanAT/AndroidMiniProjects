package com.coding.informer.androidloginregistrationexample.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.coding.informer.androidloginregistrationexample.R;
import com.coding.informer.androidloginregistrationexample.database.AppDatabase;
import com.coding.informer.androidloginregistrationexample.helpers.DatabaseHelper;
import com.coding.informer.androidloginregistrationexample.models.User;

import org.mindrot.jbcrypt.BCrypt;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private DatabaseHelper openHelper;
    private Button registerBtn;
    private Button loginBtn;
    private EditText regUsername;
    private EditText regEmail;
    private EditText regPassword;
    private EditText regConfirmPassword;
    private EditText regFirstName;
    private EditText regLastName;


    @RequiresApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        openHelper = new DatabaseHelper(this);
        registerBtn = findViewById(R.id.register_btn);
        loginBtn =  findViewById(R.id.login_btn);
        regUsername =  findViewById(R.id.username);
        regEmail =  findViewById(R.id.email);
        regPassword= findViewById(R.id.password);
        regConfirmPassword = findViewById(R.id.confirm_password);
        regFirstName = findViewById(R.id.first_name);
        regLastName = findViewById(R.id.last_name);

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String fUsername = regUsername.getText().toString().trim();
                String fEmail = regEmail.getText().toString().trim();
                String fPassword = regPassword.getText().toString().trim();
                String fConfirmPassword = regConfirmPassword.getText().toString().trim();
                String fFirstName = regFirstName.getText().toString().trim();
                String fLastName = regLastName.getText().toString().trim();
                if (fUsername.isEmpty() || fPassword.isEmpty() || fConfirmPassword.isEmpty() || fFirstName.isEmpty() || fLastName.isEmpty()) {
                    Toast.makeText(
                            RegisterActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(
                            fUsername,
                            fEmail,
                            fPassword,
                            fConfirmPassword,
                            fFirstName,
                            fLastName);
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT)
                    .show();
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
        });

    }

    public void insertData(String username, String email, String password, String confirmPassword, String firstName, String lastName){
        User newUser = new User();
        if (username != null) {
            newUser.setUsername(username);
        }
        if (email != null) {
            newUser.setEmail(email);
        }
        if (password != null) {
            newUser.setPassword(password);
        }
        if (firstName != null) {
            newUser.setFirstName(firstName);
        }
        if (lastName != null){
            newUser.setLastName(lastName);
        }

        if (password.equals(confirmPassword)) {
            String passwordSalt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(password, passwordSalt);
            newUser.setPassword(hashedPassword);
            newUser.setPasswordSalt(passwordSalt);
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            AppDatabase db = databaseHelper.getDatabase(getApplicationContext());
            if (db != null) {
                db.getUserDAO().insertUser(newUser);
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Password does not match Confirm Password",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

}
