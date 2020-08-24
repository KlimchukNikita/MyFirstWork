package com.myfirstwork.myfirstwork.activity.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myfirstwork.myfirstwork.R;

public class Tests extends AppCompatActivity {

    private RecyclerView numbersList;
    private TestsAdapter testsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        numbersList = findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        testsAdapter = new TestsAdapter(6);
        numbersList.setAdapter(testsAdapter);
    }
}
