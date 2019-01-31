package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.GetLoginOTP;
import f.ratnatech.yowapplication.Response.OtpResponse;
import f.ratnatech.yowapplication.Response.SaveUserResponse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidateOTPActivity extends AppCompatActivity {
    private String oTP,pHNUMBER,OTP_check;
    private TextView textPh;
    private AppCompatEditText otp;
    private Button btnNext;
    ApiInterface apiService;
    private Toolbar toolbar;
    private int validate_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_otp);
        apiService =
                ApiClient.getClient().create(ApiInterface.class);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ValidateOTPActivity.this,LoginActivity.class));
                finish();
            }
        });

        Intent intent = getIntent();
        if(intent!=null){
            oTP = intent.getStringExtra("id");
            pHNUMBER = intent.getStringExtra("phNumber");

        }

        textPh = findViewById(R.id.text1);
        otp = findViewById(R.id.otp);
        btnNext = findViewById(R.id.verify);

        RegPrefManager.getInstance(ValidateOTPActivity.this).setOTP(oTP);
        validate_user_id=Integer.valueOf(RegPrefManager.getInstance(ValidateOTPActivity.this).getSaveUser());
        Toast.makeText(ValidateOTPActivity.this, oTP, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                otp.setText(oTP);
            }
        },1000);

        textPh.setText("We will auto verify the otp send to " + "\n"
                + pHNUMBER);



        // getLoginResponse();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                verifyOtp();

            }
        });
    }

    private void verifyOtp(){



        Call<OtpResponse> call = apiService.getOtpResponse(pHNUMBER,
                oTP);
        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {


                int success = response.body().getSuccess();


                int verifiyed = response.body().getData().getVerified();

                    Toast.makeText(ValidateOTPActivity.this,
                            "Otp succesfully verifyed", Toast.LENGTH_SHORT).show();

                    // if(oTP.equalsIgnoreCase("0")){
                    Intent in = new Intent(ValidateOTPActivity.this, EditProfileActivity.class);
                    startActivity(in);
                    finish();







            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

                Toast.makeText(ValidateOTPActivity.this,
                        "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();

            }


        });

    }

    private void getLoginResponse(){
        Call<GetLoginOTP> getLoginOTPCall=apiService.getLoginResponse(pHNUMBER);
        getLoginOTPCall.enqueue(new Callback<GetLoginOTP>() {
            @Override
            public void onResponse(Call<GetLoginOTP> call, Response<GetLoginOTP> response) {
                if(response.body().getSuccess()==1){
                    final String otpp = response.body().getData().getOTP();
                    OTP_check=otpp;
                    otp.setText(OTP_check);
                    Toast.makeText(ValidateOTPActivity.this, otpp, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           /* Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                            in.putExtra("phNumber",
                                    phNumber.getText().toString().trim());
                            startActivity(in);*/

                               /* Intent in = new Intent(ValidateOTPActivity.this,ValidateOTPActivity.class);
                                in.putExtra("id",OTP_check);
                                in.putExtra("phNumber", etPhone.getText().toString().trim());
                                startActivity(in);*/
                            verifyOtp();

                        }
                    },1000);
                }
            }

            @Override
            public void onFailure(Call<GetLoginOTP> call, Throwable t) {
                Toast.makeText(ValidateOTPActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveUser(){
        Call<SaveUserResponse> saveUserResponseCall=apiService.getSaveUserResponse(pHNUMBER,String.valueOf(oTP));
        saveUserResponseCall.enqueue(new Callback<SaveUserResponse>() {
            @Override
            public void onResponse(Call<SaveUserResponse> call, Response<SaveUserResponse> response) {
                if(response.body().getSuccess()==1){
                    int otp_varifiy=response.body().getData().getOTP_Verified();
                    int id1=response.body().getData().getId();
                    if(otp_varifiy==0){
                        getLoginResponse();

                    }
                    else {
                        int id=response.body().getData().getId();
                        if(id>0){
                            Toast.makeText(ValidateOTPActivity.this, "User Id Generated",
                                    Toast.LENGTH_SHORT).show();
                            RegPrefManager.getInstance(ValidateOTPActivity.this).setMobileNumber(pHNUMBER);
                            Intent in = new Intent(ValidateOTPActivity.this, EditProfileActivity.class);

                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                            startActivity(in);
                            ValidateOTPActivity.this.finish();
                        }
                        else {
                            Toast.makeText(ValidateOTPActivity.this, "Already Mobile No register",
                                    Toast.LENGTH_SHORT).show();
                            RegPrefManager.getInstance(ValidateOTPActivity.this).setMobileNumber(pHNUMBER);
                            Intent in = new Intent(ValidateOTPActivity.this, EditProfileActivity.class);

                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                            startActivity(in);
                            ValidateOTPActivity.this.finish();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<SaveUserResponse> call, Throwable t) {
                Toast.makeText(ValidateOTPActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
