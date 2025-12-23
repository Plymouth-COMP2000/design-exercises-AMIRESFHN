package com.example.resapp;

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



        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, "FAB clicked", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null)
                        .show()
        );
    }
}
