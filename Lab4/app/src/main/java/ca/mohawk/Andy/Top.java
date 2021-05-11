package ca.mohawk.Andy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Top extends Fragment implements View.OnClickListener {

    public static String TAG = "==Top==";

    public Top() {
        Log.d(TAG, "constructor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);

        Button smallClick = v.findViewById(R.id.smallButton);
        smallClick.setOnClickListener(this);

        Button mediumClick = v.findViewById(R.id.mediumButton);
        mediumClick.setOnClickListener(this);

        Button largeClick = v.findViewById(R.id.largeButton);
        largeClick.setOnClickListener(this);

        Log.d(TAG, "onCreateView");
        return v;
    }

    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (v.getId() == R.id.smallButton) {
            fragmentTransaction.replace(R.id.frame2, new Small());
        } else if (v.getId() == R.id.mediumButton) {
            fragmentTransaction.replace(R.id.frame2, new Medium());
        } else {
            fragmentTransaction.replace(R.id.frame2, new Large());
        }

        fragmentTransaction.commit();
        Log.d(TAG, "onClick");
    }
}