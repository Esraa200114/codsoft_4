package com.example.windownotificationapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String fact_notification_channel_ID = "FNC";
    public static final String fact_notification_channel_Name = "Fact_Notification_Channel";

    public static final String hydration_notification_channel_ID = "HNC";
    public static final String hydration_notification_channel_Name = "Hydration_Notification_Channel";

    private NotificationManager notification_manager;

    public NotificationHelper(Context base) {
        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannels() {

        NotificationChannel fact_notification_channel = new NotificationChannel(fact_notification_channel_ID, fact_notification_channel_Name, NotificationManager.IMPORTANCE_DEFAULT);
        fact_notification_channel.enableLights(true);
        fact_notification_channel.enableVibration(true);
        fact_notification_channel.setLightColor(R.color.fuchsia);
        fact_notification_channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getNotificationManager().createNotificationChannel(fact_notification_channel);

        NotificationChannel hydration_notification_channel = new NotificationChannel(hydration_notification_channel_ID, hydration_notification_channel_Name, NotificationManager.IMPORTANCE_DEFAULT);
        hydration_notification_channel.enableLights(true);
        hydration_notification_channel.enableVibration(true);
        hydration_notification_channel.setLightColor(R.color.blue);
        hydration_notification_channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getNotificationManager().createNotificationChannel(hydration_notification_channel);
    }

    public NotificationManager getNotificationManager(){

        if(notification_manager == null){
            notification_manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notification_manager;
    }

    public NotificationCompat.Builder getNotificationChannel(String title, String body, int color_id, String notification_channel_id, int drawable_id, int notification_id) {

        RemoteViews customNotificationView = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
        customNotificationView.setTextViewText(R.id.title, title);
        customNotificationView.setTextViewText(R.id.message, body);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pending_intent = PendingIntent.getActivity(this, notification_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent dismiss_intent = new Intent(this, DismissNotificationReceiver.class);
        dismiss_intent.setAction("DISMISS_NOTIFICATION");
        dismiss_intent.putExtra("notification_id", notification_id);
        PendingIntent dismiss_pending_intent = PendingIntent.getBroadcast(this, notification_id, dismiss_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), notification_channel_id)
                .setSmallIcon(drawable_id)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setContentIntent(pending_intent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "View", pending_intent)
                .addAction(R.mipmap.ic_launcher, "Dismiss", dismiss_pending_intent)
                .setColor(getResources().getColor(color_id))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCustomContentView(customNotificationView);
    }
}
