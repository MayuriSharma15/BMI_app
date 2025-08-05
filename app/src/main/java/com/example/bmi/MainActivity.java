package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editweight, editheightFT, editheightIN;
    Button btn;
    TextView textresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editweight = findViewById(R.id.editweight);
        editheightFT = findViewById(R.id.editheightFT);
        editheightIN = findViewById(R.id.editheightIN);
        btn = findViewById(R.id.btn);
        textresult = findViewById(R.id.textresult);

        // ✅ Correct click listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Validate input
                String weightStr = editweight.getText().toString();
                String heightFtStr = editheightFT.getText().toString();
                String heightInStr = editheightIN.getText().toString();

                if (weightStr.isEmpty() || heightFtStr.isEmpty() || heightInStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }

                // Parse input
                int wt = Integer.parseInt(weightStr);
                int ft = Integer.parseInt(heightFtStr);
                int in = Integer.parseInt(heightInStr);

                // Convert height to meters
                int totalIn = ft * 12 + in;
                double totalCm = totalIn * 2.54;
                double totalM = totalCm / 100;

                // Calculate BMI
                double bmi = wt / (totalM * totalM);

                // Show BMI result
                if (bmi < 18.5) {
                    textresult.setText("Underweight\nBMI: " + String.format("%.2f", bmi));
                } else if (bmi < 24.9) {
                    textresult.setText("Normal weight\nBMI: " + String.format("%.2f", bmi));
                } else if (bmi < 29.9) {
                    textresult.setText("Overweight\nBMI: " + String.format("%.2f", bmi));
                } else {
                    textresult.setText("Obese\nBMI: " + String.format("%.2f", bmi));
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
