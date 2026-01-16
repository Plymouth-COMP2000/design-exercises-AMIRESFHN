package com.example.resapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.content.Intent;
import androidx.room.Room;
import com.example.resapp.model.AppDatabase;
import com.example.resapp.model.ReservationEntity;
import java.util.List;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;




public class customer_rez_pg extends AppCompatActivity {
    private AppDatabase db;
    private RecyclerView rvCustomerReservations;
    private CustomerReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_rez_pg);

        String username = getIntent().getStringExtra("username");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "app_database"
        ).allowMainThreadQueries().build();

        List<ReservationEntity> reservations = db.reservationDao().getReservationsForUser(username);

        // 1. SET UP RECYCLERVIEW
        RecyclerView rvReservations = findViewById(R.id.rvCustomerReservations);
        rvReservations.setLayoutManager(new LinearLayoutManager(this));
        CustomerReservationAdapter adapter = new CustomerReservationAdapter(reservations, db);
        rvReservations.setAdapter(adapter);

        // 2. Book Button
        Button btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(customer_rez_pg.this, date.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        // 3. Bottom Bar Buttons
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(customer_rez_pg.this, cusmenu.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });
        ImageButton btnCalendar = findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(customer_rez_pg.this, customer_rez_pg.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });
    }

}