package id.gwijaya94.androidbackgroundprocess

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.broadcastreceiver.BroadcastReceiverActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityMainBinding
import id.gwijaya94.androidbackgroundprocess.taskbuilder.TaskBuilderActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            toBgThread.setOnClickListener {
                val intent = Intent(this@MainActivity, BackgroundThreadActivity::class.java)
                startActivity(intent)
            }
            toServiceActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, ServiceActivity::class.java)
                startActivity(intent)
            }
            toBroadcastReceiver.setOnClickListener {
                val intent = Intent(this@MainActivity, BroadcastReceiverActivity::class.java)
                startActivity(intent)
            }
            toNotificationActivity.setOnClickListener{
                val intent = Intent(this@MainActivity, NotificationActivity::class.java)
                startActivity(intent)
            }
            toTaskBackstackActivity.setOnClickListener{
                val intent = Intent(this@MainActivity, TaskBuilderActivity::class.java)
                startActivity(intent)
            }

        }

    }
}