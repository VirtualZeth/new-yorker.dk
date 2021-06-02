package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newyorkerdk.databinding.FragmentWebviewBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment {

    private FragmentWebviewBinding binding;
    private static final String PAGE_NAME = "page_name";
    private String pageName;

    public WebViewFragment() {
        // Required empty public constructor
    }

    public static WebViewFragment newInstance(String pageName) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(PAGE_NAME, pageName);
        webViewFragment.setArguments(args);
        return webViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pageName = getArguments().getString(PAGE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentWebviewBinding.inflate(getLayoutInflater());
        binding.webview.getSettings().setJavaScriptEnabled(true);
        showPage();
        return binding.getRoot();
    }

    private void showPage() {

        if (getArguments() == null) {
            return;
        }

        if (this.pageName.equals("priceExample")) {
            binding.webview.loadUrl("https://www.new-yorker.dk/pris-eksempler-paa-new-yorker-glasvaeg/");
        }
        if (this.pageName.equals("contactUs")) {
            binding.webview.loadUrl("https://www.new-yorker.dk/kontakt/");
        }
    }
}