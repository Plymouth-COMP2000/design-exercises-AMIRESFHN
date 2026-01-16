package com.example.resapp;
import android.content.Intent;
import com.example.resapp.databinding.ContentStaffDashBinding;


import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.resapp.databinding.ActivityStaffDashBinding;
import com.google.android.material.snackbar.Snackbar;
import android.widget.Toast;
import android.util.Log;


public class staff_dash extends AppCompatActivity {
    private ActivityStaffDashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        View includedLayout = findViewById(R.id.contentStaffDash);
        ContentStaffDashBinding contentBinding = ContentStaffDashBinding.bind(includedLayout);

        contentBinding.tvSignOut.setOnClickListener(v -> {
            Intent intent = new Intent(staff_dash.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });


       contentBinding.btnManageMenu.setOnClickListener(v -> {
           Log.d("STAFF_DASH", "Manage Menu Button Clicked");
            Toast.makeText(staff_dash.this, "Manage Menu Clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(staff_dash.this, foodmenu.class));
        });
        contentBinding.btnViewReservations.setOnClickListener(v -> {
            Intent intent = new Intent(staff_dash.this, StaffReservationListActivity.class);
            startActivity(intent);
        });


        binding.fab.setOnClickListener(view -> {
            Snackbar.make(view, "FAB clicked", Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab)
                    .setAction("Action", null)
                    .show();
        });
    }

}
