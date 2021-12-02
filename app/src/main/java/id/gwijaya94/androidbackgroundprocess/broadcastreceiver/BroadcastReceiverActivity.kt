package id.gwijaya94.androidbackgroundprocess.broadcastreceiver

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityBroadcastReceiverBinding

class BroadcastReceiverActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityBroadcastReceiverBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastReceiverBinding.inflate(layoutInflater)
        title = "Broadcast Receiver"
        setContentView(binding?.root)

        binding?.btnPermission?.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}