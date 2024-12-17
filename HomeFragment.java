package com.alexandr.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

    private Button createNoteButton;
    private Button viewNotesButton;
    private Button deleteNoteButton;

    public HomeFragment() {
        // Порожній конструктор
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Інфлейт макету фрагмента
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ініціалізація кнопок
        createNoteButton = view.findViewById(R.id.createNoteButton);
        viewNotesButton = view.findViewById(R.id.viewNotesButton);
        deleteNoteButton = view.findViewById(R.id.deleteNoteButton);

        // Обробники кліків для кнопок
        createNoteButton.setOnClickListener(v -> navigateToCreateNote());
        viewNotesButton.setOnClickListener(v -> navigateToViewNotes());
        deleteNoteButton.setOnClickListener(v -> navigateToDeleteNote());

        return view;
    }

    // Метод для навігації до фрагмента створення нотатки
    private void navigateToCreateNote() {
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_homeFragment_to_createNoteFragment);
    }

    // Метод для навігації до фрагмента перегляду нотаток
    private void navigateToViewNotes() {
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_homeFragment_to_viewNotesFragment);
    }

    // Метод для навігації до фрагмента видалення нотаток
    private void navigateToDeleteNote() {
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_homeFragment_to_deleteNoteFragment);
    }
}
