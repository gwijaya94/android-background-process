package id.gwijaya94.androidbackgroundprocess.taskbuilder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityDetailTaskBinding

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        binding.tvTitle.text = title
        binding.tvMessage.text = message
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MESSAGE = "extra_message"
    }
}