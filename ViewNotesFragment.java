package com.alexandr.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewNotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private List<String> notesList = new ArrayList<>();

    public ViewNotesFragment() {
        // Порожній конструктор
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_notes, container, false);

        // Ініціалізація RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Завантаження нотаток з SharedPreferences
        loadNotes();

        // Створення адаптера та прив'язка до RecyclerView
        notesAdapter = new NotesAdapter(notesList);  // Передаємо тільки список нотаток
        recyclerView.setAdapter(notesAdapter);

        return view;
    }

    private void loadNotes() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("notes_data", Context.MODE_PRIVATE);
        Set<String> notes = sharedPreferences.getStringSet("notes_list", new HashSet<>());
        notesList.clear();
        notesList.addAll(notes);
    }
}

