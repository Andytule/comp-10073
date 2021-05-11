package ca.mohawk.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BlankFragment extends Fragment {

    public static String TAG = "==BlankFragment==";

    public BlankFragment() {
        Log.d(TAG, "constructor");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        startDownload(null);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        startDownload(null);
    }

    public void startDownload(View v) {
        DownloadAsyncTask dl = new DownloadAsyncTask();

        // Build call to Webservice
        String uri = "https://csunix.mohawkcollege.ca/~geczy/mohawkprograms.php";
        Log.d(TAG, "startDownload " + uri);
        dl.execute(uri);
    }
}