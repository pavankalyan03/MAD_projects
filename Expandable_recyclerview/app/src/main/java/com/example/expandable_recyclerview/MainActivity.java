package com.example.expandable_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Datamodel> mList;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        //list1
        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add("Jams and Honey");
        nestedList1.add("Pickles and Chutneys");


        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add("Book");
        nestedList2.add("Pen");
        nestedList2.add("Office Chair");


        List<String> nestedList3 = new ArrayList<>();
        nestedList3.add("Decorates");
        nestedList3.add("Tea Table");
        nestedList3.add("Wall Paint");

        List<String> nestedList4 = new ArrayList<>();
        nestedList4.add("Pasta");
        nestedList4.add("Kurkure");

        List<String> nestedList5 = new ArrayList<>();
        nestedList5.add("Jams and Honey");


        List<String> nestedList6 = new ArrayList<>();
        nestedList6.add("Pasta");



        List<String> nestedList7 = new ArrayList<>();
        nestedList7.add("Decorates");
        nestedList7.add("Tea Table");


        mList.add(new Datamodel(nestedList1 , "Instant Food and Noodles"));
        mList.add(new Datamodel( nestedList2,"Stationary"));
        mList.add(new Datamodel( nestedList3,"Home Care"));
        mList.add(new Datamodel(nestedList4 ,"Grocery & Staples"));
        mList.add(new Datamodel(nestedList5,"Pet Care"));
        mList.add(new Datamodel(nestedList6,"Baby Care"));
        mList.add(new Datamodel(nestedList7 ,"Personal Care"));

        adapter = new ItemAdapter(mList);
        recyclerView.setAdapter(adapter);
    }
}