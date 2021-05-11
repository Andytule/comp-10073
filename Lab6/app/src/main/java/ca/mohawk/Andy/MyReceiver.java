package ca.mohawk.Andy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import static android.provider.Telephony.Sms.Intents.getMessagesFromIntent;

public class MyReceiver extends BroadcastReceiver {
    public static String tag = "==MyReceiver==";
    public static String MSG = "MSG";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(tag, "onReceive");
        // Pull the SmsMessage information from the intent
        // This is an array of messages
        SmsMessage[] messages = getMessagesFromIntent(intent);
        // Get the sender from the first message block
        String sender = messages[0].getOriginatingAddress();
        // Get the first message body text -
        String msg = messages[0].getMessageBody();
        // Log the message
        Log.d(tag, sender + " : " + msg);
        // Prepare to launch the main activity
        Intent myIntent = new Intent(context,
                MainActivity.class);
        myIntent.putExtra(MSG, msg);
        // Set the New Activity Flags
        myIntent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK );

        context.startActivity(myIntent);
    }
}

