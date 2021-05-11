package ca.mohawk.Andy;

/*
I, Andy Le, 000805099 certify that this material is my original work.
No other person's work has been used without due
acknowledgement.
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final static String tag = "==Activity One==";

    private int myOption = 0;
    private String myReset = "false";
    // user navigates to the activity, render the display
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner mySpinner = findViewById(R.id.spinner);
        mySpinner.setSelection(0, false);
        mySpinner.setOnItemSelectedListener(this);
        Log.d(tag,"onCreate()");
    }
    // reached after onCreate() or onRestart()
    // called when the activity is about to become visible
    @Override
    protected void onStart() {
        super.onStart();
        Switch mySwitch = findViewById(R.id.reset);
        mySwitch.setChecked(false);
        Log.d(tag,"onStart()");
    }
    // reached after onPause() or onStart()
    // called when the activity has become visible
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"onResume()");
    }
    /**
     * ----------------------------------------------
     * Activity is running after call to onResume()
     * ----------------------------------------------
     */
    // another activity comes into the foreground
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(tag,"onPause()");
    }
    // activity is no longer visible
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(tag,"onStop()");
    }
    // user navigates to the activity after the stop state
    // next state will be onStart()
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(tag,"onRestart()");
    }
    // activity is finishing or being destroyed by the system
    // reached after onStop()
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(tag, "onDestroy()");
    }

    public void switchOver(View view) {
        Intent switchActivity = new Intent(MainActivity.this, MainActivity2.class);
        String temp = String.valueOf(myOption);
        switchActivity.putExtra("myOption", temp);
        switchActivity.putExtra("myReset", myReset);
        startActivity(switchActivity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] activities = getResources().getStringArray(R.array.options);
        Log.d(tag, activities[position]);
        myOption = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // log the spinner's choice
        Log.d(tag, "Nothing Selected");
    }

    public void switchChanged(View view) {
        Switch mySwitch = (Switch) view;
        if (mySwitch.isChecked()) {
            myReset = "true";
        } else {
            myReset = "false";
        }
        Log.d(tag, myReset);
    }
}