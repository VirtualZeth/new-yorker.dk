package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorkerdk.databinding.FragmentPriceExamplesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriceExamplesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriceExamplesFragment extends Fragment {

   FragmentPriceExamplesBinding binding;

    public PriceExamplesFragment() {
        // Required empty public constructor
    }

    public static PriceExamplesFragment newInstance() {
        PriceExamplesFragment fragment = new PriceExamplesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPriceExamplesBinding.inflate(getLayoutInflater());
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.loadUrl("https://www.new-yorker.dk/pris-eksempler-paa-new-yorker-glasvaeg/");
        return binding.getRoot();
    }
}