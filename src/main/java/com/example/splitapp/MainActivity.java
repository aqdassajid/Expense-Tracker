package com.example.splitapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signUpButton = findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields
                EditText usernameEditText = findViewById(R.id.Username);
                EditText msalaryEditText = findViewById(R.id.Monthlysal);
                EditText bGoalEditText = findViewById(R.id.Bgoal);

                String username = usernameEditText.getText().toString();
                String salary = msalaryEditText.getText().toString();

                // Create an intent to open the next activity

                Intent intent = new Intent(MainActivity.this, login.class);

                // Pass data as extras to the intent
                intent.putExtra("username", username);
                intent.putExtra("salary", salary);

                startActivity(intent); // Start the new activity
            }
        });
    }
}
