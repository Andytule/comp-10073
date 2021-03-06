package ca.mohawk.lab10;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadImageTask
        extends AsyncTask<String, Void, Bitmap> {
    public static String TAG = "==DownloadImageTask==";
    public static int HTTP_OK = 200;

    private static ArrayList<Bitmap> bmpList = new ArrayList<Bitmap>();

    public static int imageIndex = 0;

    /**
     * Download an image as a background task
     * @param urls - expect the first string to be a URL of an image
     * @return null on failure, a bit mapped image otherwise
     */
    protected Bitmap doInBackground(String... urls) {
        // Use the URL Connection interface to download the URL
        Bitmap bmp = null;
        Log.d(TAG, "do background " + urls[0]);
        // URL connection must be done in a try/catch
        int statusCode = -1;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            statusCode = conn.getResponseCode();
            if (statusCode == HTTP_OK) {
                bmp = BitmapFactory.decodeStream(conn.getInputStream());
                bmpList.add(bmp);
            }
        } catch (MalformedURLException e) {
            Log.d(TAG, "bad URL " + e);
        } catch (IOException e) {
            Log.d(TAG, "bad I/O " + e);
        }
        Log.d(TAG, "done " + statusCode);
        return bmp;
    }

    /**
     * after downloading is complete display the image
     * @param result - a bitmap image, null will skip the routine
     */
    protected void onPostExecute(Bitmap result) {
        Log.d(TAG,"onPostExecute()");
        if (result != null) {
            imageIndex = 0;
            // Find the ImageView object to use
            Activity main = MainActivity.getMainActivity();
            ImageView imageView = (ImageView) main.findViewById(R.id.imageView);
            imageView.setImageBitmap(result);
            imageView.setOnClickListener(this::onImageClick);
        }
    }

    public void onImageClick(View v) {
        Activity main = MainActivity.getMainActivity();
        ImageView imageView = (ImageView) main.findViewById(R.id.imageView);
        imageIndex++;
        if (imageIndex == bmpList.size()) {
            imageIndex = 0;
        }
        imageView.setImageBitmap(bmpList.get(imageIndex));
    }


}
