package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 绑定控件
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在此处添加登录逻辑
                // Validate the credentials entered by the user
                if (validateCredentials()) {
                    // Save the user's credentials to SharedPreferences or a database if needed
                    // Assume "username" is the variable containing the user's login username
                    String username = usernameEditText.getText().toString().trim();
                    SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("username", username); // Replace "username" with the variable containing the user's username
                    editor.apply(); // Call apply() to save the preference changes

                    Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                    finish(); // Call finish() to prevent the user from navigating back to LoginActivity
                } else {
                    // Display an error message to the user
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validateCredentials() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Here, you can implement the actual credential validation logic e.g. by checking against a user database
        // For demonstration purposes, we just check if the username and password fields are not empty
        return !username.isEmpty() && !password.isEmpty();
    }
}
