package com.example.android.cs2table;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addNoteActivity extends AppCompatActivity {




    public void submitNote(View view){
        Calendar c= Calendar.getInstance();
        String dt= c.getTime().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(sdf.parse(dt));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String intialdate=sdf.format(c.getTime());
        c.add(Calendar.DATE, 1);  // number of days to add
        String newDate= sdf.format(c.getTime());


        EditText noteData= findViewById(R.id.noteData);

        ParseObject notes= new ParseObject("notes");
        notes.put("Data", noteData.getText().toString());
        notes.put("date", intialdate);
        notes.saveInBackground();


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

    }
}
