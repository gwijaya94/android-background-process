package id.gwijaya94.androidbackgroundprocess

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityServiceBinding
import id.gwijaya94.androidbackgroundprocess.services.MyJobIntentService
import id.gwijaya94.androidbackgroundprocess.services.MyService

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
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
        binding.btnStartBoundService.setOnClickListener { }
    }
}