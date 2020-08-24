package com.myfirstwork.myfirstwork.activity.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfirstwork.myfirstwork.R;

public class TestUnit extends AppCompatActivity {

    private RecyclerView numList;
    private TestsAdapter tAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_unit);

        numList = findViewById(R.id.rv_test1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numList.setLayoutManager(layoutManager);

        numList.setHasFixedSize(true);

        tAdapter = new TestsAdapter(6);
        numList.setAdapter(tAdapter);
    }
}
