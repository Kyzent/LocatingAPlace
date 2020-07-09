package com.myapplicationdev.android.locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    Spinner spinner;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        final LatLng poi_Singapore = new LatLng(1.3521, 103.8198);

        final LatLng poi_North = new LatLng(1.461708, 103.813500);
        final LatLng poi_Central = new LatLng(1.300542, 103.841226);
        final LatLng poi_East = new LatLng(1.350057, 103.934452);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    if(map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,15));
                    }
                }else if (i == 1){
                    if(map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,15));
                    }
                }else if(i == 2){
                    if(map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,15));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        15));

                UiSettings ui = map.getUiSettings();

                ui.setZoomControlsEnabled(true);

                final Marker cp_North = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 \n Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                final Marker cp_Central = map.addMarker(new
                    MarkerOptions()
                    .position(poi_Central)
                    .title("Central")
                    .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                            "Operating hours: 11am-8pm\n" +
                            "Tel:67788652")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                final Marker cp_East = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.equals(cp_North)) {
                            Toast.makeText(MainActivity.this, cp_North.getTitle(), Toast.LENGTH_SHORT).show();
                        } else if (marker.equals(cp_Central)) {
                            Toast.makeText(MainActivity.this, cp_Central.getTitle(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, cp_East.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
            }
        });

//        btn1 = findViewById(R.id.button);
//        btn2 = findViewById(R.id.button2);
//        btn3 = findViewById(R.id.button3);

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
//                        15));
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
//                        15));
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
//                        15));
//            }
//        });

    }
}