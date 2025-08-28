package com.freetime.supersmpcompanionapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.freetime.supersmpcompanionapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize BottomNavigationView
        bottomNav = findViewById(R.id.bottomNav);
        if (bottomNav == null) {
            // Handle missing BottomNavigationView (e.g., log error or show fallback UI)
            return;
        }

        // Set listener for navigation items
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment(); // Replace with your HomeFragment
            } else if (itemId == R.id.nav_cmd) {
                selectedFragment = new CommandFragment(); // Replace with your CommandFragment
            } else if (itemId == R.id.nav_vl) {
                selectedFragment = new VoLiFragment(); // Replace with your VoiceFragment
            }

            // Replace fragment if a valid one is selected
            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment) // Ensure fragment_container exists in activity_main.xml
                        .commit();
                return true;
            }
            return false;
        });

        // Load default fragment on startup (e.g., HomeFragment)
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }
}