package com.example.resapp;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

import com.example.resapp.model.AppDatabase;
import com.example.resapp.model.ReservationEntity;

import androidx.room.Room;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;




public class date extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_date);

        String username = getIntent().getStringExtra("username");

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "app_database"
        ).allowMainThreadQueries().build();

        EditText etDay = findViewById(R.id.etDay);
        EditText etMonth = findViewById(R.id.etMonth);
        EditText etYear = findViewById(R.id.etYear);
        EditText etHour = findViewById(R.id.etHour);
        EditText etMinute = findViewById(R.id.etMinute);
        Button btnBook = findViewById(R.id.btnBook);

        btnBook.setOnClickListener(v -> {

            String day = etDay.getText().toString().trim();
            String month = etMonth.getText().toString().trim();
            String year = etYear.getText().toString().trim();
            String hour = etHour.getText().toString().trim();
            String minute = etMinute.getText().toString().trim();

            if (day.isEmpty() || month.isEmpty() || year.isEmpty()
                    || hour.isEmpty() || minute.isEmpty()) {
                return;
            }

            String date = day + "/" + month + "/" + year;
            String time = hour + ":" + minute;

            ReservationEntity reservation =
                    new ReservationEntity(username, date, time);

            db.reservationDao().insert(reservation);

            Intent intent = new Intent(date.this, customer_rez_pg.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });

    }
}