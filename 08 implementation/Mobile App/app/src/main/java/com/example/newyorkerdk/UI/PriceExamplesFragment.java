package com.example.newyorkerdk.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.newyorkerdk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriceExamplesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriceExamplesFragment extends Fragment {
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
        WebView webView = requireActivity().findViewById(R.id.webview);
        webView.loadUrl("https://www.journaldev.com");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.new-yorker.dk/pris-eksempler-paa-new-yorker-glasvaeg/");
        return inflater.inflate(R.layout.fragment_price_examples, container, false);
    }
}