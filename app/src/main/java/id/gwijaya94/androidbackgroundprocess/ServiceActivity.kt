package id.gwijaya94.androidbackgroundprocess

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityServiceBinding
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
        binding.btnStartJobIntentService.setOnClickListener { }
        binding.btnStartBoundService.setOnClickListener { }
    }
}