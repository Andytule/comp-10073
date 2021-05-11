package ca.mohawk.exam;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DataEntryFragment extends Fragment {

    private static final String TAG = "==DataEntryFragment==";

    public DataEntryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_entry, container, false);
        Button button = (Button) v.findViewById(R.id.btnAddGrade);
        button.setOnClickListener(this::callBack);
        return v;
    }

    public void callBack(View view) {
        Log.d(TAG, "callBack");
        ((MainActivity)getActivity()).addData(view);
    }
}