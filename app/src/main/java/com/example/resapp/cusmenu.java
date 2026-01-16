package com.example.resapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.content.Intent;
import androidx.room.Room;
import com.example.resapp.model.MenuItemEntity;
import android.widget.Button;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resapp.model.MenuItem;
import com.example.resapp.model.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;



public class cusmenu extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView rvMenu;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuList = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cusmenu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cusmenu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final String username = getIntent().getStringExtra("firstname");
        final String email = getIntent().getStringExtra("email");


        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "app_database"
        ).allowMainThreadQueries().build();

        rvMenu = findViewById(R.id.rvCustomerMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));


        List<MenuItemEntity> dbItems = db.menuItemDao().getAll();
        menuList.clear();
        for (MenuItemEntity entity : dbItems) {
            menuList.add(new MenuItem(entity.name, entity.price));
        }

        Log.d("DEBUG", "Loaded menu size: " + menuList.size());
        for (MenuItem item : menuList) {
            Log.d("DEBUG", "Menu: " + item.name + " - " + item.price);
        }

        Button btnMakeReservation = findViewById(R.id.btnMakeReservation);
        btnMakeReservation.setOnClickListener(v -> {
            Intent intent = new Intent(cusmenu.this, date.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });



        menuAdapter = new MenuAdapter(menuList, null);
        rvMenu.setAdapter(menuAdapter);


        TextView tvSignOut = findViewById(R.id.tvSignOut);
        tvSignOut.setOnClickListener(v -> {
            Intent intent = new Intent(cusmenu.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

}