package com.example.splitapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText amountEditText, dateEditText;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button addButton;
    DataBaseConn dbconnection;
    StringBuilder expenseDetails = new StringBuilder();

    private void displayAllExpenses() {
        Cursor cursor = dbconnection.getAllExpenses();

        if (cursor != null && cursor.moveToFirst()) {


            do {
                @SuppressLint("Range") String amount = cursor.getString(cursor.getColumnIndex("amount"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex("radio"));

                expenseDetails.append("Amount: ").append(amount).append("\n")
                        .append("Date: ").append(date).append("\n")
                        .append("Category: ").append(category).append("\n\n");

            } while (cursor.moveToNext());

            cursor.close();

            // Assuming you have a TextView in your layout with id 'expenseTextView'
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize UI elements
        amountEditText = findViewById(R.id.amount);
        dateEditText = findViewById(R.id.Date);
        radioGroup = findViewById(R.id.radiogroup);
        addButton = findViewById(R.id.button);

        // Initialize your existing database connection helper
        dbconnection = new DataBaseConn(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addExpenseToDatabase();

            }

            private void addExpenseToDatabase() {
                // Get user input
                String amount = amountEditText.getText().toString();
                String date = dateEditText.getText().toString();

                // Get selected radio button from the radio group
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String category = selectedRadioButton.getText().toString();

                // Insert the data into the SQLite database
                long result = dbconnection.insertExpense(amount, date, category);

                if (result != -1) {
                    // Data inserted successfully
                    // You can show a success message or navigate back to another activity
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                    amountEditText.setText(" ");
                    dateEditText.setText(" ");
                    radioGroup.clearCheck();

                    displayAllExpenses();
                    Intent intent = new Intent(login.this, user.class);
                    String username =getIntent().getStringExtra("username");
                    String salary =getIntent().getStringExtra("salary");
                    intent.putExtra("expenseDetails", expenseDetails.toString());
                    intent.putExtra("username",username);
                    intent.putExtra("salary",salary);
                    startActivity(intent);

                } else {
                    // Data insertion failed
                    // You can show an error message
                    Toast.makeText(getApplicationContext(), "Data Not Inserted", Toast.LENGTH_LONG).show();


                }
            }
        });
    }

}

