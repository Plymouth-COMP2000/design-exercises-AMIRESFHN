package com.example.resapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.resapp.model.MenuItem;
import java.util.ArrayList;
import java.util.List;

import androidx.activity.OnBackPressedCallback;
import androidx.room.Room;
import com.example.resapp.model.AppDatabase;
import com.example.resapp.model.MenuItemEntity;
import android.util.Log;





public class foodmenu extends AppCompatActivity {
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuList = new ArrayList<>();
    private AppDatabase db;

    private MenuItem entityToMenuItem(MenuItemEntity entity) {
        return new MenuItem(entity.name, entity.price);
    }

    private void saveMenuChanges() {

        db.menuItemDao().deleteAll();

        for (MenuItem item : menuList) {
            if (!item.name.trim().isEmpty() && item.price > 0) {
                db.menuItemDao().insert(
                        new MenuItemEntity(item.name, item.price)
                );
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foodmenu);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app_database").allowMainThreadQueries().build();


        menuList.clear();
        List<MenuItemEntity> entities = db.menuItemDao().getAll();
        for (MenuItemEntity entity : entities) {
            menuList.add(entityToMenuItem(entity));
        }
        Log.d("DEBUG", "Loaded menu size: " + menuList.size());
        for (MenuItem item : menuList) {
            Log.d("DEBUG", "Menu: " + item.name + " - " + item.price);
        }




        findViewById(R.id.btnBack).setOnClickListener(v -> {
            saveMenuChanges();
            finish();});
        RecyclerView rvMenu = findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));


        menuAdapter = new MenuAdapter(menuList, new MenuAdapter.OnMenuChangedListener() {
            @Override
            public void onMenuDeleted(int position) {
                menuList.remove(position);
                menuAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onMenuChanged(int position, String name, double price) {
                menuList.get(position).name = name;
                menuList.get(position).price = price;
            }
        });

        rvMenu.setAdapter(menuAdapter);


        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            menuList.add(new MenuItem("", 0));
            menuAdapter.notifyItemInserted(menuList.size() - 1);
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                saveMenuChanges();
                finish();
            }
        });

    }


}