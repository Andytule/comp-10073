package ca.mohawk.Andy;

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

public class CustomDialog extends DialogFragment implements View.OnClickListener {

    public static String tag = "==DialogFragment";

    public CustomDialog() {
        Log.d(tag, "construction");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custom_dialog, container, false);

        Log.d(tag, "onCreateView, myview = " + v);

        Button button = v.findViewById(R.id.okay);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Log.d(tag, "onClick");

        EditText userInput = getView().findViewById(R.id.username);
        String user = userInput.getText().toString();

        EditText passInput = getView().findViewById(R.id.password);
        String pass = passInput.getText().toString();

        // Send the string to the main activity -
        // call a method in the main activity class
        MainActivity main = (MainActivity) getActivity();
        if (main instanceof MainActivity) {
            main.sendInput(user, pass);
        }

        // Dismiss will close the dialog
        dismiss();
    }
}