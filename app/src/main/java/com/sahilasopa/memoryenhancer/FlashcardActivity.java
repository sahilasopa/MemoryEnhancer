package com.sahilasopa.memoryenhancer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sahilasopa.memoryenhancer.Adapters.FlashcardAdapter;
import com.sahilasopa.memoryenhancer.Models.Flashcard;
import com.sahilasopa.memoryenhancer.databinding.ActivityFlashcardBinding;

import java.util.ArrayList;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity {
    DatabaseReference reference;
    private FlashcardAdapter flashcardAdapter;
    private List<Flashcard> flashcards;
    FirebaseDatabase database;
    ActivityFlashcardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFlashcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textView4.setVisibility(View.INVISIBLE);
        binding.button2.setVisibility(View.INVISIBLE);
        database = FirebaseDatabase.getInstance();
        // Get from the SharedPreferences
        SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
        String uid = values.getString("uid", "none");
        RecyclerView recyclerView;
        reference = FirebaseDatabase.getInstance().getReference("Flashcards");
        flashcards = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                flashcards.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Flashcard flashcard = ds.getValue(Flashcard.class);
                    assert flashcard != null;
                    if (flashcard.getUid().equals(uid)) {
                        flashcards.add(flashcard);
                    }
                }
                flashcardAdapter = new FlashcardAdapter(getApplicationContext(), flashcards);
                if (flashcardAdapter.getItemCount() == 0) {
                    binding.textView4.setVisibility(View.VISIBLE);
                    binding.button2.setVisibility(View.VISIBLE);
                    binding.button2.setOnClickListener(v -> {
                        Intent newCard = new Intent(FlashcardActivity.this, NewFlashcard.class);
                        startActivity(newCard);
                        finish();
                    });
                }
                recyclerView.setAdapter(flashcardAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flashcard, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.newCard) {
            Intent newCard = new Intent(this, NewFlashcard.class);
            startActivity(newCard);
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}