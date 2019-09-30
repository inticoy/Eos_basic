package com.example.eos_basic_todo_app.Data.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eos_basic_todo_app.Data.Entity.TodoItem;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void InsertTodo(TodoItem item);

    @Delete
    void DeleteTodo(TodoItem item);

    @Update
    void UpdateTodo(TodoItem item);

    @Query("SELECT * FROM Todo")
    List<TodoItem> getAllTodo();

    @Query("SELECT * FROM Todo WHERE id = :id")
    TodoItem getTodo(int id);

    @Query("DELETE FROM Todo")
    void deleteAllTodo();

}
