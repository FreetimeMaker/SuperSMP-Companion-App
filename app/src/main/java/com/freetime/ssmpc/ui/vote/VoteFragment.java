package com.freetime.ssmpc.ui.vote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.freetime.ssmpc.databinding.FragmentVoteBinding;

public class VoteFragment extends Fragment {

    private FragmentVoteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VoteViewModel voteViewModel =
                new ViewModelProvider(this).get(VoteViewModel.class);

        binding = FragmentVoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVote;
        voteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}