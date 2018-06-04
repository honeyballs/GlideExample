package de.thm.glideexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yannick Bals on 04.06.2018.
 */

public class ListAdapter extends ArrayAdapter<String> {

    public ListAdapter(Context c, ArrayList<String> list) {
        super(c, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String url = getItem(position);
        ImageView myImageView;
        if (convertView == null) {
            myImageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.list_image, parent, false);
        } else {
            myImageView = (ImageView) convertView;
        }

        if (isConnected()) {
            GlideApp.with(getContext()).load(url).placeholder(R.drawable.placeholder).into(myImageView);
        } else {
            Toast.makeText(getContext(), "An internet connection is required to load the images", Toast.LENGTH_SHORT).show();
        }

        return myImageView;
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
