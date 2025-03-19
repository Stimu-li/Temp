package com.example.mad_lab_6;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView countdownText;
    private Button showButton;
    private int countdownValue = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        countdownText = findViewById(R.id.countdownText);
        showButton = findViewById(R.id.showButton);

        // Start the countdown on a separate thread
        startCountdown();

        // Set up button click listener to show a toast when clicked
        showButton.setOnClickListener(v -> {
            // Show a toast when the button is clicked
            Toast.makeText(MainActivity.this, "Button Clicked!", Toast.LENGTH_SHORT).show();
        });
    }

    private void startCountdown() {
        // Create a Handler to update the UI
        Handler handler = new Handler();

        // Create a new thread for the countdown
        Thread countdownThread = new Thread(() -> {
            while (countdownValue > 0) {
                try {
                    // Wait for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Update the countdown value
                countdownValue--;

                // Post the UI update to the main thread using Handler
                handler.post(() -> countdownText.setText(String.valueOf(countdownValue)));
            }

            // Once countdown is complete, show the button
            handler.post(() -> showButton.setVisibility(View.VISIBLE));
        });

        // Start the countdown thread
        countdownThread.start();
    }
}
