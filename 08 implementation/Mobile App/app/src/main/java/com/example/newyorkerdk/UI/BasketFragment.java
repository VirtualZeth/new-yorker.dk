package com.example.newyorkerdk.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.FragmentBasketBinding;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketFragment extends Fragment {

    private SharedViewModel model;
    public BasketFragment() {
        // Required empty public constructor
    }

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBasketBinding binding;

        binding = FragmentBasketBinding.inflate(getLayoutInflater());
        binding.button.setOnClickListener(v -> displayBuildWallFragment());
        binding.button2.setOnClickListener(v -> displayContactUsFragment());
        model.getBasketTotalPrice().observe(requireActivity(), binding.totalPriceTextView::setText);

        return binding.getRoot();
    }

    public void displayContactUsFragment() {

        ContactUsFragment contactUsFragment = ContactUsFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, contactUsFragment)
                .addToBackStack(null).commit();
    }

    private void displayBuildWallFragment() {
        BuildWallFragment buildWallFragment = BuildWallFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                buildWallFragment).addToBackStack(null).commit();
    }

    public void getWallsInBasket() {

        model.getBasket().getValue().getListOfWalls();


    }

}