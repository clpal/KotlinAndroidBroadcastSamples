package example.clpal.dyamicbroadcastreciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.action)) {
                Toast.makeText(context, "Airplan mode Changed", Toast.LENGTH_LONG).show()
            } else if (Intent.ACTION_TIME_TICK.equals(intent.action)) {
                Toast.makeText(context, "Time Changed", Toast.LENGTH_LONG).show()
            } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.action)) {
                val state = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                if (!state) {
                    Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Internet DisConnected", Toast.LENGTH_LONG).show()
                }

            }

        }
    }
}