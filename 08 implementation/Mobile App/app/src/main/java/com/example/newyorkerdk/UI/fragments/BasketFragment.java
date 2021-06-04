package com.example.newyorkerdk.UI.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.FragmentBasketBinding;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

import java.util.ArrayList;

public class BasketFragment extends Fragment implements RecyclerViewAdapter.OnWallListener {

    private SharedViewModel model;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    FragmentBasketBinding binding;

    public BasketFragment() {
        // Required empty public constructor
    }

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentBasketBinding.inflate(getLayoutInflater());
        recyclerView = binding.recyclerview;
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(requireActivity(), R.drawable.horizontal_divider);
        assert horizontalDivider != null;
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireActivity()));

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding.button.setOnClickListener(v -> displayBuildWallFragmentNewWall());
        binding.button2.setOnClickListener(v -> displayContactUsFragment());
        model.getBasket().observe(requireActivity(), basketUpdateObserver );
        model.getBasketTotalPrice().observe(requireActivity(), totalPrice -> binding.
                totalPriceTextView.setText(getString(R.string.total_price, totalPrice)));

        return binding.getRoot();
    }

    Observer<Basket> basketUpdateObserver = walls -> {
        recyclerViewAdapter = new RecyclerViewAdapter(requireActivity(), (ArrayList<Wall>) walls.getListOfWalls(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
    };


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

    @Override
    public void onClick(int position, String tag) {
        if (tag.equals("wallItem")) {
            displayBuildWallFragmentEdit(model.getBasket().getValue().getListOfWalls().get(position));
        }

        if (tag.equals("delete")) {
            model.removeFromBasket(position);
        }
    }
}