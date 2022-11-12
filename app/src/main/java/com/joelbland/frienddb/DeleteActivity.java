package com.joelbland.frienddb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    private void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for( Friend friend: friends) {
            RadioButton rb = new RadioButton(this);
            rb.setId(friend.getId());
            rb.setText(friend.getFirstName() + " " + friend.getLastName());
            group.addView(rb);
        }

        // event handler
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        // back button
        Button btnBack = new Button(this);
        btnBack.setText("Back");
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { DeleteActivity.this.finish(); }
        });
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);
        layout.addView(btnBack,params);

        scrollView.addView(group);
        layout.addView(scrollView);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // delete candy from db
            dbManager.deleteById(checkedId);
            Toast.makeText(DeleteActivity.this, "Friend deleted", Toast.LENGTH_SHORT).show();
            // update screen
            updateView();
        }
    }

}
