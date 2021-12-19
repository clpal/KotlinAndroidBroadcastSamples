package example.clpal.customsender

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SenderActivity : AppCompatActivity() {
   private lateinit var editText:EditText
    private lateinit var intentFilter:IntentFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText= findViewById(R.id.editText)
        intentFilter= IntentFilter("example.clpal.customreceiver.REC_DATA")
        registerReceiver(innerBroadcast,intentFilter)
    }
    // Annyomous class
    private  val innerBroadcast: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if ("example.clpal.customreceiver.REC_DATA".equals(intent?.action)){
                val  textmsg=intent?.getStringExtra("receiverMessage")

                Toast.makeText(applicationContext,"From Receiver "+textmsg.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }
  fun  sendMessage(view:View):Unit{
      if(!TextUtils.isEmpty(editText.text)){
          val intent =Intent("example.clpal.customsender.SENDER_DATA")
          intent.putExtra("senderMessage",editText.text.toString())
          sendBroadcast(intent)
      }

  }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(innerBroadcast)
    }
}