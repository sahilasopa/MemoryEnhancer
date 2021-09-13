package com.sahilasopa.memoryenhancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.sahilasopa.memoryenhancer.Models.Todo;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CardView sentences = view.findViewById(R.id.sentences);
        CardView numbers = view.findViewById(R.id.numbers);
        CardView flashcards = view.findViewById(R.id.flashcard);
        CardView todos = view.findViewById(R.id.todo);
        sentences.setOnClickListener(view1 -> {
            Intent sentence = new Intent(getActivity(), SentencesActivity.class);
            startActivity(sentence);
        });
        numbers.setOnClickListener(view1 -> {
            Intent number = new Intent(getActivity(), NumbersActivity.class);
            startActivity(number);
        });
        flashcards.setOnClickListener(view1 -> {
            Intent flashcard = new Intent(getActivity(), FlashcardActivity.class);
            startActivity(flashcard);
        });
        todos.setOnClickListener(view1 -> {
            Intent todo = new Intent(getActivity(), TodoActivity.class);
            startActivity(todo);
        });
    }

}