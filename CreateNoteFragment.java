package com.alexandr.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log; // Додаємо Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.Set;

public class CreateNoteFragment extends Fragment {

    private static final String TAG = "CreateNoteFragment"; // Тег для логів

    private EditText titleEditText;
    private EditText noteEditText;
    private Button saveNoteButton;

    public CreateNoteFragment() {
        // Порожній конструктор
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called"); // Лог для перевірки виклику onCreateView

        // Інфлейт макета фрагмента
        View view = inflater.inflate(R.layout.fragment_create_note, container, false);
        Log.d(TAG, "Layout inflated: R.layout.fragment_create_note");

        // Ініціалізація елементів інтерфейсу
        titleEditText = view.findViewById(R.id.titleEditText);
        noteEditText = view.findViewById(R.id.noteEditText);
        saveNoteButton = view.findViewById(R.id.saveNoteButton);

        Log.d(TAG, "UI elements initialized: titleEditText, noteEditText, saveNoteButton");

        // Обробник кліку для кнопки "Зберегти"
        saveNoteButton.setOnClickListener(v -> {
            Log.d(TAG, "Save button clicked");

            String title = titleEditText.getText().toString().trim();
            String noteText = noteEditText.getText().toString().trim();

            // Перевірка, чи введено заголовок і текст нотатки
            if (title.isEmpty() || noteText.isEmpty()) {
                Log.w(TAG, "Empty input: title or noteText is empty");
                Toast.makeText(getContext(), "Будь ласка, введіть заголовок та текст нотатки", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "Title: " + title + ", NoteText: " + noteText);

                // Збереження нотатки в SharedPreferences
                saveNoteToSharedPreferences(title, noteText);

                // Показати повідомлення про успішне додавання
                Toast.makeText(getContext(), "Нотатку додано!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Note saved successfully");

                // Повертаємося до попереднього фрагмента
                getActivity().onBackPressed(); // Це замість переходу через NavController
                Log.d(TAG, "Navigating to HomeFragment");
            }
        });

        return view;
    }

    // Метод для збереження нотатки в SharedPreferences
    private void saveNoteToSharedPreferences(String title, String noteText) {
        Log.d(TAG, "Saving note to SharedPreferences");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("notes_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Зчитування існуючих нотаток (якщо є)
        Set<String> notes = sharedPreferences.getStringSet("notes_list", new HashSet<>());
        notes.add(title + ": " + noteText); // Додаємо нову нотатку до списку

        editor.putStringSet("notes_list", notes); // Оновлюємо список нотаток
        editor.apply();

        Log.d(TAG, "Note saved: " + title + ": " + noteText);
    }
}
