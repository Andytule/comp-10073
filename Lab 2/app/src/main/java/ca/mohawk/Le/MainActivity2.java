package ca.mohawk.Le;
/*
I, Andy Le, 000805099 certify that this material is my original work. No
other person's work has been used without due acknowledgement.
https://youtu.be/QT4-KCfJ4Hw
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final static String tag = "==Activity Two==";
    final static String curr = "From Second Activity";
    // user navigates to the activity, render the display
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(tag,"onCreate()");

        Spinner mySpinner = findViewById(R.id.spinner2);
        // Set a selection before setting listener,
        // to prevent "ghost" selection on start
        mySpinner.setSelection(1, false);
        mySpinner.setOnItemSelectedListener(this);

        TextView nameOutput = findViewById(R.id.name2);
        nameOutput.setText("");
        TextView activityOutput = findViewById(R.id.from2);
        activityOutput.setText("");

        Intent intent = getIntent();
        String name = intent.getStringExtra("loginText");
        String activity = intent.getStringExtra("activity");

        Log.d(tag,"onCreate() - received '" + name + "'");
        Log.d(tag,"onCreate() - received '" + activity + "'");

        nameOutput.setText(name);
        activityOutput.setText(activity);
    }
    // reached after onCreate() or onRestart()
    // called when the activity is about to become visible
    @Override
    protected void onStart() {
        super.onStart();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Load string array from resources
        String[] activities = getResources().getStringArray(R.array.activities);
        // log the spinner's choice
        Intent intent = getIntent();
        String name = intent.getStringExtra("loginText");
        Log.d(tag, activities[position]);

        if (position == 0) {
            Intent switchActivity = new Intent(MainActivity2.this, MainActivity.class);
            switchActivity.putExtra("loginText", name);
            switchActivity.putExtra("activity", curr);
            startActivity(switchActivity);
        } else if (position == 2) {
            Intent switchActivity = new Intent(MainActivity2.this, MainActivity3.class);
            switchActivity.putExtra("loginText", name);
            switchActivity.putExtra("activity", curr);
            startActivity(switchActivity);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // log the spinner's choice
        Log.d(tag, "Nothing Selected");
    }
}