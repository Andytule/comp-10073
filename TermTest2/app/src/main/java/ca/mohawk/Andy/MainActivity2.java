package ca.mohawk.Andy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Queue;

public class MainActivity2 extends AppCompatActivity {

    public static String TAG = "==Main Activity 2==";
    public static ArrayList<String> messages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String filter = intent.getStringExtra("filter");
        String message = intent.getStringExtra("MSG");

        if (message != null) {
            if (messages.size() == 4) {
                messages.remove(0);
            }
            messages.add(message);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment msg = new Message();
        fragmentTransaction.replace(R.id.frame1, msg);
        fragmentTransaction.commit();
    }
}