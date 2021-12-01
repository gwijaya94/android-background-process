package id.gwijaya94.androidbackgroundprocess

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityServiceBinding
import id.gwijaya94.androidbackgroundprocess.services.MyBoundService
import id.gwijaya94.androidbackgroundprocess.services.MyJobIntentService
import id.gwijaya94.androidbackgroundprocess.services.MyService

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding

    private var mServiceBound = false
    private lateinit var mBoundService: MyBoundService
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as MyBoundService.MyBinder
            mBoundService = myBinder.getService
            mServiceBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        title = "Service Activity"
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener {
            val mStartService = Intent(this, MyService::class.java)
            startService(mStartService)
        }
        binding.btnStartJobIntentService.setOnClickListener {
            val mStartIntentService = Intent(this, MyJobIntentService::class.java)
            mStartIntentService.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
            MyJobIntentService.enqueueWork(this, mStartIntentService)
        }
        binding.btnStartBoundService.setOnClickListener {
            val mBoundServiceIntent = Intent(this, MyBoundService::class.java)
            bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE)
        }
        binding.btnStopBoundService.setOnClickListener {
            unbindService(mServiceConnection)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mServiceBound) {
            unbindService(mServiceConnection)
        }
    }
}