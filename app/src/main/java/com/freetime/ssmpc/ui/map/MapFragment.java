package com.freetime.ssmpc.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.freetime.ssmpc.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
            MapViewModel mapViewModel =
            new ViewModelProvider(this).get(MapViewModel.class);

            binding = FragmentMapBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            WebView webView = binding.mapWebView;
            webView.setWebViewClient(new WebViewClient()); // Ensures links open inside the WebView
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true); // Enable JS if needed

            webView.loadUrl("https://map.supersmp.fun"); // Replace with your desired URL

            return root;
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}