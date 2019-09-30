package com.example.eos_basic_todo_app.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eos_basic_todo_app.Data.Database.MyDatabase;
import com.example.eos_basic_todo_app.Data.Entity.TodoItem;
import com.example.eos_basic_todo_app.R;

import java.util.ArrayList;
import java.util.List;

public class MainTodoAdapter extends RecyclerView.Adapter<MainTodoViewHolder> {

    private ArrayList<TodoItem> itemList = new ArrayList<>();

    public void submitAll(List<TodoItem> list){
        itemList.clear();
        itemList.addAll(list);
         notifyDataSetChanged();
    }

    public void add_item(TodoItem item){
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void removeAllItem(){
        itemList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainTodoViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final MainTodoViewHolder viewHolder =
                new MainTodoViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_todo, parent, false));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodoItem temp = itemList.get(viewHolder.getAdapterPosition());
                if(temp.getChecked()) {
                    temp.setChecked(false);
                }else{
                    temp.setChecked(true);
                }
                MyDatabase myDatabase = MyDatabase.getInstance(parent.getContext());
                myDatabase.todoDao().UpdateTodo(temp);
                notifyItemChanged(viewHolder.getAdapterPosition());
//
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final TodoItem temp = itemList.get(viewHolder.getAdapterPosition());
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(temp.getTitle());
                final String [] items = {"수정", "삭제", "취소"};
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        switch (items[position]){
                            case "수정":
                                //TODO: ACTIVITY 만들기 (MODIFY ACTIVITY)
                                break;
                            case "삭제":
                                itemList.remove(temp);
                                MyDatabase myDatabase = MyDatabase.getInstance(parent.getContext());
                                myDatabase.todoDao().DeleteTodo(temp);
                                notifyItemRemoved(viewHolder.getAdapterPosition());
                            case "취소":
                                break;
                        }
                    }
                });
                builder.show();
                return false;
            }
        });

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
