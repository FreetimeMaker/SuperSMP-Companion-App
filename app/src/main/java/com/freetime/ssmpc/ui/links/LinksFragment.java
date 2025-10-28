package com.freetime.ssmpc.ui.links;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.freetime.ssmpc.databinding.FragmentLinksBinding;

public class LinksFragment extends Fragment {

    private FragmentLinksBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LinksViewModel linksViewModel =
                new ViewModelProvider(this).get(LinksViewModel.class);

        binding = FragmentLinksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button discordBTN = binding.discordBTN;
        discordBTN.setOnClickListener(v -> {
            String url = "https://discord.com/invite/supersmp"; // URL of the Discord invite to SuperSMP
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button vote1BTN = binding.vote1BTN;
        vote1BTN.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Vote1Activity.class);
            startActivity(intent);
        });

        Button vote2BTN = binding.vote2BTN;
        vote2BTN.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Vote2Activity.class);
            startActivity(intent);
        });

        Button vote3BTN = binding.vote3BTN;
        vote3BTN.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Vote3Activity.class);
            startActivity(intent);
        });

        Button vote4BTN = binding.vote4BTN;
        vote4BTN.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Vote4Activity.class);
            startActivity(intent);
        });

        Button vote5BTN = binding.vote5BTN;
        vote5BTN.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Vote5Activity.class);
            startActivity(intent);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
