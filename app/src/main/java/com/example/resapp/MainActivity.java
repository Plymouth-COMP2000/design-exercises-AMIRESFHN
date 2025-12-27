package com.example.resapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.resapp.model.ReadUserResponse;
import com.example.resapp.model.User;
import com.example.resapp.api.ApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String STUDENT_ID = "student_amiresi10778083";

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnDone = findViewById(R.id.btnDone);
        TextView tvForgot = findViewById(R.id.tvForgot);
        ImageButton btnBack = findViewById(R.id.btnBack);
        TextView tvSignupLink = findViewById(R.id.tvSignupLink);



        btnBack.setOnClickListener(v -> finish());

        

        tvSignupLink.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, signup.class));
        });



        tvForgot.setOnClickListener(v -> {
            // Example:
            startActivity(new Intent(MainActivity.this, forgotpass.class));
        });

        btnDone.setOnClickListener(v -> login());
    }

    private void login() {
        String username = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter username/email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.getApi().readUser(STUDENT_ID, username).enqueue(new Callback<ReadUserResponse>() {
            @Override
            public void onResponse(Call<ReadUserResponse> call, Response<ReadUserResponse> response) {

                if (!response.isSuccessful() || response.body() == null || response.body().user == null) {
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                User u = response.body().user;

                if (u.password == null || !u.password.equals(password)) {
                    Toast.makeText(MainActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("staff".equalsIgnoreCase(u.usertype)) {
                    startActivity(new Intent(MainActivity.this, staff_dash.class));
                } else if ("customer".equalsIgnoreCase(u.usertype)) {
                    // Pass customer details to cusmenu
                    Intent intent = new Intent(MainActivity.this, cusmenu.class);
                    intent.putExtra("firstname", u.firstname);
                    intent.putExtra("lastname", u.lastname);
                    intent.putExtra("email", u.email);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Unknown user type", Toast.LENGTH_SHORT).show();
                    return;
                }

                finish();
            }

            @Override
            public void onFailure(Call<ReadUserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
