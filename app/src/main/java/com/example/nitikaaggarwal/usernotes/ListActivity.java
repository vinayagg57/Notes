package com.example.nitikaaggarwal.usernotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements ListShowAdapter.OnItemClickListener,
        View.OnClickListener {
    private RecyclerView mRecyclerView;
    DatabaseHandler db;
    public static final int REQUEST_CODE_NOTIFY_ORDER_ACTION = 1000;
    Button createBtn;
    private RecyclerView.Adapter mAdapter;
    private List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = new DatabaseHandler(this);
        noteList = db.getAllNotess();
        createBtn = (Button) findViewById(R.id.create_new_btn);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.hasFixedSize();


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListShowAdapter(this, noteList);
        mRecyclerView.setAdapter(mAdapter);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ListActivity.this, AddNoteActivity.class);
                startActivity(intent1);
            }
        });
//finish();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(Note note) {
        passData(note);
    }

    private void passData(Note note) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("KEY_NOTE_TITLE", note.getTitle());
        intent.putExtra("KEY_NOTE_DESCRIPTION", note.getDescription());
        startActivityForResult(intent, REQUEST_CODE_NOTIFY_ORDER_ACTION);
        finish();
    }


//    private void prepareFestivalData() {
//        for (final Note note : noteList) {
//            noteList.add();
//        }
//    }

}
