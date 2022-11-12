package com.joelbland.frienddb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();

        ScrollView scrollView = new ScrollView(this);

        TableLayout layout = new TableLayout(this);
        layout.setPadding(60,60,60,60);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView header = new TextView(this);
        header.setText("Friend List");
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
            if(!friend.getFirstName().equals("")) {
                String name = friend.getFirstName() + " " + friend.getLastName();
                name += " (" + friend.getEmail() + ")";
                tv.setText(name);
                tv.setTextSize(16);
                layout.addView(tv);
            }
        }

        scrollView.addView(layout);

        setContentView(scrollView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Log.w("MainActivity", "Add");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "Update");
                Intent updateIntent = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateView();
    }
}