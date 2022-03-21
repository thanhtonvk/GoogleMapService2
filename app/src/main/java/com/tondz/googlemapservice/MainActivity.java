package com.tondz.googlemapservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkInternet()&&internetIsConnected()){
            Toast.makeText(getApplicationContext(),"Có kết nối interet",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),MapsActivity.class));
        }else{
            Toast.makeText(getApplicationContext(),"Không có kết nối internet",Toast.LENGTH_LONG).show();
        }
    }

    //check internet
    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null;
    }
    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }
}