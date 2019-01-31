package f.ratnatech.yowapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;

public class RequestBookingActivity extends AppCompatActivity implements View.OnClickListener {
    ApiInterface apiService;
    private Toolbar toolbar;
    private Button acceptbtn,cancelbtn;
    private TextView pickupTv,dropTv,rateTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_booking);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestBookingActivity.this,HomeActivity.class));
                finish();
            }
        });



        inialize();
    }
    private void inialize(){
        acceptbtn=findViewById(R.id.acceptbtn);
        cancelbtn=findViewById(R.id.cancelbtn);
        pickupTv=findViewById(R.id.pickupTv);
        dropTv=findViewById(R.id.dropTv);
        rateTv=findViewById(R.id.rateTv);

        rateTv.setText("\u20B9 "+RegPrefManager.getInstance(RequestBookingActivity.this).getRate());

        pickupTv.setText(RegPrefManager.getInstance(this).getPickLocationAddress());
        dropTv.setText(RegPrefManager.getInstance(this).getDropLocationAddress());

        acceptbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.acceptbtn:
                bookingConfirmation();
                break;
        }
    }

    private void bookingConfirmation(){
        // Initializing a new alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Show a message on alert dialog
        builder.setMessage("Booking Confirmation!");
        // Set the positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(RequestBookingActivity.this, SuccessfulActivity.class);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                startActivity(in);
                RequestBookingActivity.this.finish();
              //  finish();
            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();


    }
}
