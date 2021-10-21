package com.namangarg.splitwiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    int friendsCount = 0; // no of vertices.
    HashMap<Integer, String> map = new HashMap<>();
    boolean start = true;
    int temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.friends_count);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        textView.setText("Enter number of friends.");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(start) {
                    if (!editText.getText().toString().isEmpty()) {
                        friendsCount = Integer.parseInt(editText.getText().toString());
                        start = false;
                        editText.setText("");
                        textView.setText("Enter " + friendsCount + " friends name separated by comma and whitespace.");
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter number of friends.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (!editText.getText().toString().isEmpty()) {
                        String [] strings = editText.getText().toString().split(", ");

                        for (String p : strings){
                            map.put(temp++, p);
                        }

                        Intent intent = new Intent(MainActivity.this, InputActivity.class);
                        intent.putExtra("hashMap", map);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Please enter friends name.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}