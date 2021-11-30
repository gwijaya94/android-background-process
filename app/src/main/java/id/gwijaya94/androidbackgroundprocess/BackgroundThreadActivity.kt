package id.gwijaya94.androidbackgroundprocess

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityBackgroundThreadBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class BackgroundThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBackgroundThreadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackgroundThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        binding.btnStart.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                //simulate process in background thread
                for (i in 0..10) {
                    delay(500)
                    val percentage = i * 10
                    withContext(Dispatchers.Main) {
                        //update ui in main thread
                        if (percentage == 100) {
                            binding.tvStatus.setText(R.string.task_completed)
                        } else {
                            binding.tvStatus.text =
                                String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }
//        binding.btnStart.setOnClickListener {
//            executor.execute {
//                try {
//                    //simulate process compressing
//                    for (i in 0..10) {
//                        Thread.sleep(500)
//                        val percentage = i * 10
//
//                        handler.post {
//                            if (percentage == 100) {
//                                binding.tvStatus.text = getString(R.string.task_completed)
//                            } else {
//                                binding.tvStatus.text =
//                                    String.format(getString(R.string.compressing), percentage)
//                            }
//                        }
//                    }
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//        }
    }
}