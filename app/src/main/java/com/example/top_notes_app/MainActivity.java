package com.example.top_notes_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.top_notes_app.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding; // Object for binding data purpose

    private Note_ViewModel noteViewModel; // Object of Note_ViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         getSupportActionBar().hide(); //hide Action Bar

        //Initialize noteViewModel
        noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(Note_ViewModel.class);


        //on Clicking Floating Action Button
        binding.floatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Jump to Write_Notes Activity
                Intent intent = new Intent(MainActivity.this,Write_Notes.class);
                startActivityForResult(intent,1);
            }
        });



        //RecyclerView Stuff
        binding.recylerViewMain.setLayoutManager(new LinearLayoutManager(this));
        binding.recylerViewMain.setHasFixedSize(true);

        Note_AdapterClass adapter =new Note_AdapterClass();
        binding.recylerViewMain.setAdapter(adapter);

    // Get Data from Note_ViewModel and Show in MainActivity
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                adapter.submitList(notes);
            }
        });



        // For Deleting Item on Swipe Right & Editing Item on Swipe Left

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //delete the note
                noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recylerViewMain);

    } //onCreate() ends here



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

             if(requestCode==1)
             {

                 // Get Data from Write_Notes Activity
                 String note_Title = data.getStringExtra("note_title");
                 String note_Details = data.getStringExtra("note_details");

                 //make Object of Note class and pass note_Title and note_Details
                 Note note = new Note(note_Title,note_Details);

                 noteViewModel.insert(note); //Insert note
                 Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();

             }

    }
}