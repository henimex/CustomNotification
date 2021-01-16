package com.hendev.notificationapp


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBildir.setOnClickListener {
            val builder:NotificationCompat.Builder
            val bildirimYoneticisi = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this,MainActivity::class.java)
            val gidilecekIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                val kanalId = "kanalId"
                val kanalAdı = "kanalAdı"
                val kanalTanitim = "kanalTanitim"
                val kanalOnceligi = NotificationManager.IMPORTANCE_HIGH

                var kanal : NotificationChannel? = bildirimYoneticisi.getNotificationChannel(kanalId)

                if(kanal == null){
                    kanal = NotificationChannel(kanalId,kanalAdı,kanalOnceligi)
                    kanal.description = kanalTanitim
                    bildirimYoneticisi.createNotificationChannel(kanal)
                }

                builder=NotificationCompat.Builder(this,kanalId)
                builder.setContentTitle("Başlık")
                        .setContentText("İçerik")
                        .setSmallIcon(R.drawable.picture1)
                        .setContentIntent(gidilecekIntent)
                        .setAutoCancel(true)
                        .priority = Notification.PRIORITY_HIGH
            }else{

                builder=NotificationCompat.Builder(this)
                builder.setContentTitle("Başlık")
                        .setContentText("İçerik")
                        .setSmallIcon(R.drawable.picture1)
                        .setContentIntent(gidilecekIntent)
                        .setAutoCancel(true)
                        .priority = Notification.PRIORITY_HIGH
            }

            bildirimYoneticisi.notify(1,builder.build())
        }
    }
}