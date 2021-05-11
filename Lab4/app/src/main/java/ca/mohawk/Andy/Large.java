package ca.mohawk.Andy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class Large extends Fragment {

    public Large() {
        Log.d(TAG, "constructor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");

        View v = inflater.inflate(R.layout.fragment_large, container, false);

        EditText input = getActivity().findViewById(R.id.input);
        TextView output = v.findViewById(R.id.largeText);
        output.setText(input.getText().toString());
        return v;
    }
}