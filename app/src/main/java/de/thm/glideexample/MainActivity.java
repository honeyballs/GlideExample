package de.thm.glideexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.LibraryGlideModule;

/**
 * Created by Yannick Bals on 04.06.2018.
 */

public class MainActivity extends AppCompatActivity {

    private EditText urlEdit;
    private Button loadButton;
    private ImageView imageView;
    private Button listButton;

    private static final String URL = "https://placeimg.com/640/480/tech";
    private static final int PERMISSIONS_REQUEST_INTERNET = 42;
    private static final int PERMISSIONS_REQUEST_NETWORK_STATE = 43;
    private String currentUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        urlEdit = findViewById(R.id.urlText);
        urlEdit.setText(URL);

        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new OnLoadClickListener());
        imageView = findViewById(R.id.imageView);
        listButton = findViewById(R.id.listButton);

    }


    private void loadImage(String url) {
        // If we do not have the permission to access the internet we have to acquire it
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            Log.e("permission: ", "not granted");
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, PERMISSIONS_REQUEST_INTERNET);
        } else if (isConnected()) {
            // GlideApp is the generated API provided by MyGlideAppModule
            GlideApp.with(this).load(url).into(imageView);
        } else {
            Toast.makeText(this, "No internet connection available", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnected() {
        // If we do not have the permission to access the network state we have to acquire it
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, PERMISSIONS_REQUEST_NETWORK_STATE);
            return false;
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_INTERNET || requestCode == PERMISSIONS_REQUEST_NETWORK_STATE) {
            // If the grantResultsArray is empty, no permissions have been granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We got the permission so we can call the function again
                loadImage(currentUrl);
            } else {
                Toast.makeText(this, "Please grant the permissions to run this app.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class OnLoadClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String url = urlEdit.getText().toString();
            currentUrl = url;
            loadImage(url);
        }
    }
}
