package ca.mohawk.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "==SecondActivity==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView output = findViewById(R.id.output);
        Intent intent = getIntent();
        String myOutput = intent.getStringExtra("input");
        output.setText(myOutput);

        Log.d(TAG, "onCreate" + myOutput);
    }
}