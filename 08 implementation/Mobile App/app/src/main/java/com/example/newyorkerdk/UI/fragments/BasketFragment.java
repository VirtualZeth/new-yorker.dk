package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.FragmentBasketBinding;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBasketBinding binding;

        binding = FragmentBasketBinding.inflate(getLayoutInflater());
        binding.button.setOnClickListener(v -> displayBuildWallFragmentNewWall());
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

    private void displayBuildWallFragmentNewWall() {
        model.newCurrentWall();
        BuildWallFragment buildWallFragment = BuildWallFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,
                buildWallFragment).addToBackStack(null).commit();
    }

    private void displayBuildWallFragmentEdit(Wall wall) {
        model.setCurrentWall(wall);
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