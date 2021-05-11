package ca.mohawk.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "==MainActivity3==";
    private DrawerLayout myDrawer;

    // Create a global instance of our SQL Helper class
    MyDbHelper mydbhelp = new MyDbHelper(this);

    /**
     * onCreate - get an instance of our database, use a cursor to display the values
     * @param savedInstanceState (default)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

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

        Log.d(TAG, "onCreate");
        // Get an instance of the database using our helper class
        SQLiteDatabase db = mydbhelp.getReadableDatabase();
        // A projection defines what fields we want to retrieve.
        String[] projection = { MyDbHelper.ID, MyDbHelper.COURSE, MyDbHelper.FEEDBACK};
        // db.query will retreive the data and return a Cursor to access it
        Cursor mycursor = db.query(MyDbHelper.MYTABLE, projection, null,
                null, null, null, null);

        ArrayList<String> myStringList = new ArrayList<String>();

        if (mycursor != null) {
            // Loop through our returned results from the start
            while (mycursor.moveToNext()) {
                Log.d(TAG, "found DB item");
                String name = mycursor.getString(
                        mycursor.getColumnIndex(MyDbHelper.COURSE));
                String serial = mycursor.getString(
                        mycursor.getColumnIndex(MyDbHelper.FEEDBACK));
                long itemID = mycursor.getLong(
                        mycursor.getColumnIndex(MyDbHelper.ID));
                // We could add our results to an array, or process them here if we want
                myStringList.add(itemID + " " + name + " - " + serial); //XXX hackish
            }
            // Close the cursor when we're done
            mycursor.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringList);

            ListView randList = findViewById(R.id.listView);
            randList.setOnItemClickListener(this::onItemClick);
            randList.setAdapter(adapter);
        }
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
                switchActivity = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(switchActivity);
                break;
            case R.id.item2:
                switchActivity = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(switchActivity);
                break;
            case R.id.item3:
                switchActivity = new Intent(MainActivity3.this, MainActivity3.class);
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

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        Log.i("DG", "item clicked on = " + position);
        Log.i("DG", "adapter = " + parent);
        Log.i("DG", "View = " + v);
        Log.i("DG", "id = " + id);

        Toast.makeText(this,((TextView) v).getText(), Toast.LENGTH_LONG).show();
    }
}