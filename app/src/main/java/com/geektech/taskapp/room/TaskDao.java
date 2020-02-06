package com.geektech.taskapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.taskapp.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();
    @Insert
    void insert(Task employee);
}
