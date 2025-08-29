package com.freetime.supersmpcompanionapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Floating Action Button (FAB)
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> 
            Toast.makeText(MainActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show()
        );

        // Initialize BottomAppBar
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.search:
                    Toast.makeText(MainActivity.this, "Search Clicked", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.option_1:
                    Toast.makeText(MainActivity.this, "Option 1 Clicked", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.option_2:
                    Toast.makeText(MainActivity.this, "Option 2 Clicked", Toast.LENGTH_SHORT).show();
                    return true;

                default:
                    return false;
            }
        });
    }

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