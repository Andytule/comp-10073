
package ca.mohawk.le;

/*
I, Andy Le, 000805099 certify that this material is my original work.
No other person's work has been used without due
acknowledgement.
https://youtu.be/iDTJNqskBf4
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myOutput = findViewById(R.id.output);
        myOutput.setText("");

        EditText myInput = findViewById(R.id.input);
        myInput.setText("");
    }

    public void doStuff(View view) {
        EditText myInput = findViewById(R.id.input);
        int inputValue = Integer.parseInt("0" + myInput.getText().toString());
        Random rand = new Random();
        TextView myOutput = findViewById(R.id.output);

        if (0 < inputValue) {
            String output = String.valueOf(rand.nextInt(inputValue) + 1);
            myOutput.setText(output);
        } else {
            myOutput.setText("");
        }
    }
}