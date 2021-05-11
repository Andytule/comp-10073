package ca.mohawk.Andy;
/*
I, Andy Le, 000805099 certify that this material is my original work.
No other person's work has been used without due
acknowledgement.
https://youtu.be/nzMca9gxLq8
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private final String NUM = "NUM";

    public static String tag = "==MainActivity==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "onCreate() b4 setContentView");
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        // New Fragment Transaction...
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // Create a new instance of our Fragment class
        Fragment myTop = new TopHalf();
        Fragment myBottom = new BottomHalf();
        // Attach the fragment instance to a frame (viewgroup) in our layout.
        // Use .replace to ensure that any previous fragment in the frame is detached.
        fragmentTransaction.replace(R.id.frame1, myTop);
        fragmentTransaction.replace(R.id.frame2, myBottom);
        // Commit when done (you can do multiple transactions in a single commit)
        fragmentTransaction.commit();

        TextView myOutput = findViewById(R.id.textView);

        Log.d(tag, "onCreate() finished");
    }

    // called immediately before activity becomes active
    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag, "onResume()");
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        String switchText = sharedPreferences.getString(NUM, "0");
        TextView textView = findViewById(R.id.textView);
        textView.setText(switchText);
    }

    // called when activity loses focus
    @Override
    public void onPause() {
        super.onPause();
        Log.d(tag, "onPause()");
        TextView textView = findViewById(R.id.textView);
        String text = textView.getText().toString();
        // use the shared preferences editor object to modify
        // the values saved for our application.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // There are several put methods, putString is just one
        editor.putString(NUM, text);
        editor.apply();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy()");
    }

    public void onAdd(View view) {
        TextView myOutput = findViewById(R.id.textView);
        int myNum = Integer.valueOf(myOutput.getText().toString());
        myNum += 1;
        String temp = String.valueOf(myNum);
        myOutput.setText(temp);
    }

    public void onSub(View view) {
        TextView myOutput = findViewById(R.id.textView);
        int myNum = Integer.valueOf(myOutput.getText().toString());
        myNum -= 1;
        String temp = String.valueOf(myNum);
        myOutput.setText(temp);
    }
}