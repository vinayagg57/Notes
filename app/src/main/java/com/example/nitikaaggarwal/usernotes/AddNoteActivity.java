package com.example.nitikaaggarwal.usernotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    EditText mTitle, mDescription;
    DatabaseHandler db;
    Button save;
    String titleText = "";
    String descriptionText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = (EditText) findViewById(R.id.title_enetr_et);
        mDescription = (EditText) findViewById(R.id.description_enter_et);
        db = new DatabaseHandler(this);
        save = (Button) findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    titleText = mTitle.getText().toString();
                    descriptionText = mDescription.getText().toString();
                    if(titleText !=null && descriptionText != null)
                    {
                        db.addNote(new Note(titleText, descriptionText));
                        Toast.makeText(getApplicationContext(), "Data Addaed", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddNoteActivity.this, ListActivity.class);
                        intent.putExtra("keyName", "value");
                        startActivity(intent);
                        finish();
                    }
                   else
                    {
                        Toast.makeText(getApplicationContext(), "Data Not filled", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Please fill data", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void onBackPressed() {
        Intent intent = new Intent(AddNoteActivity.this, ListActivity.class);
        startActivity(intent);
        this.finish();

    }

}
