package com.example.top_notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.top_notes_app.databinding.ActivityWriteNotesBinding;

public class Write_Notes extends AppCompatActivity {

    ActivityWriteNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().hide();

        //onClicking Left Arrow for Back
        binding.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Write_Notes.this , MainActivity.class);
              startActivity(intent);
            }
        });

        //onClicking Submit Button
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Send Data to MainActivity
                Intent intent = new Intent(Write_Notes.this,MainActivity.class);
                intent.putExtra("note_title",binding.noteTitle.getText().toString());
                intent.putExtra("note_details",binding.noteDetails.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}