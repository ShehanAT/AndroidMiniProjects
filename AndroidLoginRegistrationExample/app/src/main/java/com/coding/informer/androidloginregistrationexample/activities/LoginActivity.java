package com.coding.informer.androidloginregistrationexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.coding.informer.androidloginregistrationexample.MainActivity;
import com.coding.informer.androidloginregistrationexample.R;
import com.coding.informer.androidloginregistrationexample.database.AppDatabase;
import com.coding.informer.androidloginregistrationexample.helpers.DatabaseHelper;
import com.coding.informer.androidloginregistrationexample.helpers.Functions;
import com.coding.informer.androidloginregistrationexample.helpers.SessionManager;
import com.coding.informer.androidloginregistrationexample.models.UserFieldType;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText inputUsernameField;
    private EditText inputPasswordField;
    private Button loginBtn;
    private Button registerBtn;
    private String username;
    private String password;
    private DatabaseHelper db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputUsernameField = findViewById(R.id.input_username);
        inputPasswordField = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        // create SQLite database
        db = new DatabaseHelper(this);
        session = new SessionManager(this);
        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        initialize(inputUsernameField, inputPasswordField);
    }

    public void initialize(EditText inputUsernameField, EditText inputPasswordField) {
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = inputUsernameField.getText().toString().trim();
                String password = inputPasswordField.getText().toString().trim();
                if(username != null && password != null){
                    loginProcess(username, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginProcess(String username, String password) {
        String tag_string_req = "req_login";
        DialogFragment dialogFragment = showDialog("Logging in...");
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        String storedUserPassword = databaseHelper.getUserFieldSQLQuery(
                getApplicationContext(),
                UserFieldType.PASSWORD,
                username
        );
        String storedPasswordSalt = databaseHelper.getUserFieldSQLQuery(
                getApplicationContext(),
                UserFieldType.PASSWORD_SALT,
                username
        );


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String message = null;
                if (storedUserPassword != null) {
                    String hashedInputPassword = BCrypt.hashpw(password, storedPasswordSalt);
                    if (hashedInputPassword.equals(storedUserPassword)) {
                        message = "Login successful!";
                    } else {
                        message = "Login failed! Please try again...";
                    }
                } else {
                    message = "Login failed! Please try again...";
                }
                String finalMessage = message;
                LoginActivity.this.runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), finalMessage, Toast.LENGTH_LONG).show();
                });
            }
        }, 500);
    }


    private DialogFragment showDialog(String title){
        return Functions.showProgressDialog(LoginActivity.this, title);
    }
}
