package com.example.nitikaaggarwal.usernotes;

/**
 * Created by nitikaaggarwal on 04/07/16.
 */
public class Note {
    String _title;
    String _description;

    // Empty constructor
    public Note(){

    }
    // constructor
    public Note( String title){
        this._title = title;
    }


    public Note(String title, String description){
        this._title = title;
        this._description = description;
    }


    // getting title
    public String getTitle(){
        return this._title;
    }

    // setting title
    public void setTitle(String title){
        this._title = title;
    }

    // getting descrition
    public String getDescription(){
        return this._description;
    }

    // setting descrition
    public void setDiscription(String descrition){
        this._description = descrition;
    }
}
