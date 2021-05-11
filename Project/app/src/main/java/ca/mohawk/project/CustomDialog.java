package ca.mohawk.project;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomDialog extends DialogFragment {

    public static String TAG = "==DialogFragment";
    private static FairList fl = null;
    private static int item = 0;

    public CustomDialog(int position, FairList fairlist) {
        Log.d(TAG, "construction");
        fl = fairlist;
        item = position;
        Log.d(TAG, "number" + position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custom_dialog, container, false);

        Log.d(TAG, "onCreateView, myview = " + v);

        TextView name = v.findViewById(R.id.courseTitle);
        name.setText(fl.get(item).courseTitle);

        TextView description = v.findViewById(R.id.courseDescription);
        description.setText("Description: \n" + fl.get(item).courseDescription);

        TextView other = v.findViewById(R.id.other);
        other.setText("Program: " + fl.get(item).program);

        TextView other2 = v.findViewById(R.id.other2);
        other2.setText("Semester: " + fl.get(item).semester);

        return v;
    }

}