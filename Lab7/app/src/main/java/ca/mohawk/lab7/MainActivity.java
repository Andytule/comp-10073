package ca.mohawk.lab7;

/*
I, Andy Le, 000805099 certify that this material is my original work. No other person's work has
been used without due acknowledgement.
https://youtu.be/GF_iQrBolvM
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity==";

    // Create a global instance of our SQL Helper class
    MyDbHelper mydbhelp = new MyDbHelper(this);

    /**
     * onCreate (default)
     * @param savedInstanceState - (default)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    /**
     * onClick handler, adds a data base row consisting of two fields
     * @param view - button view (unused)
     */
    public void addData(View view) {

        // Get an instance of the database using our helper class
        SQLiteDatabase db = mydbhelp.getWritableDatabase();
        EditText name = (EditText) findViewById(R.id.productName);
        EditText serial = (EditText) findViewById(R.id.serialNumber);

        String nameInput = name.getText().toString();
        String serialInput = serial.getText().toString();

        // A ContentValues class provides an easy helper function to add our values
        ContentValues values = new ContentValues();
        // Similar to using a bundle - put values using column name and value
        values.put(MyDbHelper.NAME, nameInput);
        values.put(MyDbHelper.SERIAL, serialInput);

        if (nameInput.length() != 0) {
            // The db.insert command will do a SQL insert on our table, return the new row ID
            long newrowID = db.insert(MyDbHelper.MYTABLE, null, values);
            Log.d(TAG, "New ID " + newrowID);
        }
        // Clear out fields for next entry
        name.setText("");
        serial.setText("");
    }

    /**
     * onClick handler, launches our List Activity
     * @param view - button view (unused)
     */
    public void callList(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        Log.d(TAG, "callList");
    }
}