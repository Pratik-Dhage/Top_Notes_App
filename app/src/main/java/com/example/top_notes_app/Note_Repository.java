package com.example.top_notes_app;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Note_Repository {

    private NoteDao noteDao;
    private LiveData<List<Note>> note_list;

    //Constructor
    //Application is SubClass of Context
    public Note_Repository(Application application) {

        //Note : To Access Method of Abstract Class Write Abstract Class Name.method() (NoteDatabase.getInstance())
        //Create object of NoteDatabase and Access its getInstance() method and pass application as Context
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);

        //Access NoteDao abstract method noteDao()
        noteDao = noteDatabase.noteDao(); // Method definition of noteDao() is handled by Room Database

        note_list = noteDao.getAllData();

    }

    //** These Methods Are Called in Note_ViewModel Class
    public void insertData(Note note) {
        new Insert_Task(noteDao).execute(note);
    }

    public void updateData(Note note) {
        new Update_Task(noteDao).execute(note);
    }

    public void deleteData(Note note) {
        new Delete_Task(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllData() {
        return note_list;
    }


//Async Task means One After the Other
//Sync Task Means Concurrently Occurring

    //Class For Inserting Note
    private static class Insert_Task extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        //Constructor
        public Insert_Task(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insert(notes[0]);
            return null;
        }
    }

    //Class for Updating Note
    private static class Update_Task extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        //Constructor
        public Update_Task(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.update(notes[0]);
            return null;
        }

    }

    //Class for Deleting Note
    private static class Delete_Task extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        //Constructor
        public Delete_Task(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.delete(notes[0]);
            return null;
        }

    }
}


