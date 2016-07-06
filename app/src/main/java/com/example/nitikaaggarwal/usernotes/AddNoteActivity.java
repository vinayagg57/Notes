package com.example.nitikaaggarwal.usernotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    EditText mTitle, mDescription;
    DatabaseHandler db;
    Button save;
    String titleText = "";
    String descriptionText = "";
    private AlertDialog dialogBuilder;

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

                titleText = mTitle.getText().toString();
                descriptionText = mDescription.getText().toString();
                if (mTitle.getText().toString().isEmpty())
                {
                    //     Toast.makeText(getApplicationContext(), "Title is mandatory", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this)
                            .setCancelable(false)
                            .setMessage("Title is mandatory field")
                            .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    dialogBuilder = builder.create();
                    if (!isFinishing())
                        dialogBuilder.show();
                }

                else {
                    try {
                        db.addNote(new Note(titleText, descriptionText));
                        Intent intent = new Intent(AddNoteActivity.this, ListActivity.class);
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
        Intent intent = new Intent(AddNoteActivity.this, ListActivity.class);
        startActivity(intent);
        this.finish();
    }

}
