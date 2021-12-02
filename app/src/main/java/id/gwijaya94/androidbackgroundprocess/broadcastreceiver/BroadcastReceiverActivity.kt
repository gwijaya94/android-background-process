package id.gwijaya94.androidbackgroundprocess.broadcastreceiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.gwijaya94.androidbackgroundprocess.PermissionManager
import id.gwijaya94.androidbackgroundprocess.R
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
            R.id.btn_permission -> PermissionManager.check(
                this,
                Manifest.permission.RECEIVE_SMS,
                SMS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_REQUEST_CODE) {
            when {
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> Toast.makeText(
                    this,
                    "Sms receiver permission diterima",
                    Toast.LENGTH_SHORT
                ).show()
                else -> Toast.makeText(this, "Sms receiver permission ditolak", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val SMS_REQUEST_CODE = 101
        const val INI_TEXT = "avc"
    }
}