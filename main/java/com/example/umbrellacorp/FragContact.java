package com.example.umbrellacorp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class FragContact extends Fragment implements OnMapReadyCallback
{
    View view;
    GoogleMap map;

    FirebaseDatabase db;
    DatabaseReference ref;

    DatabaseReference ref_2;
    double lat;
    double lng;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contact, container, false);
        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById((R.id.map));
        smf.getMapAsync(this);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference().child("Map");
        return view;
    }
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String,Double> data = (HashMap<String, Double>) dataSnapshot.getValue();
                lat = data.get("Lat");
                lng = data.get("Lng");
                LatLng location  = new LatLng(lat,lng);
                map.addMarker(new MarkerOptions().position(location).title("Umbrella Corp"));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(location,17));
                TextView tv1 = view.findViewById(R.id.address);
                tv1.setText("Umbrella Corp."+ " "+ "Latitude:" +" "+lat+" "+ "Longtitude:" + " "+lng);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.add("Satellite View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add("Normal View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add("Hybrid View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        menu.add("Terrain View").setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String str = item.getTitle().toString();
        if (str.equals("Satellite View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        if (str.equals("Normal View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        if (str.equals("Hybrid View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        if (str.equals("Terrain View"))
        {
            map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        return super.onOptionsItemSelected(item);
    }
}
