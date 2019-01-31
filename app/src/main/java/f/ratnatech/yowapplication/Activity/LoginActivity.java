package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.GetLoginOTP;
import f.ratnatech.yowapplication.Response.NewLoginSuccessData;
import f.ratnatech.yowapplication.Response.SaveUserResponse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView forgotTV;
    private Button loginbtn;
    ApiInterface apiService;
    private EditText etPhone;
    private String OTP_check;
    private int validate_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });

        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        intialize();
    }

    private void intialize(){
       // forgotTV=findViewById(R.id.forgotTV);
        //forgotTV.setOnClickListener(this);
        loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(this);
        etPhone=findViewById(R.id.etPhone);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.loginbtn:
                if(etPhone.length()<10 || etPhone.length()>10){

                    Toast.makeText(LoginActivity.this,
                            "Please enter a correct phone number", Toast.LENGTH_SHORT).show();
                }else {
                    loginResponse();
                }
               /* Intent in = new Intent(LoginActivity.this,HomeActivity.class);
                in.putExtra("id",OTP_check);
                in.putExtra("phNumber", etPhone.getText().toString().trim());
                startActivity(in);*/
                break;
        }
    }

    private void loginResponse(){
        Call<NewLoginSuccessData> call = apiService.getNewLoginResponse(etPhone.getText().toString().trim());
        call.enqueue(new Callback<NewLoginSuccessData>() {
            @Override
            public void onResponse(Call<NewLoginSuccessData> call, Response<NewLoginSuccessData> response) {

                int id=response.body().getData().getId();

                if(response.body().getSuccess()==1){

                   /* Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                    in.putExtra("id",response.body().getData().getId()+"");
                    in.putExtra("phNumber",
                            phNumber.getText().toString().trim());
                    startActivity(in);*/

                    validate_user_id=response.body().getData().getId();
                    if(validate_user_id==0){
                        Toast.makeText(getApplicationContext(),"New User",Toast.LENGTH_LONG).show();
                        getLoginResponse();
                    }
                    else {
                        String fname=response.body().getData().getFirst_Name();

                        RegPrefManager.getInstance(LoginActivity.this).setUserId(response.body().getData().getId());
                        RegPrefManager.getInstance(LoginActivity.this).setFirstName(response.body().getData().getFirst_Name());
                        RegPrefManager.getInstance(LoginActivity.this).setSecondName(response.body().getData().getLast_Name());
                        RegPrefManager.getInstance(LoginActivity.this).setMobileNumber(response.body().getData().getMobile_No());
                        RegPrefManager.getInstance(LoginActivity.this).setEmailNumber(response.body().getData().getEmail_Id());

                     /*   startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                        LoginActivity.this.finish();*/


                       /*Intent in = new Intent(LoginActivity.this,DashBoardActivity.class);
                       startActivity(in);*/
                        getLoginResponse();
                    }

                }
                else {
                    Toast.makeText(LoginActivity.this, "Some thing went wrong",
                            Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onFailure(Call<NewLoginSuccessData> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
                String ti = t.toString();

            }


        });


    }

    private void getLoginResponse(){
        Call<GetLoginOTP> getLoginOTPCall=apiService.getLoginResponse(etPhone.getText().toString().trim());
        getLoginOTPCall.enqueue(new Callback<GetLoginOTP>() {
            @Override
            public void onResponse(Call<GetLoginOTP> call, Response<GetLoginOTP> response) {
            if(response.body().getSuccess()==1){
                final String otpp = response.body().getData().getOTP();
                OTP_check=otpp;
                Toast.makeText(LoginActivity.this, otpp, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                           /* Intent in = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                            in.putExtra("phNumber",
                                    phNumber.getText().toString().trim());
                            startActivity(in);*/
                        RegPrefManager.getInstance(LoginActivity.this).setSaveUser(String.valueOf(validate_user_id));
                        if(validate_user_id==0){

                            saveUser();
                        }else {
                            Intent in = new Intent(LoginActivity.this,ValidateOTPActivity.class);
                            in.putExtra("id",OTP_check);
                            in.putExtra("phNumber", etPhone.getText().toString().trim());
                            startActivity(in);
                        }
                    }
                },1000);
            }
            }

            @Override
            public void onFailure(Call<GetLoginOTP> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveUser(){
        Call<SaveUserResponse> saveUserResponseCall=apiService.getSaveUserResponse(etPhone.getText().toString().trim(),OTP_check);
        saveUserResponseCall.enqueue(new Callback<SaveUserResponse>() {
            @Override
            public void onResponse(Call<SaveUserResponse> call, Response<SaveUserResponse> response) {
                if(response.body().getSuccess()==1){
               /*   Intent in= new Intent(LoginActivity.this,EditProfileActivity.class);

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                    startActivity(in);
                    LoginActivity.this.finish();*/

                    int otp_varifiy=response.body().getData().getOTP_Verified();
                    int id1=response.body().getData().getId();
                    if(otp_varifiy==0){
                     //   getLoginResponse();

                    }
                    else {
                        int id=response.body().getData().getId();
                        if(id>0){
                            Toast.makeText(LoginActivity.this, "User Id Generated",
                                    Toast.LENGTH_SHORT).show();
                            RegPrefManager.getInstance(LoginActivity.this).setMobileNumber(etPhone.getText().toString().trim());
                            Intent in = new Intent(LoginActivity.this, EditProfileActivity.class);

                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                            startActivity(in);
                            LoginActivity.this.finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Already Mobile No register",
                                    Toast.LENGTH_SHORT).show();
                            RegPrefManager.getInstance(LoginActivity.this).setMobileNumber(etPhone.getText().toString().trim());
                            Intent in = new Intent(LoginActivity.this, EditProfileActivity.class);

                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                            startActivity(in);
                            LoginActivity.this.finish();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<SaveUserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}
