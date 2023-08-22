package com.example.windownotificationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int factNotificationId = 1;
    private final int hydrationNotificationId = 2;
    private String[] hydration_tips_title = {"Stay Hydrated!", "Time to Hydrate!", "Hydration Reminder", "Stay Refreshed! Hydration is Key.", "Remember to Hydrate", "Hydration Alert", "Water Break", "Hydration Check", "Quench Your Thirst"};
    private String[] hydration_tips_body = {"Drink Water.", "Take a Sip.", "Drink Some Water.", "Grab a Glass of Water.", "Drink Water Now.", "Your Body Needs Water.", "Keep Calm and Hydrate On!", "Rehydrate for a Healthy You.", "Drink Water, Feel Better."};
    private String[] facts_title = {"Did You Know?", "Fun Fact", "Fact of the Day", "Mind-blowing Fact", "Here's a Fact", "Fascinating Fact", "Fact Check", "Curious Fact", "Explore This Fact", "Unbelievable Fact"};
    private String[] facts_body = {"Honey Never Spoils!", "Octopuses Have Three Hearts.", "Bees Dance to Communicate.", "The Earth is Not a Perfect Sphere.", "Cows Have Best Friends.", "A Day on Venus is Longer Than Its Year.", "Chameleons' Tongues Are Longer Than Their Bodies.", "Bananas Are Berries, but Strawberries Aren't.", "A Group of Flamingos Is Called a 'Flamboyance'."};
    private Random random;
    private NotificationHelper helper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.green));

        random = new Random();
        helper = new NotificationHelper(getApplicationContext());

    }

    public void getFactNotification(View view) {
        sendOnNotificationChannel(facts_title[random.nextInt(facts_title.length)], facts_body[random.nextInt(facts_body.length)], factNotificationId);
    }

    public void getHydrationNotification(View view) {
        sendOnNotificationChannel(hydration_tips_title[random.nextInt(hydration_tips_title.length)], hydration_tips_body[random.nextInt(hydration_tips_body.length)], hydrationNotificationId);
    }

    private void sendOnNotificationChannel(String title, String body, int id) {

        MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.ting);
        NotificationCompat.Builder builder = null;
        if(id == factNotificationId){
            try{
                builder = helper.getNotificationChannel(title, body, R.color.fuchsia, NotificationHelper.fact_notification_channel_ID, R.drawable.ic_baseline_fact_check_24, factNotificationId);
            }catch (Exception exception){
                exception.getStackTrace();
            }
        }else if (id == hydrationNotificationId){
            try{
                builder = helper.getNotificationChannel(title, body, R.color.blue, NotificationHelper.hydration_notification_channel_ID, R.drawable.ic_baseline_local_drink_24, hydrationNotificationId);
            }catch (Exception exception){
                exception.getStackTrace();
            }
        }
        player.start();
        helper.getNotificationManager().notify(id, builder.build());
    }

}