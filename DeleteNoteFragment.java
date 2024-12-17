package com.alexandr.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNoteFragment extends Fragment {

    private ListView notesListView;
    private List<String> notesList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Інфляція макета фрагмента
        View view = inflater.inflate(R.layout.fragment_delete_note, container, false);

        // Ініціалізація ListView для відображення нотаток
        notesListView = view.findViewById(R.id.notesListView);

        // Завантаження нотаток з SharedPreferences
        loadNotes();

        // Ініціалізація адаптера для ListView
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, notesList);
        notesListView.setAdapter(adapter);

        // Обробник натискання на елемент ListView (для видалення)
        notesListView.setOnItemClickListener((parent, view1, position, id) -> {
            String noteToDelete = notesList.get(position);
            deleteNoteFromSharedPreferences(noteToDelete);
            notesList.remove(position); // Видаляємо нотатку зі списку
            adapter.notifyDataSetChanged(); // Оновлюємо адаптер
            Toast.makeText(getContext(), "Нотатку видалено", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    // Метод для завантаження нотаток з SharedPreferences
    private void loadNotes() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("notes_data", Context.MODE_PRIVATE);
        Set<String> notes = sharedPreferences.getStringSet("notes_list", new HashSet<>());
        if (notes != null) {
            notesList.addAll(notes);
        } else {
            Toast.makeText(getContext(), "Немає збережених нотаток", Toast.LENGTH_SHORT).show();
        }
    }

    // Метод для видалення нотатки з SharedPreferences
    private void deleteNoteFromSharedPreferences(String noteToDelete) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("notes_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> notes = sharedPreferences.getStringSet("notes_list", new HashSet<>());
        if (notes != null && notes.contains(noteToDelete)) {
            notes.remove(noteToDelete); // Видаляємо вибрану нотатку
            editor.putStringSet("notes_list", notes);
            editor.apply();
        }
    }
}
