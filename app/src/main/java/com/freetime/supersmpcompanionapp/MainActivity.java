package com.freetime.supersmpcompanionapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Fragment homeFragment = new HomeFragment();
        Fragment scmdFragment = new SCMDFragment();
        Fragment ulFragment = new ULFragment();

        setCurrentFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    setCurrentFragment(homeFragment);
                    break;
                case R.id.scmd:
                    setCurrentFragment(scmdFragment);
                    break;
                case R.id.ul:
                    setCurrentFragment(ulFragment);
                    break;
            }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
    }
}