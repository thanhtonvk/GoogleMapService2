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
        Location myLoc = mMap.getMyLocation();

        double distanceinmeters = meters(new LatLng(20.943818165357627, 106.05994283900276).latitude,new LatLng(20.943818165357627, 106.05994283900276).longitude, new LatLng(20.932742770677375, 106.00856769017439).latitude,new LatLng(20.932742770677375, 106.00856769017439).longitude);
        Toast.makeText(getApplicationContext(), "Khoảng cách là: " + distanceinmeters/1000 + "km", Toast.LENGTH_LONG).show();


    }
    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    public static double meters(double lt1, double ln1, double lt2, double ln2) {
        double x = lt1 * d2r;
        double y = lt2 * d2r;
        return Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (ln1 - ln2))) * d2km;
    }
}