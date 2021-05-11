package ca.mohawk.exam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataListFragment extends Fragment {

    private static final String TAG = "==DataListFragment==";

    MyDbHelper mydbhelp = null;

    ArrayList<String> myStringList = null;

    public DataListFragment(MyDbHelper mydb) {
        mydbhelp = mydb;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data_list, container, false);

        SQLiteDatabase db = mydbhelp.getReadableDatabase();
        String[] projection = { MyDbHelper.ID, MyDbHelper.NAME, MyDbHelper.GRADE};
        Cursor mycursor = db.query(MyDbHelper.MYTABLE, projection, null,
                null, null, null, null);

        myStringList = new ArrayList<String>();

        if (mycursor != null) {
            while (mycursor.moveToNext()) {
                Log.d(TAG, "found DB item");
                String name = mycursor.getString(
                        mycursor.getColumnIndex(MyDbHelper.NAME));
                String grade = mycursor.getString(
                        mycursor.getColumnIndex(MyDbHelper.GRADE));
                long itemID = mycursor.getLong(
                        mycursor.getColumnIndex(MyDbHelper.ID));
                myStringList.add(itemID + " " + name + " - " + grade);
            }

            mycursor.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myStringList);

            ListView randList = v.findViewById(R.id.listView);
            randList.setOnItemClickListener(this::onItemClick);
            randList.setAdapter(adapter);

        }

        return v;
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {

        Log.d(TAG, myStringList.get(position));
        Toast.makeText(getActivity(), myStringList.get(position), Toast.LENGTH_LONG).show();
    }
}