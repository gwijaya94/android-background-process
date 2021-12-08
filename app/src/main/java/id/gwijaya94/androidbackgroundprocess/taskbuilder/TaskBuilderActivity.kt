package id.gwijaya94.androidbackgroundprocess.taskbuilder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import id.gwijaya94.androidbackgroundprocess.R
import id.gwijaya94.androidbackgroundprocess.databinding.ActivityTaskBuilderBinding

class TaskBuilderActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTaskBuilderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBuilderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenDetail.setOnClickListener(this)
        showNotification(
            this,
            getString(R.string.notification_title),
            getString(R.string.notification_message),
            110
        )
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_open_detail) {
            val detailIntent = Intent(this, DetailTaskActivity::class.java)
            detailIntent.putExtra(
                DetailTaskActivity.EXTRA_TITLE, getString(R.string.detail_title)
            )
            detailIntent.putExtra(
                DetailTaskActivity.EXTRA_MESSAGE, getString(R.string.detail_message)
            )
            startActivity(detailIntent)
        }
    }

    fun showNotification(context: Context, title: String, message: String, notifId: Int) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Navigation channel"
        val notifDetailIntent = Intent(this, DetailTaskActivity::class.java)
        notifDetailIntent.putExtra(DetailTaskActivity.EXTRA_TITLE, title)
        notifDetailIntent.putExtra(DetailTaskActivity.EXTRA_MESSAGE, message)
        val pendingIntent = TaskStackBuilder.create(this)
            .addParentStack(DetailTaskActivity::class.java)
            .addNextIntent(notifDetailIntent)
            .getPendingIntent(110, PendingIntent.FLAG_MUTABLE)
        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        with(notificationManagerCompat) {
            notify(notifId, notification)
        }
    }
}