package com.example.project_madd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Period_Notification_reciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeatingIntent = new Intent(context,PeriodReminder.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,101,repeatingIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Notification Title")
                .setContentText("Notification text")
                .setAutoCancel(true);

        notificationManager.notify(101,builder.build());

    }
}
