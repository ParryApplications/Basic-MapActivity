package com.example.paras.mapactivity;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener{

    private GoogleMap mMap;


    private static final LatLng Miranda = new LatLng(28.6928674,77.2080126);
    private static final LatLng SGTBIMIT = new LatLng(28.6924547,77.1891273);
    private static final LatLng Azadpur = new LatLng(28.7127527,77.167232);


    private Marker mMiranda;
    private Marker mSGTBIMIT;
    private Marker mAzadpur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Marker> list = new ArrayList<>();

        mMiranda = mMap.addMarker(new MarkerOptions()
                .position(Miranda)
                .title("Miranda")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        );
        mMiranda.setTag(0);
        list.add(mMiranda);

        mSGTBIMIT = mMap.addMarker(new MarkerOptions()
                .position(SGTBIMIT)
                .title("SGTBIMIT")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                );
        mSGTBIMIT.setTag(0);
        list.add(mSGTBIMIT);

        mAzadpur = mMap.addMarker(new MarkerOptions()
                .position(Azadpur)
                .title("Azadpur")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                );
        mAzadpur.setTag(0);
        list.add(mAzadpur);

        mMap.setOnMarkerClickListener(this);

        for(Marker m : list)
        {
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,2));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//            Log.d("Map",""+m.getTitle());
        }



        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,1));
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        Integer counter = (Integer) marker.getTag();
        if(counter!=null)
        {
            counter=counter+1;
            marker.setTag(counter);
            Toast.makeText(this, marker.getTitle() + "clicked " +counter + " Times", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
