package id.gwijaya94.androidbackgroundprocess

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat


object PermissionManager {
    fun check(activity: Activity, permission: String, requestCode: Int) {
        Log.i("ini", "check: ${ActivityCompat.checkSelfPermission(activity, permission)}")
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }else {
            Toast.makeText(activity,"Permission has been accepted",Toast.LENGTH_LONG).show()
        }
    }
}