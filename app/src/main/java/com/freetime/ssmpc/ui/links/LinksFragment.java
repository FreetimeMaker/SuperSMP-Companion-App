package com.freetime.ssmpc.ui.links;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.freetime.ssmpc.databinding.FragmentLinksBinding;

public class LinksFragment extends Fragment {

    private FragmentLinksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LinksViewModel linksViewModel =
                new ViewModelProvider(this).get(LinksViewModel.class);

        binding = FragmentLinksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.welcomeText;
        linksViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Discord button click listener
        binding.openDiscordButton.setOnClickListener(v -> {
            String discordUrl = "https://discord.com/invite/supersmp"; // Replace with your actual invite
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(discordUrl));
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
