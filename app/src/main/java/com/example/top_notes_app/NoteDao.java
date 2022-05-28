package com.example.top_notes_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// All Methods in Interface are By Default public abstract methods
// Dao (Data Access Object)can be either Interface OR Abstract Class
@Dao
public interface NoteDao {

    @Insert
    public void insert(Note note);

    @Update
    public void update(Note note);

    @Delete
    public void delete(Note note);

    @Query("SELECT * FROM my_notes")
    public LiveData<List<Note>> getAllData();

}
