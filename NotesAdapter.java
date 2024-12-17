package com.alexandr.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<String> notes;

    public NotesAdapter(List<String> notes) {
        this.notes = notes;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        String note = notes.get(position);
        String[] noteParts = note.split(": "); // Розбиваємо заголовок і текст нотатки

        holder.titleTextView.setText(noteParts[0]);  // Відображення заголовку
        holder.textTextView.setText(noteParts[1]);   // Відображення тексту нотатки
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView textTextView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.noteTitle);
            textTextView = itemView.findViewById(R.id.noteText);
        }
    }
}
