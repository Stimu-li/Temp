package com.example.mad_lab_5;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "alarm_channel";
    private EditText secondsInput;
    private Button setAlarmButton;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        secondsInput = findViewById(R.id.seconds_input);
        setAlarmButton = findViewById(R.id.set_alarm_button);

        // Set up handler
        handler = new Handler();

        // Create a notification channel (needed for Android 8.0 and above)
        createNotificationChannel();

        // Button click listener
        setAlarmButton.setOnClickListener(v -> {
            String secondsStr = secondsInput.getText().toString();
            if (!secondsStr.isEmpty()) {
                int seconds = Integer.parseInt(secondsStr);
                Toast.makeText(MainActivity.this, "Alarm Set for " + seconds + " seconds", Toast.LENGTH_SHORT).show();
                startAlarm(seconds);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a number of seconds", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startAlarm(int seconds) {
        // Delay the alarm by the entered number of seconds
        handler.postDelayed(this::showToastAndNotification, seconds * 1000L);
    }

    private void showToastAndNotification() {
        // Show a Toast message
        Toast.makeText(MainActivity.this, "Alarm triggered!", Toast.LENGTH_SHORT).show();

        // Show a Notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setContentTitle("Alarm")
                .setContentText("Your alarm has been triggered!")
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .build();

        // Display the notification
        notificationManager.notify(1, notification);
    }

    private void createNotificationChannel() {
        // Notification Channel for Android 8.0 and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Alarm Channel";
            String description = "Channel for alarm notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
