package com.example.nitikaaggarwal.usernotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {
Button doneBtn;
    EditText mTitle,mDescription;
    String getTitleInfo,getDescriptionInfo;
    DatabaseHandler db;
    View toolbarView;
    ImageView backToolbarImv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

toolbarView = (View)findViewById(R.id.toolbar_view);
        backToolbarImv = (ImageView)findViewById(R.id.back_btn_toolbar);

        mTitle = (EditText)findViewById(R.id.editText_toolbar);
        mDescription = (EditText)findViewById(R.id.description_edit_et);
        doneBtn = (Button) findViewById(R.id.edit_save_btn);
        readIntent();
        db = new DatabaseHandler(this);
        mTitle.setText(getTitleInfo);
        mDescription.setText(getDescriptionInfo);
        backToolbarImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        doneBtn.setText("EDIT");
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if (doneBtn.getText() == "EDIT") {
                        backToolbarImv.setVisibility(View.GONE);
                        mDescription.setFocusable(true);
                        mDescription.setFocusableInTouchMode(true);
                        doneBtn.setText("DONE");

                    }
                    if(doneBtn.getText() == "DONE") {
//                        backToolbarImv.setVisibility(View.GONE);
//                        mDescription.setFocusable(true);
//                        mDescription.setFocusableInTouchMode(true);
//                        doneBtn.setText("DONE");
                        try {
                            String updateTitle, updateDescription;
                            updateTitle = mTitle.getText().toString();
                            updateDescription = mDescription.getText().toString();
                            db.updateNote(new Note(updateTitle, updateDescription));
                            Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(EditNoteActivity.this, ListActivity.class);
                            intent.putExtra("keyName", "value");
                            startActivity(intent);
                            finish();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

            }
        });

    }
    public void onBackPressed() {
        Intent intent = new Intent(EditNoteActivity.this, ListActivity.class);
        startActivity(intent);
        this.finish();

    }
    private void readIntent() {
        getTitleInfo = getIntent().getStringExtra("KEY_NOTE_TITLE");
        getDescriptionInfo = getIntent().getStringExtra("KEY_NOTE_DESCRIPTION");

    }

}
