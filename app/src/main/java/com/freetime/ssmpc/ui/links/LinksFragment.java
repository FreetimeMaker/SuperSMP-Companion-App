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
            String discordUrl = "https://discord.com/invite/supersmp"; // your invite link
            Uri uri = Uri.parse(discordUrl);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            // Optional: explicitly tell Android you want to use a browser, not an in-app handler
            intent.addCategory(Intent.CATEGORY_BROWSABLE);

            // Safety check
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(requireContext(), "No browser found to open link", Toast.LENGTH_SHORT).show();
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
