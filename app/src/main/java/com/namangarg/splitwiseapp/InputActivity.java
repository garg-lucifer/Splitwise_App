package com.namangarg.splitwiseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.namangarg.splitwiseapp.Utils.Minimze_flow;

import java.util.ArrayList;
import java.util.HashMap;

public class InputActivity extends AppCompatActivity implements Adapter.ClickEvent{

    RecyclerView recyclerView;
    int[][] graph;
    Adapter adapter;
    Button calculate;
    ArrayList<InputData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        recyclerView = findViewById(R.id.list_view);
        calculate = findViewById(R.id.calculate);
        calculate = findViewById(R.id.calculate);

        Intent intent = getIntent();
        final HashMap<Integer, String> map = (HashMap<Integer, String>) intent.getSerializableExtra("hashMap");

        final int vertices = map.size();
        graph = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++){
            for (int j = 0; j < vertices; j++){
                if(i != j){
                    String s = map.get(i) + " needs to pay to " + map.get(j);
                    list.add(new InputData(s, i,j, 0));
                }
            }
        }

        adapter = new Adapter(this,list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Minimze_flow minimze_flow = new Minimze_flow();
                graph  = minimze_flow.minCashFlow(graph);
                list.clear();
                for (int i = 0; i < vertices; i++){
                    for (int j = 0; j < vertices; j++){
                        if(i != j){
                            String s = map.get(i) + " needs to pay to " + map.get(j);
                            list.add(new InputData(s, i,j, graph[i][j]));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(final InputData inputData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter amount");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                graph[inputData.getI()][inputData.getJ()] = Integer.parseInt(input.getText().toString());
                inputData.setMoney(Integer.parseInt(input.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }
}