package com.joelbland.frienddb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateFriendActivity extends AppCompatActivity {
    String idString;
    DatabaseManager dbManager;
    Friend friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_update_friend);

        Intent intent = getIntent();
        idString = intent.getStringExtra("id");
        friend = dbManager.selectById(idString);

        EditText etFirstName = findViewById(R.id.etEditFirstName);
        EditText etLastName = findViewById(R.id.etEditLastName);
        EditText etEmail = findViewById(R.id.etEditEmail);

        etFirstName.setText(friend.getFirstName());
        etLastName.setText(friend.getLastName());
        etEmail.setText(friend.getEmail());

    }

    public void editFriend(View view) {

        EditText etFirstName = findViewById(R.id.etEditFirstName);
        EditText etLastName = findViewById(R.id.etEditLastName);
        EditText etEmail = findViewById(R.id.etEditEmail);

        int id = friend.getId();
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String email = etEmail.getText().toString();

        try {
            dbManager.updateById(id,firstname,lastname,email);
            Toast.makeText(UpdateFriendActivity.this, "Database Updated", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {
            Toast.makeText(UpdateFriendActivity.this, "DB Error", Toast.LENGTH_LONG).show();
        }
        this.finish();
    }

    public void goBack(View view) {
        this.finish();
    }
}
