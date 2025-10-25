package com.freetime.ssmpc.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.freetime.ssmpc.databinding.FragmentShopBinding;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
            ShopViewModel shopViewModel =
            new ViewModelProvider(this).get(ShopViewModel.class);

            binding = FragmentShopBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            WebView webView = binding.shopWebView;
            webView.setWebViewClient(new WebViewClient()); // Ensures links open inside the WebView
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true); // Enable JS if needed

            webView.loadUrl("https://store.supersmp.fun"); // Replace with your desired URL

            return root;
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}