package com.example.broadcastdemo1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var myBroadcastReceiver: MyBroadcastReceiver? = null

    var brDownload = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.txtPath.setText(
                intent?.getStringExtra("path")
            )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //myBroadcastReceiver = MyBroadcastReceiver()

       /* IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(myBroadcastReceiver, it)
        } */

        binding.btnRegisterDownload.setOnClickListener {
            registerReceiver(brDownload, IntentFilter("in.bitcode.DOWNLOAD_COMPLETE"))
        }

        binding.btnRegister.setOnClickListener {
            myBroadcastReceiver = MyBroadcastReceiver()
            var intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED)
            intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
            intentFilter.addAction(Intent.ACTION_WALLPAPER_CHANGED)
            intentFilter.addAction("in.bitcode.DOWNLOAD_COMPLETE")

            registerReceiver(myBroadcastReceiver,intentFilter)
        }

        binding.btnUnregister.setOnClickListener {
            unregisterReceiver(myBroadcastReceiver)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(myBroadcastReceiver)
        super.onDestroy()
    }
    /*override fun onStop(){
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
    }
     */
}