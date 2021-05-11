package ca.mohawk.project;

/*
I, Andy Le, 000805099 certify that this material is my original work. No
other person's work has been used without due acknowledgement.

https://youtu.be/GTrvtcpZaVA
 */
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "==MainActivity==";
    private static Activity currentActivity = null;
    private DrawerLayout myDrawer;
    private static MainActivity instance;

    public FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Fragment blank1 = new BlankFragment();
        fragmentTransaction.replace(R.id.frame1, blank1);
        fragmentTransaction.commit();

        currentActivity = this;
        instance = this;
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

    @Override
    public void onResume() {
        super.onResume();
    }

    // Respond to Navigation Drawer item selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected");
        // Show visual for selection
        //item.setChecked(true);
        // Close the Drawer
        myDrawer.closeDrawers();

        Intent intent = getIntent();
        Intent switchActivity = null;

        switch (item.getItemId()) {
            case R.id.item1:
                switchActivity = new Intent(MainActivity.this, MainActivity.class);
                startActivity(switchActivity);
                break;
            case R.id.item2:
                switchActivity = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(switchActivity);
                break;
            case R.id.item3:
                switchActivity = new Intent(MainActivity.this, MainActivity3.class);
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

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void getDialog(int position, FairList fairlist) {
        CustomDialog myDialog = new CustomDialog(position, fairlist);
        myDialog.show(getSupportFragmentManager(), null);
    }


}