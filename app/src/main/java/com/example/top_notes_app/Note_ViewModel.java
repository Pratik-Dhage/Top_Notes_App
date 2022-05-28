package com.example.top_notes_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Note_ViewModel extends AndroidViewModel {

private Note_Repository noteRepository;
private LiveData<List<Note>> noteList;

    public Note_ViewModel(@NonNull Application application) {
        super(application);

        //Receive Data Coming From Note_Repository Class
        noteRepository = new Note_Repository(application);
        noteList = noteRepository.getAllData();

    }

//**Call Methods of Insert,Update,Delete from Note_Repository Class**

    //for Insert
    public void insert(Note note)
    {
        noteRepository.insertData(note);
    }

    //for Update
    public void update(Note note)
    {
        noteRepository.updateData(note);
    }

    //for Delete
    public void delete(Note note)
    {
        noteRepository.deleteData(note);
    }

   public LiveData<List<Note>> getAllNotes()
    {
       return noteList;
    }

}
