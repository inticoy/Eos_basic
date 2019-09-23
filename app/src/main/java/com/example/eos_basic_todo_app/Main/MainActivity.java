package com.example.eos_basic_todo_app.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.eos_basic_todo_app.Data.Database.MyDatabase;
import com.example.eos_basic_todo_app.Data.Entity.TodoItem;
import com.example.eos_basic_todo_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView main_rcv;
    private FloatingActionButton main_fab;
    private MainTodoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_rcv = findViewById(R.id.main_recyclerview);
        main_fab = findViewById(R.id.main_floatingactionbutton);
        adapter = new MainTodoAdapter();

        main_rcv.setAdapter(adapter);
        main_rcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        final MyDatabase myDatabase = MyDatabase.getInstance(this);
        List<TodoItem> list_item = myDatabase.todoDao().getAllTodo();
        adapter.submitAll(list_item);

        main_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long time = System.currentTimeMillis();
                TodoItem temp = new TodoItem(time.toString());
                myDatabase.todoDao().InsertTodo(temp);
                adapter.add_item(temp);

            }
        });


    }
}
