package ca.mohawk.Andy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Input extends Fragment {
    public static String tag = "==Input==";
    private static int fragCount = 0;
    private int fragID;

    private TextView tView;
    private int myChoice;
    Input(TextView tView, int myChoice) {
        this.tView = tView;
        this.myChoice = myChoice;
    }
    public Input() {
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
        return inflater.inflate(R.layout.fragment_input,
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

    public void myAction(View view) {
        int myNum = Integer.valueOf(tView.getText().toString());
        if (myChoice == 0) {
            myNum += 1;
        } else if (myChoice == 1) {
            myNum += 2;
        } else if (myChoice == 2) {
            myNum -= 1;
        } else if (myChoice == 3) {
            myNum -= 2;
        }
        String temp = String.valueOf(myNum);
        tView.setText(temp);
    }

}