package de.thm.glideexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yannick Bals on 04.06.2018.
 */

public class ListActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 37;

    private EditText urlText;
    private Button addButton;
    private ListView listView;
    private ListAdapter adapter;

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
        addButton.setOnClickListener(new OnAddClickedListener());
        listView = findViewById(R.id.listView);
        adapter = new ListAdapter(this, urls);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // We access the internet in the adapter, so only set the adapter if we have the permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET}, REQUEST_PERMISSIONS);
        } else {
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            // If the grantResultsArray is empty, no permissions have been granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // We got the permission so we can set the adapter
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Please grant the permissions to run this app.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class OnAddClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            adapter.add(urlText.getText().toString());
            urlText.setText("");
        }
    }
}
