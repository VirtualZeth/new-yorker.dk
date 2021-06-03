package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.newyorkerdk.databinding.FragmentWebviewBinding;

public class WebViewFragment extends Fragment {

    private FragmentWebviewBinding binding;
    private static final String PAGE_NAME = "page_name";
    private String pageName;

    public WebViewFragment() { }

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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