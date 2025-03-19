package com.example.mad_lab_8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button writeButton = findViewById(R.id.writeButton);

        // Button click to write data to SD card (app-specific directory)
        writeButton.setOnClickListener(v -> writeDataToSDCard());
    }

    private void writeDataToSDCard() {
        // Get the app-specific directory on external storage
        File dir = getExternalFilesDir(null); // this returns a directory where app data is stored
        if (dir != null) {
            File file = new File(dir, "examplefile.txt");

            try (FileOutputStream fos = new FileOutputStream(file)) {
                String data = "This is some sample data to be written to the SD card. Hello World!";
                fos.write(data.getBytes());
                fos.flush();
                Toast.makeText(this, "Data written to SD card!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error writing to SD card", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "External storage not available", Toast.LENGTH_SHORT).show();
        }
    }
}
