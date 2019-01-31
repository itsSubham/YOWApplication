package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.GetLoginOTP;
import f.ratnatech.yowapplication.Response.MobileUpdateResponse;
import f.ratnatech.yowapplication.Response.NewLoginSuccessData;
import f.ratnatech.yowapplication.Response.OtpResponse;
import f.ratnatech.yowapplication.Response.RateResponse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileUpdateActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private EditText mobileEd;
    private Button updatebtn;
    ApiInterface apiService;
    private String OTP_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_update);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MobileUpdateActivity.this,EditProfileActivity.class));
                finish();
            }
        });
        apiService =
                ApiClient.getClient().create(ApiInterface.class);
        intialize();
    }

    private void intialize(){
        updatebtn=findViewById(R.id.updatebtn);
        mobileEd=findViewById(R.id.mobileEd);

        updatebtn.setOnClickListener(this);

        mobileEd.setText(RegPrefManager.getInstance(MobileUpdateActivity.this).getMobileNumber());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updatebtn:
                if(mobileEd.length()<10 || mobileEd.length()>10){

                    Toast.makeText(MobileUpdateActivity.this,
                            "Please enter a correct phone number", Toast.LENGTH_SHORT).show();
                }else {
                    getOTPResponse();
                }
                break;
        }
    }

    private void getOTPResponse(){
        Call<GetLoginOTP> getLoginOTPCall=apiService.getLoginResponse(mobileEd.getText().toString().trim());
        getLoginOTPCall.enqueue(new Callback<GetLoginOTP>() {
            @Override
            public void onResponse(Call<GetLoginOTP> call, Response<GetLoginOTP> response) {
                if(response.body().getSuccess()==1){
                    final String otpp = response.body().getData().getOTP();
                    OTP_check=otpp;
                    Toast.makeText(MobileUpdateActivity.this, otpp, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           /* Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                            in.putExtra("phNumber",
                                    phNumber.getText().toString().trim());
                            startActivity(in);*/
                           // verifyOtp();
                            mobileUpdate();
                        }
                    },1000);
                }
            }

            @Override
            public void onFailure(Call<GetLoginOTP> call, Throwable t) {
                Toast.makeText(MobileUpdateActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verifyOtp(){



        Call<OtpResponse> call = apiService.getOtpResponse(mobileEd.getText().toString().trim(),
                OTP_check);
        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {


                int success = response.body().getSuccess();


                int verifiyed = response.body().getData().getVerified();

                Toast.makeText(MobileUpdateActivity.this,
                        "Otp succesfully verifyed", Toast.LENGTH_SHORT).show();

                // if(oTP.equalsIgnoreCase("0")){


                mobileUpdate();





            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

                Toast.makeText(MobileUpdateActivity.this,
                        "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();

            }


        });

    }

    private void mobileUpdate(){
        String id=RegPrefManager.getInstance(MobileUpdateActivity.this).getSaveUser();
        Call<MobileUpdateResponse> call=apiService.getMobileUpdateResponse(id,mobileEd.getText().toString().trim(),OTP_check);
        call.enqueue(new Callback<MobileUpdateResponse>() {
            @Override
            public void onResponse(Call<MobileUpdateResponse> call, Response<MobileUpdateResponse> response) {
                int otp_verify=response.body().getData().getOTP_Verified();
                if(otp_verify==0){
                    Toast.makeText(getApplicationContext(),"OTP Mismatch,Try Again",Toast.LENGTH_LONG).show();
                }
                else if(otp_verify==1){
                    int profile_update=response.body().getData().getProfile_Updated();
                    if(profile_update==1){
                        Toast.makeText(getApplicationContext(),"Mobile Updated",Toast.LENGTH_LONG).show();
                        RegPrefManager.getInstance(MobileUpdateActivity.this).setMobileNumber(mobileEd.getText().toString().trim());
                        RegPrefManager.getInstance(MobileUpdateActivity.this).clearlocation();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                        startActivity(new Intent(MobileUpdateActivity.this, HomeActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Mobile not Updated",Toast.LENGTH_LONG).show();
                        RegPrefManager.getInstance(MobileUpdateActivity.this).clearlocation();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                        startActivity(new Intent(MobileUpdateActivity.this, HomeActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<MobileUpdateResponse> call, Throwable t) {
                Toast.makeText(MobileUpdateActivity.this,
                        "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
