package com.example.splitapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class user extends AppCompatActivity {
    EditText profile;
    Button backButton;
    TextView expenseTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        profile = (EditText) findViewById(R.id.profile);
        backButton = (Button) findViewById(R.id.add);
        expenseTextView = findViewById(R.id.log);
        String username =getIntent().getStringExtra("username");
        String salary =getIntent().getStringExtra("salary");
        String expenseDetails =getIntent().getStringExtra("expenseDetails");
        profile.setText("Hello!"+username+"\n Salary : "+salary);

        expenseTextView.setText("\n"+expenseDetails);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to go back to the login activity
                Intent intent = new Intent(user.this, login.class);
                startActivity(intent);
                // Close the current activity (optional)
                finish();
            }
        });
    }
}





