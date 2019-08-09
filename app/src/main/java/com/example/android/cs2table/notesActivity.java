package com.example.android.cs2table;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class notesActivity extends AppCompatActivity {

    public  void newNote(View view){
        Intent intent= new Intent(this, addNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

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

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("notes");
        query1.whereNotEqualTo("date", intialdate);
        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for(ParseObject object:objects){
                    object.deleteInBackground();
                }
            }
        });


        final ListView listView= findViewById(R.id.notesList);
        final List<String> list= new ArrayList<>();
        final ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("notes");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                if(e==null){
                    for (int i = 0; i < objects.size(); i++) {
                        Object object = objects.get(i);
                        String note = ((ParseObject) object).getString("Data");
                        arrayAdapter.add(note);
                    }
                }
            }
        });









    }
}
