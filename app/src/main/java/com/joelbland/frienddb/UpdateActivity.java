package com.joelbland.frienddb;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    private void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();

        ScrollView scrollView = new ScrollView(this);

        TableLayout layout = new TableLayout(this);
        layout.setPadding(60,60,60,60);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        ButtonHandler bh = new ButtonHandler();

        TextView header = new TextView(this);
        header.setText("Touch Name to Edit Friend");
        header.setTextSize(18);
        header.setTypeface(Typeface.DEFAULT_BOLD);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0,0,0,20);
        layout.addView(header, params);

        for(Friend friend: friends ) {

            TextView tv = new TextView(this);
            tv.setId(friend.getId());
            String name = friend.getFirstName() + " " + friend.getLastName();
            tv.setText(name);
            tv.setTextSize(16);

            tv.setOnClickListener(bh);

            layout.addView(tv);
        }

        scrollView.addView(layout);
        setContentView(scrollView);
    }


    // button handler for clicking on names
    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            handleButtonClick(id);
        }
    }

    // calls intent to edit friend info
    private void handleButtonClick(int id) {
        String idString = String.valueOf(id);
        Intent updateFriendIntent = new Intent(this, UpdateFriendActivity.class);
        updateFriendIntent.putExtra("id", idString);
        this.startActivity(updateFriendIntent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateView();
    }


}
