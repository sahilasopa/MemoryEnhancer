package com.sahilasopa.memoryenhancer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahilasopa.memoryenhancer.Models.Flashcard;
import com.sahilasopa.memoryenhancer.R;

import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.ViewHolder> {
    private final Context context;
    private final List<Flashcard> flashcardList;

    public FlashcardAdapter(Context context, List<Flashcard> flashcards) {
        this.context = context;
        this.flashcardList = flashcards;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flashcard_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flashcard flashcard = flashcardList.get(position);
        holder.title.setText(flashcard.getTitle());
    }

    @Override
    public int getItemCount() {
        return flashcardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.flashcardTitle);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            flashcardList.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, flashcardList.size());
        }
    }
}
