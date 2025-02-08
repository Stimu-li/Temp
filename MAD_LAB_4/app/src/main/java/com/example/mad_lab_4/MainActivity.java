package com.example.mad_lab_4;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText etName, etAge;
    Button btnSave, btnView;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        tvResult = findViewById(R.id.tvResult);

        // Save data
        btnSave.setOnClickListener(view -> {
            String name = etName.getText().toString();
            String ageText = etAge.getText().toString();

            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                int age = Integer.parseInt(ageText);
                boolean isInserted = dbHelper.insertData(name, age);
                if (isInserted) {
                    Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // View data
        btnView.setOnClickListener(view -> {
            Cursor cursor = dbHelper.getAllData();
            if (cursor.getCount() == 0) {
                tvResult.setText("No data found");
                return;
            }

            StringBuilder builder = new StringBuilder();
            while (cursor.moveToNext()) {
                builder.append("ID: ").append(cursor.getInt(0)).append("\n");
                builder.append("Name: ").append(cursor.getString(1)).append("\n");
                builder.append("Age: ").append(cursor.getInt(2)).append("\n\n");
            }
            tvResult.setText(builder.toString());
        });
    }
}
