package ca.mohawk.exam;

/*
I, Andy Le, 000805099 certify that this material is my original
work. No other person's work has been used without due
acknowledgement. (Replace with your own name and student number)
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

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "==MainActivity==";

    private DrawerLayout myDrawer;

    MyDbHelper mydbhelp = new MyDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle myactionbartoggle = new
                ActionBarDrawerToggle(this, myDrawer,
                (R.string.open), (R.string.close));
        myDrawer.addDrawerListener(myactionbartoggle);
        myactionbartoggle.syncState();

        NavigationView myNavView = (NavigationView)
                findViewById(R.id.navView);
        myNavView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment dataEntry = new DataEntryFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frmMain, dataEntry);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected");
        myDrawer.closeDrawers();

        FragmentManager fm = getSupportFragmentManager();
        Fragment dataEntry = new DataEntryFragment();
        Fragment dataList = new DataListFragment(mydbhelp);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (item.getItemId()) {
            case R.id.item1:
                fragmentTransaction.replace(R.id.frmMain, dataEntry);
                fragmentTransaction.commit();
                break;
            case R.id.item2:
                fragmentTransaction.replace(R.id.frmMain, dataList);
                fragmentTransaction.commit();
                break;
        }
        Log.d(TAG, "Item Selected = " + item);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        boolean isOpen = myDrawer.isDrawerOpen(GravityCompat.START);
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isOpen == true) {
                    myDrawer.closeDrawer(GravityCompat.START);
                } else {
                    myDrawer.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addData(View view) {
        SQLiteDatabase db = mydbhelp.getWritableDatabase();
        EditText name = (EditText) findViewById(R.id.editName);
        EditText grade = (EditText) findViewById(R.id.editGrade);
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.NAME, name.getText().toString());
        values.put(MyDbHelper.GRADE, grade.getText().toString());
        long newrowID = db.insert(MyDbHelper.MYTABLE, null, values);
        Log.d(TAG, "New ID " + newrowID);
        name.setText("");
        grade.setText("");
    }
}