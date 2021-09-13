package com.sahilasopa.memoryenhancer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SummaryFragment extends Fragment {
    View view2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view2 = view;
        updateScores();
        super.onViewCreated(view, savedInstanceState);
    }

    public void updateScores() {
        // Get from the SharedPreferences
        SharedPreferences values = view2.getContext().getSharedPreferences("memories", 0);
        final int stringPlayed = values.getInt("stringPlayed", 0);
        final int numberPlayed = values.getInt("numbersPlayed", 0);
        final int stringWon = values.getInt("stringScore", 0);
        final int numberWon = values.getInt("numberScore", 0);
        TextView numberScore;
        TextView numberPlays;
        TextView stringPlays;
        TextView stringScore;
        numberScore = view2.findViewById(R.id.textView9);
        stringPlays = view2.findViewById(R.id.textView6);
        stringScore = view2.findViewById(R.id.textView7);
        numberPlays = view2.findViewById(R.id.textView8);
        numberPlays.setText(getString(R.string.numberPLayed).concat(String.valueOf(numberPlayed)));
        stringPlays.setText(getString(R.string.stringPlays).concat(String.valueOf(stringPlayed)));
        stringScore.setText(getString(R.string.stringWon).concat(String.valueOf(stringWon)));
        numberScore.setText(getString(R.string.numberWon).concat(String.valueOf(numberWon)));
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        updateScores();
        super.onResume();
    }
}