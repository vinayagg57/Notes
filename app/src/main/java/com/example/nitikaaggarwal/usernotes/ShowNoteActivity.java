package com.example.nitikaaggarwal.usernotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowNoteActivity extends AppCompatActivity {
Button editBtn;
    TextView mTitle, mDescription;
    String title,description;
    DatabaseHandler db;
    public static final int REQUEST_CODE_NOTIFY_ORDER_ACTION = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        mTitle = (TextView) findViewById(R.id.title_tv);
        mDescription = (TextView) findViewById(R.id.description_tv);
        editBtn = (Button)findViewById(R.id.edit_btn);
        readIntent();
       mTitle.setText(title);
        mDescription.setText(description);
//
//        if (db.getNote(title).getDescription()!=null ) {
//            mDescription.setText(db.getNote(title).getDescription());
//        }else
//        {
//            mDescription.setText(" No Name found for this number");
//        }
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowNoteActivity.this, EditNoteActivity.class);
                intent.putExtra("KEY_NOTE_TITLE", title);
                intent.putExtra("KEY_NOTE_DESCRIPTION", description);
                startActivityForResult(intent, REQUEST_CODE_NOTIFY_ORDER_ACTION);
            }
        });

    }
    private void readIntent() {
        title = getIntent().getStringExtra("KEY_NOTE_TITLE");
        description = getIntent().getStringExtra("KEY_NOTE_DESCRIPTION");

    }


}
