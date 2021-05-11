package ca.mohawk.Andy;
/*
I, Andy Le, 000805099 certify that this material is my original work.
No other person's work has been used without due
acknowledgement.
https://youtu.be/nzMca9gxLq8
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class BottomHalf extends Fragment {
    public static String tag = "==BlankFragment==";
    private static int fragCount = 0;
    private int fragID;
    public BottomHalf() {
        // Required empty public constructor
        fragCount += 1;
        fragID = fragCount;
        Log.d(tag, "constructor: "+ fragID);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "onCreate(): " + fragID);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(tag, "onCreateView(): " + fragID);
        return inflater.inflate(R.layout.fragment_bottom_half,
                container, false);
    }
    // called immediately before fragment becomes active
    @Override
    public void onResume() {
        super.onResume();
        Log.d(tag,"onResume(): " + fragID);
    }
    // called when fragment or parent activity loses focus
    @Override
    public void onPause() {
        super.onPause();
        Log.d(tag,"onPause(): " + fragID);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag,"onDestroy(): " + fragID);
    }
}