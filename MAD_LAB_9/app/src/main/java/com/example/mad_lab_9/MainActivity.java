package com.example.mad_lab_9;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private EditText phoneNumber, messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phoneNumber);
        messageText = findViewById(R.id.messageText);
        Button sendButton = findViewById(R.id.sendButton);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        sendButton.setOnClickListener(v -> sendSMS());
    }

    private void sendSMS() {
        String phoneNo = phoneNumber.getText().toString().trim();
        String message = messageText.getText().toString().trim();

        if (phoneNo.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SmsManager.getDefault().sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS failed, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
