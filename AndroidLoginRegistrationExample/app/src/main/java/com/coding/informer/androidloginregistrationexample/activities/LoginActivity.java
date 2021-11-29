package com.coding.informer.androidloginregistrationexample.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.coding.informer.androidloginregistrationexample.MainActivity;
import com.coding.informer.androidloginregistrationexample.R;
import com.coding.informer.androidloginregistrationexample.database.AppDatabase;
import com.coding.informer.androidloginregistrationexample.helpers.DatabaseHelper;
import com.coding.informer.androidloginregistrationexample.helpers.SessionManager;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.Timer;

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

//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initialize(inputUsernameField, inputPasswordField);
    }

    public void initialize(EditText inputUsernameField, EditText inputPasswordField) {
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = inputUsernameField.getText().toString().trim();
                String password = inputPasswordField.getText().toString().trim();
                if(username != null && password != null){
                    Log.d(TAG, "asdf");
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
//        DialogFragment dialogFragment = showDialog("Logging in...");
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
//        val storedUserPassword = databaseHelper.getUserFieldSQLQuery(
//                applicationContext,
//                UserFieldType.PASSWORD,
//                username
//        )
//        val storedPasswordSalt = databaseHelper.getUserFieldSQLQuery(
//                applicationContext,
//                UserFieldType.PASSWORD_SALT,
//                username
//        )
//        Timer().schedule(object : TimerTask() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            override fun run() {
//                hideDialog(dialogFragment)
//                var message: String? = null
//                message = if (storedUserPassword != null) {
//                    val hashedInputPassword = BCrypt.hashpw(password, storedPasswordSalt)
//                    if (hashedInputPassword == storedUserPassword) {
//                        "Login successful!"
//                    } else {
//                        "Login failed! Please try again..."
//                    }
//                } else {
//                    "Login failed! Please try again..."
//                }
//                Functions.showToastMessage(this@LoginActivity, applicationContext, message)
//            }
//        }, 1000)
    }

//    @WorkerThread
//    private fun showToastMessage(message: String) {
//        runOnUiThread { Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show() }
//    }

//    private DialogFragment showDialog(String title){
//        return Functions.showProgressDialog(LoginActivity.this, title);
//    }

//    private fun hideDialog(dialogFragment: DialogFragment) {
//        dialogFragment.dismiss()
//    }

}
