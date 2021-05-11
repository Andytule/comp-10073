package ca.mohawk.Andy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Message extends Fragment {

    public static String tag = "==Message Fragment==";

    public Message() {
        Log.d(tag, "construction");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        Log.d(tag, "onCreateView, myview = " + v);
        return v;
    }
}