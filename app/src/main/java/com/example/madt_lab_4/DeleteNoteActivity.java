package com.example.madt_lab_4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class DeleteNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_note);

        ListView ListNotes = findViewById(R.id.listnotes);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SetNote", Context.MODE_PRIVATE);
        HashSet<String> SetNotes = (HashSet<String>) sharedPreferences.getStringSet(com.example.madt_lab_4.SetNotes.BASE_NOTE_KEY, null);

        ListNotes.setAdapter(MainActivity.adapter);

        ListNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(DeleteNoteActivity.this)
                        .setTitle("Delete")
                        .setMessage("If you click yes, the note will be deleted. No will bring you back to delete note page")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                MainActivity.notes.remove(position);
                                MainActivity.adapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("notes", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet(com.example.madt_lab_4.SetNotes.BASE_NOTE_KEY, set).apply();
                            }
                        })

                        .setNegativeButton("No", null)
                        .show();

            }
        });
    }
}



