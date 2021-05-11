package ca.mohawk.Andy;

/*
I, Andy Le 000805099 certify that this material is my original work. No
other person's work has been used without due acknowledgement.
https://youtu.be/AQsv_6pN-fg
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String tag = "==MainActivity==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            CustomDialog myDialog = new CustomDialog();
            myDialog.show(getSupportFragmentManager(), null);
        }
    }

    public void sendInput(String user, String pass) {
        Button myButton = findViewById(R.id.login);
        myButton.setVisibility(View.INVISIBLE);

        TextView username = findViewById(R.id.user_output);
        username.setVisibility(View.VISIBLE);
        String user_out = "User Name: " + user;
        username.setText(user_out);

        TextView password = findViewById(R.id.password_output);
        password.setVisibility(View.VISIBLE);
        String pass_out = "Password: " + pass;
        password.setText(pass_out);
    }
}