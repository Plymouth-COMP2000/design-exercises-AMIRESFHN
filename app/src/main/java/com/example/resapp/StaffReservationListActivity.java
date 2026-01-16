package com.example.resapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.resapp.model.AppDatabase;
import com.example.resapp.model.ReservationEntity;
import androidx.room.Room;
import java.util.List;
import android.widget.ImageButton;



public class StaffReservationListActivity extends AppCompatActivity {
    private AppDatabase db;
    private RecyclerView rvReservations;
    private ReservationAdapter reservationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        rvReservations = findViewById(R.id.rvReservations);
        rvReservations.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database")
                .allowMainThreadQueries().build();

        List<ReservationEntity> reservations = db.reservationDao().getAllReservations();
        reservationAdapter = new ReservationAdapter(reservations, db);
        rvReservations.setAdapter(reservationAdapter);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}
