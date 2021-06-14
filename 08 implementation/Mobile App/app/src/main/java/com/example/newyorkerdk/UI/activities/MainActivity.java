package com.example.newyorkerdk.UI.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.fragments.BasketFragment;
import com.example.newyorkerdk.UI.fragments.MainFragment;
import com.example.newyorkerdk.databinding.ActivityMainBinding;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

import java.util.Observer;

/**
 * @author Mike
 * Et fragment {@link AppCompatActivity} subclass.
 * Fungerer som entrypoint til vores applikation
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private SharedViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        displayMainScreenFragment();

        binding.baskeImage.setOnClickListener(v -> {displayBasketFragment();});
    }

    private void displayMainScreenFragment() {
        MainFragment mainFragment = MainFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                mainFragment).addToBackStack(null).commit();

    }
    private void displayBasketFragment() {
        BasketFragment basketFragment = BasketFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                basketFragment).addToBackStack(null).commit();

    }
}