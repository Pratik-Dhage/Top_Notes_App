package com.example.top_notes_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//This is a Model Class
//Room Database will generate table as my_notes with columns id,title,desc
@Entity(tableName = "my_notes")
public class Note {

    private String title;
    private String desc; //description

    // Dependency is added for annotation purpose in build.gradle(app/Module level)
    // Annotations is for/part of Room Database
    //Room Database will autoIncrement id
    @PrimaryKey(autoGenerate = true)
    private int id;


    //Constructor
    public Note(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    // Getter and Setter for id, title, desc

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
