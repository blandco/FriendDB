package com.joelbland.frienddb;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view) {
        EditText etFirstName = findViewById(R.id.etEditFirstName);
        EditText etLastName = findViewById(R.id.etEditLastName);
        EditText etEmail = findViewById(R.id.etEditEmail);
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String email = etEmail.getText().toString();

        try {
            Friend friend = new Friend(0,firstname,lastname,email);
            dbManager.insert(friend);
            Toast.makeText(this,firstname + " " + lastname + " added", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {
            Toast.makeText(this,"Insert error", Toast.LENGTH_LONG).show();
        }

        etFirstName.setText("");
        etLastName.setText("");
        etEmail.setText("");
    }

    public void goBack(View view) {
        this.finish();
    }
}
