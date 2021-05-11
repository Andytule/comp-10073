package ca.mohawk.Andy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private final String NUM = "NUM";

    final static String tag = "==Activity Two==";

    private String toReset = "false";

    private int myChoice = 0;

    // user navigates to the activity, render the display
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tView = findViewById(R.id.output);
        setContentView(R.layout.activity_main2);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment myInput = new Input(tView, myChoice);
        Fragment myDisplay = new Display();
        fragmentTransaction.replace(R.id.frame1, myDisplay);
        fragmentTransaction.replace(R.id.frame2, myInput);
        fragmentTransaction.commit();


        Intent intent = getIntent();
        String myOption = intent.getStringExtra("myOption");
        toReset = intent.getStringExtra("myReset");
        myChoice = Integer.parseInt(myOption);
        Log.d(tag,"onCreate()");
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

        String[] activities = getResources().getStringArray(R.array.options);
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String switchText = sharedPreferences.getString(NUM, "0");
        TextView myOutput = findViewById(R.id.output);
        if (toReset.equals("true")) {
            myOutput.setText("0");
        }
        Button myButton = findViewById(R.id.action);
        myButton.setText(activities[myChoice]);
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
        TextView textView = findViewById(R.id.output);
        String text = textView.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NUM, text);
        editor.apply();
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


}