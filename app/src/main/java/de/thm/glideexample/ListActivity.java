package de.thm.glideexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Yannick Bals on 04.06.2018.
 */

public class ListActivity extends AppCompatActivity {

    private EditText urlText;
    private Button addButton;
    private ListView listView;

    private ArrayList<String> urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_layout);

        urls = new ArrayList<>();
        urls.add("https://placeimg.com/1000/480/tech");
        urls.add("https://placeimg.com/1000/480/nature");
        urls.add("https://placeimg.com/1000/480/animals");

        urlText = findViewById(R.id.urlText);
        addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.listView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
