package com.example.eos_basic_todo_app.Main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eos_basic_todo_app.Data.Entity.TodoItem;
import com.example.eos_basic_todo_app.R;

import java.util.ArrayList;

public class MainTodoAdapter extends RecyclerView.Adapter<MainTodoViewHolder> {

    private ArrayList<TodoItem> itemList = new ArrayList<>();

    @NonNull
    @Override
    public MainTodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainTodoViewHolder viewHolder =
                new MainTodoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_todo, parent, false));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MainTodoViewHolder holder, int position) {
        holder.onBind(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
