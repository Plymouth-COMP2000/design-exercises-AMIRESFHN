package com.example.resapp;
import android.content.Intent;
import com.example.resapp.databinding.ContentStaffDashBinding;


import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.resapp.databinding.ActivityStaffDashBinding;
import com.google.android.material.snackbar.Snackbar;

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



        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, "FAB clicked", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null)
                        .show()
        );
    }
}
