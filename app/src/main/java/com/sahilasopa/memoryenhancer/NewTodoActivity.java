package com.sahilasopa.memoryenhancer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.sahilasopa.memoryenhancer.Models.Todo;
import com.sahilasopa.memoryenhancer.databinding.ActivityNewTodoBinding;

import java.util.Objects;

public class NewTodoActivity extends AppCompatActivity {
    ActivityNewTodoBinding binding;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences values = getApplicationContext().getSharedPreferences("memories", 0);
        String uid = values.getString("uid", "none");
        database = FirebaseDatabase.getInstance();
        binding.buttonSignIn.setOnClickListener(v -> {
            if (binding.title.getText().toString().isEmpty()) {
                binding.title.setText(R.string.enter);
                binding.title.requestFocus();
                return;
            } else if (binding.content.getText().toString().isEmpty()) {
                binding.content.setText(R.string.enter);
                binding.content.requestFocus();
                return;
            }
            Todo todo = new Todo();
            todo.setUid(uid);
            todo.setTitle(binding.title.getText().toString());
            todo.setBody(binding.content.getText().toString());
            database.getReference().child("Todos").child(Objects.requireNonNull(database.getReference().child("Todos").push().getKey())).setValue(todo);
            Intent todoIntent = new Intent(this, TodoActivity.class);
            startActivity(todoIntent);
            finish();
        });
    }
}