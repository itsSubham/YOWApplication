package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;

public class PlaceActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView placeTv;
    private TextView pickupEd,dropEd;
    private Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceActivity.this,HomeActivity.class));
                finish();
            }
        });
        placeTv=toolbar.findViewById(R.id.placeTv);
        placeTv.setText("Choose Location");

        intialize();
    }
    private void intialize(){
        submitbtn=findViewById(R.id.submitbtn);
        pickupEd=findViewById(R.id.pickupEd);
        submitbtn.setOnClickListener(this);
      //  dropEd=findViewById(R.id.dropEd);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setBoundsBias(new LatLngBounds(
                new LatLng(19.3150, 84.7941),
                new LatLng(20.2961, 85.8245)
               ));

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setCountry("IN")
                .build();

        autocompleteFragment.setFilter(autocompleteFilter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                String loc=RegPrefManager.getInstance(PlaceActivity.this).getChooseLocation();
                if(loc.equals("PickUP")) {
                    RegPrefManager.getInstance(PlaceActivity.this).setPickLocationName(place.getName().toString());
                    RegPrefManager.getInstance(PlaceActivity.this).setPickLocationAddress(place.getAddress().toString());
                    RegPrefManager.getInstance(PlaceActivity.this).setPickLocationLatLng(place.getLatLng().toString());

                }else {
                    RegPrefManager.getInstance(PlaceActivity.this).setDropLocationName(place.getName().toString());
                    RegPrefManager.getInstance(PlaceActivity.this).setDropLocationAddress(place.getAddress().toString());
                    RegPrefManager.getInstance(PlaceActivity.this).setDropLocationLatLng(place.getLatLng().toString());
                }
                pickupEd.setText(place.getAddress());
            }
            @Override
            public void onError(Status status) {
                pickupEd.setText(status.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitbtn:
                   // pickUpLocation();
                Intent in = new Intent(PlaceActivity.this, HomeActivity.class);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                startActivity(in);
                PlaceActivity.this.finish();
                break;
        }
    }


}
