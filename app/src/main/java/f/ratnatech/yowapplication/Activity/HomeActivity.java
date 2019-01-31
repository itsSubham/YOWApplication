package f.ratnatech.yowapplication.Activity;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import f.ratnatech.yowapplication.Helper.GPSTracker;
import f.ratnatech.yowapplication.Helper.LocationTrack;
import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.GoogleDistanceResponse;
import f.ratnatech.yowapplication.Response.RateResponse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiClientGoogle;
import f.ratnatech.yowapplication.client.ApiInterface;
import f.ratnatech.yowapplication.fragment.BottomFragment;
import f.ratnatech.yowapplication.fragment.ConfirmBookingFragment;
import f.ratnatech.yowapplication.fragment.DailyRideFragment;
import f.ratnatech.yowapplication.fragment.OutSideFragment;
import f.ratnatech.yowapplication.fragment.RentalFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("All")
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,
        OnMapReadyCallback,LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    ActionBarDrawerToggle toggle;
    private ImageView imageView;
    private TextView nameTv,editProfileTv;
    private GoogleMap mMap;

    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    private TextView pickupTv,dropTv,rateTv;

    private Button rideLaterBut,ridenowbut,ridelaterbut,ridenow1but,ridecontinuebut;
    String zipcode="",base_rate;

    private final static int ALL_PERMISSIONS_RESULT = 101;
    LocationTrack locationTrack;
    String pickUpLatLng,DropLatlng;
    LatLng pickupLatLng,dropLatLng;
    double pLat1,plng1,dLat1,dLng1;
    private CardView idCardView;
    LinearLayout mLinear,header,lnHome;
    TextView mTextView,dailyRideTv,outstationTv,rentalTv;
    ApiInterface apiService,apiService1;
    DailyRideFragment bottomSheetDialog;
    Fragment fragment_bottom;
    ArrayList<GoogleDistanceResponse.Legs> legs;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLinear=(LinearLayout)toolbar.findViewById(R.id.lnTitle);
        mTextView=(TextView)toolbar.findViewById(R.id.tvTitle);
        mLinear.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);
        dailyRideTv=(TextView)toolbar.findViewById(R.id.dailyRideTv);
        outstationTv=(TextView)toolbar.findViewById(R.id.outstationTv);
        rentalTv=(TextView)toolbar.findViewById(R.id.rentalTv);
        dailyRideTv.setBackgroundResource(R.drawable.shape);
        dailyRideTv.setTextColor(Color.BLACK);
        dailyRideTv.setOnClickListener(this);
        outstationTv.setOnClickListener(this);
        rentalTv.setOnClickListener(this);

        apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService1= ApiClientGoogle.getClient().create(ApiInterface.class);
        checkPermission();
        buildGoogleApiClient();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        //for nav header

        View headerview = navigationView.getHeaderView(0);
         nameTv = (TextView) headerview.findViewById(R.id.nameTv);
        nameTv.setText(RegPrefManager.getInstance(this).getMobileNumber());
        imageView=headerview.findViewById(R.id.imageView);
        editProfileTv=headerview.findViewById(R.id.editProfileTv);
        editProfileTv.setOnClickListener(this);

        intialize();
    }

    private void initilizeMap() {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // check if map is created successfully or not


    }

    private void intialize(){
        //rideLaterBut=findViewById(R.id.rideLaterBut);
        ridenowbut=findViewById(R.id.ridenowbut);

        //ridenowbut.setOnClickListener(this);
        idCardView=findViewById(R.id.idCardView);
        rateTv=findViewById(R.id.rateTv);
        pickupTv=findViewById(R.id.pickupTv);
        dropTv=findViewById(R.id.dropTv);
        lnHome=findViewById(R.id.lnHome);
        pickupTv.setOnClickListener(this);
        dropTv.setOnClickListener(this);
        ridelaterbut=findViewById(R.id.ridelaterbut);
        ridenow1but=findViewById(R.id.ridenow1but);
        ridecontinuebut=findViewById(R.id.ridecontinuebut);

    }
    @Override
    protected void onResume() {
        super.onResume();
       // buildGoogleApiClient();
        initilizeMap();
        dailyRide();



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        displaySelectedScreen(item.getItemId());

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editProfileTv:
                startActivity(new Intent(HomeActivity.this,EditProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
               // HomeActivity.this.finish();
                break;
          /*  case R.id.ridenowbut:
                ConfirmBookingFragment flightBottomFragment1=new ConfirmBookingFragment();
                flightBottomFragment1.show(getSupportFragmentManager(),flightBottomFragment1.getTag());
                break;*/
            case R.id.pickupTv:
                RegPrefManager.getInstance(HomeActivity.this).setChooseLocation("PickUP");
                startActivity(new Intent(HomeActivity.this,PlaceActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                break;
            case R.id.dropTv:
                RegPrefManager.getInstance(HomeActivity.this).setChooseLocation("Drop");
                startActivity(new Intent(HomeActivity.this,PlaceActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                break;
            case R.id.dailyRideTv:
                //dailyRide();
                lnHome.setVisibility(View.VISIBLE);
                dailyRideTv.setBackgroundResource(R.drawable.shape);
                dailyRideTv.setTextColor(Color.BLACK);
                outstationTv.setTextColor(Color.WHITE);
                rentalTv.setTextColor(Color.WHITE);
                outstationTv.setBackgroundResource(0);
                rentalTv.setBackgroundResource(0);
                BottomFragment selectedFragment = new BottomFragment();
              //  selectedFragment.newInstance(zipcode);
                // selectedFragment.setTitle(String.valueOf(menuItem.getTitle()));
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.rideFrame, selectedFragment);
                fragmentTransaction.commit();
                ridelaterbut.setVisibility(View.GONE);
                ridenow1but.setVisibility(View.GONE);
                ridenowbut.setVisibility(View.VISIBLE);
                ridecontinuebut.setVisibility(View.GONE);
                break;
            case R.id.outstationTv:
                outstationTv.setBackgroundResource(R.drawable.shape);
                outstationTv.setTextColor(Color.BLACK);
                dailyRideTv.setTextColor(Color.WHITE);
                rentalTv.setTextColor(Color.WHITE);
                rentalTv.setBackgroundResource(0);
                dailyRideTv.setBackgroundResource(0);
                OutSideFragment outsideFragment = new OutSideFragment();
                //outsideFragment.newInstance(zipcode);

                // selectedFragment.setTitle(String.valueOf(menuItem.getTitle()));
                FragmentTransaction fragmentOutTransaction = getSupportFragmentManager().beginTransaction();
                fragmentOutTransaction.replace(R.id.rideFrame, outsideFragment);
                fragmentOutTransaction.commit();
                ridenowbut.setVisibility(View.GONE);
                ridelaterbut.setVisibility(View.VISIBLE);
                ridenow1but.setVisibility(View.VISIBLE);
                ridecontinuebut.setVisibility(View.GONE);
                break;
            case R.id.rentalTv:
                rentalTv.setBackgroundResource(R.drawable.shape);
                rentalTv.setTextColor(Color.BLACK);
                outstationTv.setBackgroundResource(0);
                dailyRideTv.setTextColor(Color.WHITE);
                outstationTv.setTextColor(Color.WHITE);
                dailyRideTv.setBackgroundResource(0);
                RentalFragment rentalFragment=new RentalFragment();
               // rentalFragment.newInstance(zipcode);
                FragmentTransaction fragmentRentalTransaction = getSupportFragmentManager().beginTransaction();
                fragmentRentalTransaction.replace(R.id.rideFrame, rentalFragment);
                fragmentRentalTransaction.commit();
                ridenowbut.setVisibility(View.GONE);
                ridelaterbut.setVisibility(View.GONE);
                ridenow1but.setVisibility(View.GONE);
                ridecontinuebut.setVisibility(View.VISIBLE);
                break;
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device.
     * This method will only be triggered once the user has installed
     Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getBaseContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission(){
        if ((checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)||(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    ALL_PERMISSIONS_RESULT);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant

            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.
                    Toast.makeText(getApplicationContext(),"Allow",Toast.LENGTH_LONG).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }

    /*public void setUp(){
        locationTrack = new LocationTrack(HomeActivity.this);


        if (locationTrack.canGetLocation()) {


            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();

            LatLng TutorialsPoint = new LatLng(latitude, longitude);
            mMap.addMarker(new
                    MarkerOptions().position(TutorialsPoint).title("Current location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(TutorialsPoint)), DEFAULT_ZOOM);

            Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        } else {

            locationTrack.showSettingsAlert();
        }
    }*/

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        //creating fragment object
        switch (itemId) {

            case R.id.tvLogout:
                Intent intent_ordr = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent_ordr);
                RegPrefManager.getInstance(this).logout();
                finish();
                break;
            case R.id.tvHistory:
                startActivity(new Intent(HomeActivity.this,HistoryActivity.class));
                finish();
                break;
            case R.id.tvWallet:
                startActivity(new Intent(HomeActivity.this,WalletActivity.class));
                finish();
                break;
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        pickUpLatLng=RegPrefManager.getInstance(HomeActivity.this).getPickLocationLatLng();
        DropLatlng=RegPrefManager.getInstance(HomeActivity.this).getDropLocationLatLng();


      //  Log.d("Tag====>",pickUpLatLng);
        if(pickUpLatLng!=null){
            if(DropLatlng!=null){
                //both pick up and drop location find

                dropTv.setText("Drop==>"+RegPrefManager.getInstance(HomeActivity.this).getDropLocationName());
                if (pickUpLatLng!=null) {
                    pickupTv.setText("Pick Up==>" + RegPrefManager.getInstance(HomeActivity.this).getPickLocationName());
                }
                String myArray[] = DropLatlng.split("\\(");
                String[] latngValue = myArray[1].split("\\)");
                String[] pickupLocation = latngValue[0].split(",");
                Double lat = Double.valueOf(pickupLocation[0]);
                Double lng = Double.valueOf(pickupLocation[1]);
                dLat1=lat;
                dLng1=lng;
                dropLatLng = new LatLng(lat, lng);
              //  distanceValue=CalculationByDistance(pickupLatLng,dropLatLng);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(dropLatLng);
                markerOptions.title("Drop");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mCurrLocationMarker = mMap.addMarker(markerOptions);

                //move map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(dropLatLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

               /* if(pickUpLatLng!=null) {
                    RateCalculate();
                }*/
               // GoogleDistance();

            }else {
                pickupTv.setText("Pick Up==>"+RegPrefManager.getInstance(HomeActivity.this).getPickLocationName());
                if(DropLatlng!=null){
                    dropTv.setText("Drop==>"+RegPrefManager.getInstance(HomeActivity.this).getDropLocationName());
                }
                String myArray[] = pickUpLatLng.split("\\(");
                String[] latngValue = myArray[1].split("\\)");
                String[] pickupLocation = latngValue[0].split(",");
                Double lat = Double.valueOf(pickupLocation[0]);
                Double lng = Double.valueOf(pickupLocation[1]);
                pLat1=lat;
                plng1=lng;

                pickupLatLng = new LatLng(lat, lng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(pickupLatLng);
                markerOptions.title("pick up");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mCurrLocationMarker = mMap.addMarker(markerOptions);

                //move map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(pickupLatLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        }else {
         /*   //Place current location marker
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mCurrLocationMarker = mMap.addMarker(markerOptions);

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));*/
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mCurrLocationMarker = mMap.addMarker(markerOptions);

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
       //     mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            //stop location updates
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, HomeActivity.this);
            }
           /* pLat1=location.getLatitude();
            plng1=location.getLongitude();


            //dailyRide();
            try {
                pinCodeGenerate(pLat1,plng1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            dailyRide();*/
        }



    }

    private void GoogleDistance(){
        Call<GoogleDistanceResponse> call=apiService1.getDistanceDuration("Toronto","Montreal","AIzaSyA2gsRCC_yoXBHJkivmgjFCdupyzsqQnkI");
        call.enqueue(new Callback<GoogleDistanceResponse>() {
            @Override
            public void onResponse(Call<GoogleDistanceResponse> call, Response<GoogleDistanceResponse> response) {
                legs=new ArrayList<>();
                GoogleDistanceResponse googleDistanceResponse=response.body();
                String status=response.body().getStatus();
                if(status.equals("OK")) {
                    Toast.makeText(HomeActivity.this, "OK",
                            Toast.LENGTH_SHORT).show();
                    ArrayList<GoogleDistanceResponse.Routes> routes = response.body().getRoutes();

                        legs = response.body().getRoutes().get(0).getLegs();


                    if (!legs.isEmpty()) {
                      //  for (int i = 0; i < legs.size(); i++) {
                            String text = legs.get(0).getDistance().getText();
                        //}
                    }
                }
                else {
                    Toast.makeText(HomeActivity.this, "OVER_QUERY_LIMIT",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GoogleDistanceResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void pinCodeGenerate(Double latitude,Double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        Address address=null;
        String addr="";

        String city="";
        String state="";
        if (addresses != null && addresses.size() > 0) {

            addr = addresses.get(0).getAddressLine(0) + "," + addresses.get(0).getSubAdminArea();
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();

            for (int i = 0; i < addresses.size(); i++) {
                address = addresses.get(i);
                if (address.getPostalCode() != null) {
                    zipcode = address.getPostalCode();
                    RegPrefManager.getInstance(HomeActivity.this).setZipcode(zipcode);
                    Log.d("Tag==>","zipcode===>"+zipcode);
                    break;
                }

            }
        }
    }

    private void RateCalculate(){
        String myArray[] = pickUpLatLng.split("\\(");
        String[] latngValue = myArray[1].split("\\)");
        String[] pickupLocation = latngValue[0].split(",");
        Double lat = Double.valueOf(pickupLocation[0]);
        Double lng = Double.valueOf(pickupLocation[1]);
        pLat1=lat;
        plng1=lng;


        String myArray1[] = DropLatlng.split("\\(");
        String[] latngValue1 = myArray1[1].split("\\)");
        String[] pickupLocation1 = latngValue1[0].split(",");
        Double lat1 = Double.valueOf(pickupLocation1[0]);
        Double lng1 = Double.valueOf(pickupLocation1[1]);
        dLat1=lat1;
        dLng1=lng1;


        try {
            pinCodeGenerate(lat,lng);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Tag","LatLng==>"+pLat1+","+plng1+","+dLat1+" ,"+dLng1);
      double distance_value=distance(pLat1,plng1,dLat1,dLng1);
      Log.d("Tag","distance==>"+distance_value);
      String[] distanceArray=String.valueOf(distance_value).split("\\.");
      String dValue=distanceArray[0];
      Log.d("Tag","distance==>"+dValue);



      Call<RateResponse> call = apiService.getRateResponse(zipcode,dValue);
      call.enqueue(new Callback<RateResponse>() {
          @Override
          public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
              int success=response.body().getSuccess();
              if(success==1){
                  //idCardView.setVisibility(View.VISIBLE);

                   base_rate=response.body().getData().getBase_Rate();
                  String km_rate=response.body().getData().getKM_Rate();
                  String waiting=response.body().getData().getWaiting_Charge();
                  RegPrefManager.getInstance(HomeActivity.this).setRate(base_rate);
                //  rateTv.setText("Base Rate==> "+base_rate);
                 // alertDialog1(base_rate);
              }else {
                  base_rate="Rate unavailable for this area or exceeds the KM range.";
                  RegPrefManager.getInstance(HomeActivity.this).setRate(base_rate);
                 // idCardView.setVisibility(View.VISIBLE);
                  //rateTv.setText("Rate unavailable for this area or exceeds the KM range.");
                  //alertDialog1("Rate unavailable for this area or exceeds the KM range.");
                  /*Toast.makeText(getApplicationContext(),
                          "Rate unavailable for this area or exceeds the KM range."
                          ,Toast.LENGTH_LONG).show();*/
              }
          }

          @Override
          public void onFailure(Call<RateResponse> call, Throwable t) {
              Toast.makeText(HomeActivity.this, "Some thing went wrong",
                      Toast.LENGTH_SHORT).show();
          }
      });


    }


  /*  private void alertDialog1(String rate){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Rate Update");
        alertDialogBuilder.setMessage(rate);
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                               finish();
                            }
                        });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/

   // Here getting distance in kilometers (km)

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }



    @Override
    public void onStart(){
        super.onStart();
        if(this.mGoogleApiClient != null){
            this.mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

  /*     // buildGoogleApiClient();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
           // if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,HomeActivity.this);
            //}

        }
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        if(mGoogleApiClient.isConnected()){
            Log.d("Tag","Connected===>");
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else{
            Log.d("Tag","Google_Api_Client: It was NOT connected on (onConnected) function, It is definetly bugged.");
            buildGoogleApiClient();

        }




    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void dailyRide(){
        /*try {
            pinCodeGenerate(pLat1,plng1);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        BottomFragment selectedFragment = new BottomFragment();
        //selectedFragment.newInstance("751001");

        // selectedFragment.setTitle(String.valueOf(menuItem.getTitle()));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rideFrame, selectedFragment);
        fragmentTransaction.commit();

        GoogleDistance();
    }
}
