package com.geektech.taskapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.geektech.taskapp.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert(Task employee);

    @Delete
    void delete(Task employee);

    @Update
    void update(Task employee);

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllLive();
}

