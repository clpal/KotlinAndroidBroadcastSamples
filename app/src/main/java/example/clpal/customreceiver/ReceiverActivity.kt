package example.clpal.customreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// Annyomous class ReceiverActivity
class ReceiverActivity : AppCompatActivity() {
    private lateinit var revMsg:TextView
    private lateinit var intentFilter:IntentFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       revMsg=findViewById(R.id.revMsg)
       intentFilter= IntentFilter("example.clpal.customsender.SENDER_DATA")
    registerReceiver(innerBroadcast,intentFilter)
    }
    // Annyomous class
    private  val innerBroadcast:BroadcastReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if ("example.clpal.customsender.SENDER_DATA".equals(intent?.action)){
                val  textmsg=intent?.getStringExtra("senderMessage")
                revMsg.text="From Sender: "+textmsg.toString()
            }
        }

    }
    fun  send(view: View):Unit{
            val intent =Intent("example.clpal.customreceiver.REC_DATA")
            intent.putExtra("receiverMessage","hi....")
            sendBroadcast(intent)
        }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(innerBroadcast)
    }
// inner class
/*companion object{
        private class MyReciver :BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {


           }

        }
    }*/
}