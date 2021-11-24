package com.example.madt_lab_4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class AddNoteActivity extends AppCompatActivity {
    int notenumber;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        editText = findViewById(R.id.EditNote);

        Intent intent = getIntent();

        notenumber = intent.getIntExtra("notenumber", -1);
        if (notenumber != -1) {
            editText.setText(MainActivity.notes.get(notenumber));
        } else {

            MainActivity.notes.add("");
            notenumber = MainActivity.notes.size() - 1;
            MainActivity.adapter.notifyDataSetChanged();

        }
    }

    public void onbtnsave (View view) {
        String noteText = editText.getText().toString();
        if(noteText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter text", Toast.LENGTH_SHORT).show();
        }else {
            MainActivity.notes.set(notenumber, noteText);
            MainActivity.adapter.notifyDataSetChanged();
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("notes", Context.MODE_PRIVATE);
            HashSet<String> set = new HashSet(MainActivity.notes);
            sharedPreferences.edit().putStringSet(SetNotes.BASE_NOTE_KEY, set).apply();
            finish();
        }
    }
}
