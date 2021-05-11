package ca.mohawk.lab8;
/*
I, Andy Le, 000805099 certify that this material is my original work. No
other person's work has been used without due acknowledgement.
https://youtu.be/3BkS6mOhdEs
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "==MainActivity==";
    private DrawerLayout myDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO Access myDrawer
        myDrawer = (DrawerLayout)
                findViewById(R.id.drawer_layout);

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
    }

    // Respond to Navigation Drawer item selected
    @Override
    public boolean onNavigationItemSelected(@NonNull
                                                    MenuItem item) {
        // Show visual for selection
        //item.setChecked(true);
        // Close the Drawer
        myDrawer.closeDrawers();

        TextView courseCode = (TextView) findViewById(R.id.code);
        TextView courseName = (TextView) findViewById(R.id.course);
        TextView courseDesc = (TextView) findViewById(R.id.description);

        String[] course1 = getResources().getStringArray(R.array.comp10001);
        String[] course2 = getResources().getStringArray(R.array.compCO710);
        String[] course3 = getResources().getStringArray(R.array.compCO910);

        switch (item.getItemId()) {
            case R.id.nav_camera:
                courseCode.setText(course1[0]);
                courseName.setText(course1[1]);
                courseDesc.setText(course1[2]);
                break;
            case R.id.nav_gallery:
                courseCode.setText(course2[0]);
                courseName.setText(course2[1]);
                courseDesc.setText(course2[2]);
                break;
            case R.id.nav_settings:
                courseCode.setText(course3[0]);
                courseName.setText(course3[1]);
                courseDesc.setText(course3[2]);
                break;
        }
        Log.d(TAG, "Item Selected = " + item);
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

}
