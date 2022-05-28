package com.example.top_notes_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top_notes_app.databinding.SampleItemBinding;

import javax.security.auth.callback.Callback;

// Here we are using ListAdapter instead of RecyclerView.Adapter
//Because When we delete a note , the list of notes will not be reloaded (Reloading Consumes memory)
//Note : We can use RecyclerView.Adapter as well
public class Note_AdapterClass extends ListAdapter<Note,Note_AdapterClass.ViewHolderClass> {


    public Note_AdapterClass()
    {
        super(CALLBACK);
    }


    //to check whether id and desc of note is same or not
    private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()== newItem.getId(); //== because it is int type
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDesc().equals(newItem.getDesc()); //equals() because String Type
        }
    };

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item,parent,false);
        return new ViewHolderClass(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {

      Note note = getItem(position);

      holder.binding.title.setText(note.getTitle());
      holder.binding.actualNote.setText(note.getDesc());

    }

//create function
    public Note getNote(int position)
    {
        return getItem(position);
    }

    //ViewHolder Class
    public class ViewHolderClass extends RecyclerView.ViewHolder {

        SampleItemBinding binding; //binding for the sample_item.xml

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

       binding = SampleItemBinding.bind(itemView);

        }
    }

}
