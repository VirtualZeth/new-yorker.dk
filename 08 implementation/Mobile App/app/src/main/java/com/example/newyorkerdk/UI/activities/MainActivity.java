package com.example.newyorkerdk.UI.activities;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.fragments.BasketFragment;
import com.example.newyorkerdk.UI.fragments.MainFragment;
import com.example.newyorkerdk.UI.fragments.WebViewFragment;
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

        final Observer<String> tottalPriceObserver = newTotalPrice -> binding.toolbarPrice.setText(newTotalPrice);


        model.getBasketTotalPrice().observe(this, tottalPriceObserver);
        binding.baskeImage.setOnClickListener(v -> displayBasketFragment());


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
    private void displayBasketFragment() {
        BasketFragment basketFragment = BasketFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                basketFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof MainFragment) {
            return;
        }
        if (currentFragment instanceof WebViewFragment) {
            displayMainScreenFragment();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Gå tilbage")
                .setMessage("Er du sikker på at du vil gå tilbage til forsiden? Din kurvs indhold vil ikke blive slettet")
                .setPositiveButton("Gå tilbage", (dialog, which) ->  displayMainScreenFragment())
                .setNegativeButton("Annuller", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}