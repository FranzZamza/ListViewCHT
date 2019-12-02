package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ListView listView;
    static ExpandableListView expListView;
    static ArrayList<String> children1 = new ArrayList<String>();
    static ArrayList<String> children2 = new ArrayList<String>();
    static ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Информация о Компании");
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        expListView = (ExpandableListView) findViewById(R.id.exListView);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.exListView);
        ExpListAdapter adapter = new ExpListAdapter(getApplicationContext(), groups);
        listView.setAdapter(adapter);
        TakeJson takeJson = new TakeJson();
        takeJson.execute();
    }
}