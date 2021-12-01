package id.gwijaya94.androidbackgroundprocess

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityMainBinding

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
        }

    }
}