package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMainBinding binding;
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        binding.toBuildWallButton.setOnClickListener(click -> displayBuildWallFragment());
        binding.toPriceexamplesButton.setOnClickListener( click -> displayPriceExamplesWebView());
        binding.contactButton.setOnClickListener(click -> displayKontaktUsWebview());

        return binding.getRoot();
    }

    private void displayBuildWallFragment() {
        BuildWallFragment buildWallFragment = BuildWallFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,
                buildWallFragment).addToBackStack(null).commit();
    }

    private void displayPriceExamplesWebView() {
        WebViewFragment webViewFragment = WebViewFragment.newInstance("priceExample");
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, webViewFragment)
                .addToBackStack(null).commit();
    }

    private void displayKontaktUsWebview() {
        WebViewFragment webViewFragment = WebViewFragment.newInstance("contactUs");
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, webViewFragment)
                .addToBackStack(null).commit();
    }
}