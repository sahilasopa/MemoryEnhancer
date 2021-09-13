package com.sahilasopa.memoryenhancer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.sahilasopa.memoryenhancer.Models.Flashcard;
import com.sahilasopa.memoryenhancer.databinding.ActivityNewFlashcardBinding;

import java.util.Objects;

public class NewFlashcard extends AppCompatActivity {
    ActivityNewFlashcardBinding binding;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewFlashcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
        String uid = values.getString("uid", "none");
        database = FirebaseDatabase.getInstance();
        binding.buttonSignIn.setOnClickListener(v -> {
            if (binding.content.getText().toString().isEmpty()) {
                binding.content.setText(R.string.enter);
                binding.content.requestFocus();
                return;
            }
            Flashcard card = new Flashcard();
            card.setUid(uid);
            card.setTitle(binding.content.getText().toString());
            database.getReference().child("Flashcards").child(Objects.requireNonNull(database.getReference().child("Flashcards").push().getKey())).setValue(card);
            Intent flashcard = new Intent(this, FlashcardActivity.class);
            startActivity(flashcard);
            finish();
        });
    }
}