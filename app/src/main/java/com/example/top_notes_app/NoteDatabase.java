package com.example.top_notes_app;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Entity;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

//version = 1 because our Database is not in Production // entities = Note.class (Model Class)
@Database(entities = Note.class , version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    // static means no need to create Object
    private static NoteDatabase noteDatabase_instance; //noteDatabase_instance Object will remain same throughout the code


    public abstract NoteDao noteDao();

    // Create Concrete Method    //Note: Abstract class can have Concrete Methods(Methods that have definition/body)
    //synchronized means it will not Block Main Thread of App
    // Synchronized means The process of allowing only a single thread to access the shared data or resource at a particular point of time
    public static synchronized NoteDatabase getInstance(Context context)
    {

        // if instance of NoteDatabase is null then it will be created as noteDatabase_instance
        if(noteDatabase_instance == null)
        {
            noteDatabase_instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database").fallbackToDestructiveMigration()
                    .build();

        }

          return  noteDatabase_instance;
    }


}
