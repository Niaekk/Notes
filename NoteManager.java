package com.alexandr.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class NoteManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    public NoteManager(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("notes_data", Context.MODE_PRIVATE);
    }

    public void addNote(ContactsContract.CommonDataKinds.Note note) {
        // Логіка додавання нотатки
    }

    public List<ContactsContract.CommonDataKinds.Note> getNotes() {
        // Логіка отримання нотаток
        return new ArrayList<>();
    }

    public void deleteNoteByTitle(String title) {
        // Логіка видалення нотатки
    }
}
