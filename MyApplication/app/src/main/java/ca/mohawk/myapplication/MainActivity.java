package ca.mohawk.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity";

    private static Activity currentActivity = null;

    /**
     * onCreate (default)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentActivity = this;
        Log.d(TAG, "onCreate");

    }

    @Override
    public void onResume() {
        super.onResume();
        startDownload(null);
    }

    /**
     * Provides access to the current activity
     * @return foreground Activity
     */
    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * onClick handler composes URI and initiates download in background
     * @param view - from button (unused)
     */
    public void startDownload(View view) {
        DownloadAsyncTask dl = new DownloadAsyncTask();

        // Build call to Webservice
        String uri = "http://www.basef.ca/api/basefws.php";
        EditText editYear = (EditText) findViewById(R.id.editText);
        String sYear = editYear.getText().toString();
        if (!sYear.equals("")) {
            // Filter - numbers (Year) no quotes on value, strings have quotes on value
            uri += "?filter=[{\"type\":\"number\",\"column\":\"Year\",\"value\":" + sYear + "}]";
        }
        Log.d(TAG, "startDownload " + uri);
        dl.execute(uri);
    }

}