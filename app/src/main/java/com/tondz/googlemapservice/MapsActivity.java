package com.tondz.googlemapservice;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.icu.text.DecimalFormat;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tondz.googlemapservice.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng cs2 = new LatLng(20.942465868325634, 106.06000269755162);
        mMap.addMarker(new MarkerOptions().position(cs2).title("Cơ sở mỹ hào"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cs2));
        mMap.addMarker(new MarkerOptions().position(new LatLng(20.94862381066199, 106.06137336061673)).title("Cơ sở khoái châu"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(20.936666595683686, 106.31259405336848)).title("Cơ sở hải dương"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(20.93298524825306, 106.0085050251927)).title("Nhà tôi"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        float[] results = new float[1];
        LatLng myLocation = new LatLng(21.020229471479325, 105.86053771522451);
        Location.distanceBetween(cs2.latitude, cs2.longitude,
                myLocation.latitude, myLocation.longitude,
                results);
        Toast.makeText(getApplicationContext(), "Khoảng cách là: " + results[0]/1000 + "km", Toast.LENGTH_LONG).show();


    }

}