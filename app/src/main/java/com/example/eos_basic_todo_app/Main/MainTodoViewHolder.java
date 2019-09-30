package com.example.eos_basic_todo_app.Main;

import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eos_basic_todo_app.Data.Entity.TodoItem;
import com.example.eos_basic_todo_app.R;

public class MainTodoViewHolder extends RecyclerView.ViewHolder{

    private TextView todo_tv_title;
    private CheckBox todo_cb;

    public MainTodoViewHolder(@NonNull View itemView) {
        super(itemView);

        todo_tv_title = itemView.findViewById(R.id.todo_tv_title);
        todo_cb = itemView.findViewById(R.id.todo_cb);

    }

    public void onBind(TodoItem item){
        todo_tv_title.setText(item.getTitle());
        todo_cb.setChecked(item.getChecked());
        if(item.getChecked()){
            SpannableString contentSp = new SpannableString(item.getTitle());
            contentSp.setSpan(new StrikethroughSpan(), 0, item.getTitle().length(), 0);
            todo_tv_title.setText(contentSp);
        }

    }
}
