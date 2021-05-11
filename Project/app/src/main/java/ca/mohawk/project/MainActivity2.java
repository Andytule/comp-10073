package ca.mohawk.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "==MainActivity2==";
    private DrawerLayout myDrawer;

    public FragmentManager fm = getSupportFragmentManager();

    // Create a global instance of our SQL Helper class
    MyDbHelper mydbhelp = new MyDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // TODO Access myDrawer
        myDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // TODO Access the ActionBar, enable "home" icon
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        // TODO set up callback method for Navigation View
        ActionBarDrawerToggle myactionbartoggle = new
                ActionBarDrawerToggle(this, myDrawer,
                (R.string.open), (R.string.close));
        myDrawer.addDrawerListener(myactionbartoggle);
        myactionbartoggle.syncState();

        // TODO Add an ActionBarDrawerToggle element

        NavigationView myNavView = (NavigationView)
                findViewById(R.id.nav_view);
        myNavView.setNavigationItemSelectedListener(this);
    }

    // Respond to Navigation Drawer item selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected");
        // Show visual for selection
        //item.setChecked(true);
        // Close the Drawer
        myDrawer.closeDrawers();

        Intent switchActivity = null;
        switch (item.getItemId()) {
            case R.id.item1:
                switchActivity = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(switchActivity);
                break;
            case R.id.item2:
                switchActivity = new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(switchActivity);
                break;
            case R.id.item3:
                switchActivity = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(switchActivity);
                break;
        }
        Log.d(TAG, "Item Selected = " + item);
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        // Find out the current state of the drawer (open or closed)
        boolean isOpen = myDrawer.isDrawerOpen(GravityCompat.START);
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // Home button - open or close the drawer
                if (isOpen == true) {
                    myDrawer.closeDrawer(GravityCompat.START);
                } else {
                    myDrawer.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * onClick handler, adds a data base row consisting of two fields
     * @param view - button view (unused)
     */
    public void addData(View view) {
        // Get an instance of the database using our helper class
        SQLiteDatabase db = mydbhelp.getWritableDatabase();
        EditText title = (EditText) findViewById(R.id.courseName);
        EditText subtitle = (EditText) findViewById(R.id.courseFeedback);
        Log.d(TAG, title.getText().toString());
        // A ContentValues class provides an easy helper function to add our values
        ContentValues values = new ContentValues();

        String myTitle = title.getText().toString();
        String mySubtitle = subtitle.getText().toString();
        // Similar to using a bundle - put values using column name and value
        values.put(MyDbHelper.COURSE, myTitle);
        values.put(MyDbHelper.FEEDBACK, mySubtitle);
        // The db.insert command will do a SQL insert on our table, return the new row ID
        if ((myTitle.length() != 0) && (mySubtitle.length() != 0)) {
            long newrowID = db.insert(MyDbHelper.MYTABLE, null, values);
            Log.d(TAG, "New ID " + newrowID);
        }

        // Clear out fields for next entry
        title.setText("");
        subtitle.setText("");
    }
}