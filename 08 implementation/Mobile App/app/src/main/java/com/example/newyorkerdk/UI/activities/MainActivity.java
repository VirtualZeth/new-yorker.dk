package com.example.newyorkerdk.UI.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.fragments.MainFragment;
import com.example.newyorkerdk.databinding.ActivityMainBinding;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

/**
 * @author Mike
 * Et fragment {@link AppCompatActivity} subclass.
 * Fungerer som entrypoint til vores applikation
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedViewModel model;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        model = new ViewModelProvider(this).get(SharedViewModel.class);
        displayMainScreenFragment();
    }



    private void displayMainScreenFragment() {
        MainFragment mainFragment = MainFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                mainFragment).addToBackStack(null).commit();
    }
}