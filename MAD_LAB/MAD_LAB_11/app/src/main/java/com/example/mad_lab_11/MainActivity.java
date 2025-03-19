package com.example.mad_lab_11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText emailInput, subjectInput, messageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.email_input);
        subjectInput = findViewById(R.id.subject_input);
        messageInput = findViewById(R.id.message_input);
    }

    public void sendEmail(View view) {
        String email = emailInput.getText().toString().trim();
        String subject = subjectInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        if (email.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Constructing mailto URI properly
        Uri mailtoUri = Uri.parse("mailto:" + email +
                "?subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(message));

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, mailtoUri);

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            Toast.makeText(this, "No email client found", Toast.LENGTH_SHORT).show();
        }
    }
}
