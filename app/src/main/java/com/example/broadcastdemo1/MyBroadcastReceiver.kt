package com.example.broadcastdemo1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver  : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val modeChanged = intent?.getBooleanExtra("state",false) ?: return

        if(modeChanged){
            Toast.makeText(context,"Mode ON",Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context,"MODE OFF",Toast.LENGTH_LONG).show()
        }
    }
}