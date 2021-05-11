package ca.mohawk.Andy;

/*
I, Andy Le, 000805099 certify that this material is my original
work. No other person's work has been used without due
acknowledgement.
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static String TAG = "==Main Activity 1==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView icon = findViewById(R.id.txtIcon);
        icon.setOnClickListener(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        }
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick");
        Intent switchActivity = new Intent(MainActivity.this, MainActivity2.class);
        EditText filter = findViewById(R.id.editTextPhoneAccept);
        String myFilter = filter.getText().toString();
        switchActivity.putExtra("filter", myFilter);
        startActivity(switchActivity);
    }
}