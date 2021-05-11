package ca.mohawk.Andy;

/*
I, Andy Le, 000805099 certify that this material is my original work.
No other person's work has been used without due acknowledgement.
https://youtu.be/jZd9-jU1jx0
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "==MainActivity==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment top = new Top();
        fragmentTransaction.replace(R.id.frame1, top);
        fragmentTransaction.commit();

        Log.d(TAG, "onCreate");
    }

}