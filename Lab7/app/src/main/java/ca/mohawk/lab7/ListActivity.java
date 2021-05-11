package ca.mohawk.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public static final String TAG = "==ListActivity==";

    // Create a global instance of our SQL Helper class
    MyDbHelper mydbhelp = new MyDbHelper(this);

    /**
     * onCreate - get an instance of our database, use a cursor to display the values
     * @param savedInstanceState (default)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d(TAG, "onCreate");
        // Get an instance of the database using our helper class
        SQLiteDatabase db = mydbhelp.getReadableDatabase();
        // A projection defines what fields we want to retrieve.
        String[] projection = { MyDbHelper.ID, MyDbHelper.NAME, MyDbHelper.SERIAL};
        // db.query will retreive the data and return a Cursor to access it
        Cursor mycursor = db.query(MyDbHelper.MYTABLE, projection, null,
                null, null, null, null);

        ArrayList<String> myStringList = new ArrayList<String>();

        if (mycursor != null) {
            // Loop through our returned results from the start
            while (mycursor.moveToNext()) {
                Log.d(TAG, "found DB item");
                String name = mycursor.getString(
                        mycursor.getColumnIndex(MyDbHelper.NAME));
                String serial = mycursor.getString(
                        mycursor.getColumnIndex(MyDbHelper.SERIAL));
                long itemID = mycursor.getLong(
                        mycursor.getColumnIndex(MyDbHelper.ID));
                // We could add our results to an array, or process them here if we want
                myStringList.add(itemID + " " + name + " - " + serial); //XXX hackish
            }
            // Close the cursor when we're done
            mycursor.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringList);

            ListView randList = findViewById(R.id.listView);
            randList.setOnItemClickListener(this::onItemClick);
            randList.setAdapter(adapter);
        }
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        Log.i("DG", "item clicked on = " + position);
        Log.i("DG", "adapter = " + parent);
        Log.i("DG", "View = " + v);
        Log.i("DG", "id = " + id);

        Toast.makeText(this,((TextView) v).getText(), Toast.LENGTH_LONG).show();
    }

}