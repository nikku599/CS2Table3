package com.example.android.cs2table;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void Notes(View view){
        Intent intent= new Intent(this, notesActivity.class);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView= findViewById(R.id.listView);
        final List<timeTable> list= new ArrayList<>();
        final timeTableAdapter arrayAdapter= new timeTableAdapter(this, R.layout.customtablelist, list);
        listView.setAdapter(arrayAdapter);

        Calendar calendar= Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        TextView day = findViewById(R.id.day);
        if(dayOfWeek==1){
            day.setText("Sunday");
        }
        if(dayOfWeek==2){
            day.setText("Monday");
        }if(dayOfWeek==3){
            day.setText("Tuesday");
        }if(dayOfWeek==4){
            day.setText("Wednesday");
        }if(dayOfWeek==5){
            day.setText("Thrusday");
        }if(dayOfWeek==6){
            day.setText("Friday");
        }if(dayOfWeek==7){
            day.setText("Saturday");
        }



        ParseQuery<ParseObject> query = ParseQuery.getQuery("timeTable");
        query.whereEqualTo("Day", String.valueOf(dayOfWeek));
        query.findInBackground(new FindCallback<ParseObject>(){
            @Override
            public void done(List<ParseObject> objects, ParseException e){
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                if( e == null ) {
                    for (int i = 0; i < objects.size(); i++) {
                        Object object = objects.get(i);
                        String I = ((ParseObject) object).getString("I");
                        arrayAdapter.add(new timeTable("8:30AM-9:30AM", I));
                        String II = ((ParseObject) object).getString("II");
                        arrayAdapter.add(new timeTable("9:30AM-10:30AM", II));
                        String III = ((ParseObject) object).getString("III");
                        arrayAdapter.add(new timeTable("10:30AM-11:30AM", III));
                        String IV = ((ParseObject) object).getString("IV");
                        arrayAdapter.add(new timeTable("11:30AM-12:30PM", IV));
                        String V = ((ParseObject) object).getString("V");
                        arrayAdapter.add(new timeTable("12:30PM-1:30PM", V));
                        String VI = ((ParseObject) object).getString("VI");
                        arrayAdapter.add(new timeTable("1:30AM-2:30AM", VI));
                        String VII = ((ParseObject) object).getString("VII");
                        arrayAdapter.add(new timeTable("2:30PM-3:30PM", VII));
                        String VIII = ((ParseObject) object).getString("VIII");
                        arrayAdapter.add(new timeTable("3:30PM-4:30PM", VIII));


                    }
                }
                else { /** DO SOMETHING ELSE */ }
            }
        });








    }
}
