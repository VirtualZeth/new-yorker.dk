package com.example.newyorkerdk.UI.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.fragments.BasketFragment;
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
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        SharedViewModel model = new ViewModelProvider(this).get(SharedViewModel.class);

        //Observer som opdatere UI'et
        final Observer<String> tottalPriceObserver = new Observer<String>() {
            @Override
            public void onChanged(String newTotalPrice) {
                binding.toolbarPrice.setText(newTotalPrice);
            }
        };

        model.getBasketTotalPrice().observe(this, tottalPriceObserver);

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