package ca.mohawk.lab10;

/*
I, Andy Le 000805099 certify that this material is my original work. No other person's work has
been used without due acknowledgement.
https://youtu.be/hX3CgY335do
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity";
    private static Activity mainActivity = null;

    public static Activity getMainActivity() {
        return mainActivity;
    }

    /**
     * onCreate - savea  reference to the current object
     * @param savedInstanceState (default)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;
        Log.d(TAG, "onCreate");
    }

    /**
     * onClick handler for button - read the editText and select an image source
     * @param view - default (unused)
     */
    public void getImage(View view) {
        // Grab the URL from edit text.
        // If the supplied text is too short, or doesn't include http, download a cat
        EditText input = findViewById(R.id.editText);
        String url = input.getText().toString();
        Log.d(TAG, "getImage = " + url);
        // We create and execute our AsyncTask
        // Pass in the URL of the image to display

        DownloadImageTask dl = new DownloadImageTask();
        dl.execute(url);

    }

}