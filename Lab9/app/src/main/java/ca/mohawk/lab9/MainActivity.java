package ca.mohawk.lab9;

/*
I, Andy Le, 000805099 certify that this material is my original work. No
other person's work has been used without due acknowledgement.
https://youtu.be/oP4GCL3Azzc
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    public void onEnter(View view) {
        EditText input = (EditText) findViewById(R.id.input);
        String myInput = input.getText().toString();
        doNotify(myInput);
    }

    public void doNotify(String myText) {
        Log.d(TAG, myText);
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, SecondActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("input", myText);
        // pendingIntent just restarts the current activity. Add it to the builder
        // with .setContentIntent(pendingIntent) so that we collect a result.
        // This version just restarts the MainActivity when you click on the star.
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Log.d(TAG, "doNotify");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.star_big_on)
                .setContentTitle("Code Entered")
                .setContentText(myText + " was entered!")
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, builder.build());
    }

}