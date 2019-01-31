package f.ratnatech.yowapplication.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.ratnatech.yowapplication.Helper.RegPrefManager;

import f.ratnatech.yowapplication.R;

public class SuccessfulActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Toolbar toolbar;
    private String PickupLatLng,DropLatLng;
    double pLat1,plng1,dLat1,dLng1;
    private TextView pickupTv,dropTv;
    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    LatLng pickupLatLng,dropLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(SuccessfulActivity.this,HomeActivity.class));
                finish();

            }
        });
        MarkerPoints =  new ArrayList<>();

        intialize();
    }

    private void intialize(){
        pickupTv=findViewById(R.id.pickupTv);
        dropTv=findViewById(R.id.dropTv);


        pickupTv.setText(RegPrefManager.getInstance(this).getPickLocationName());
        dropTv.setText(RegPrefManager.getInstance(this).getDropLocationName());

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

     /*   LatLng origin = new LatLng(pLat1, plng1);
        LatLng destination = new LatLng(dLat1, dLng1);
        DrawRouteMaps.getInstance(this)
                .draw(origin, destination, mMap);
        DrawMarker.getInstance(this).draw(mMap, origin, R.drawable.marker_a, "Origin Location");
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.marker_b, "Destination Location");
*/
      /*  LatLngBounds bounds = new LatLngBounds.Builder()
                .include(origin)
                .include(destination).build();
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 250, 30));*/
        LatLng origin = new LatLng(pLat1, plng1);
        LatLng destination = new LatLng(dLat1, dLng1);
        DrawRouteMaps.getInstance(this)
                .draw(origin, destination, mMap);
        DrawMarker.getInstance(this).draw(mMap, origin, R.drawable.marker_a, RegPrefManager.getInstance(this).getPickLocationName());
        DrawMarker.getInstance(this).draw(mMap, destination, R.drawable.marker_b, RegPrefManager.getInstance(this).getDropLocationName());
        mMap
                .addPolyline((new PolylineOptions())
                        .add(pickupLatLng, dropLatLng,pickupLatLng).width(5).color(Color.RED)
                        .geodesic(true));

        // move camera to zoom on map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pickupLatLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));




    }





    @Override
    protected void onResume() {
        super.onResume();
        // buildGoogleApiClient();
        initilizeMap();


        PickupLatLng= RegPrefManager.getInstance(SuccessfulActivity.this).getPickLocationLatLng();
        DropLatLng=RegPrefManager.getInstance(SuccessfulActivity.this).getDropLocationLatLng();

        if (DropLatLng!=null){
            String myArray[] = DropLatLng.split("\\(");
            String[] latngValue = myArray[1].split("\\)");
            String[] pickupLocation = latngValue[0].split(",");
            Double lat = Double.valueOf(pickupLocation[0]);
            Double lng = Double.valueOf(pickupLocation[1]);

            dLat1=lat;
            dLng1=lng;

            dropLatLng = new LatLng(lat, lng);
        }

        if(PickupLatLng!=null){
            String myArray[] = PickupLatLng.split("\\(");
            String[] latngValue = myArray[1].split("\\)");
            String[] pickupLocation = latngValue[0].split(",");
            Double lat = Double.valueOf(pickupLocation[0]);
            Double lng = Double.valueOf(pickupLocation[1]);
            pLat1=lat;
            plng1=lng;

            pickupLatLng = new LatLng(lat, lng);
        }



    }

    private void initilizeMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // check if map is created successfully or not




    }





}
