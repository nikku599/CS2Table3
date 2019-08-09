package com.example.android.cs2table;

public class timeTable {
    String time, subject;

    public timeTable(String time, String subject){
        this.time=time;
        this.subject=subject;
    }
    public String getTime(){
        return time;
    }
    public String getSubject(){
        return subject;
    }

}
