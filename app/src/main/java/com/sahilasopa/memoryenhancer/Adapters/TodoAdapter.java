package com.sahilasopa.memoryenhancer.Adapters;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sahilasopa.memoryenhancer.Models.Todo;
import com.sahilasopa.memoryenhancer.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private final Context context;
    private final List<Todo> todos;

    public TodoAdapter(Context context, List<Todo> todo) {
        this.context = context;
        this.todos = todo;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.title.setText(todo.getTitle());
        holder.body.setText(todo.getBody());
        holder.body.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todoTitle);
            body = itemView.findViewById(R.id.todoBody);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            todos.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, todos.size());
        }
    }
}
