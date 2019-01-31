package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.UpdateProfileRespionse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private ImageView nameEdit,mobileImg;
    private EditText firstEd,lastEd,emailEd,mobileEd;
    private Button submitbtn;

    private String firstname,lastname,email,phoneno,updatecheck;
    private int userid;
    ApiInterface apiService;
    private TextInputLayout email_layout,last_layout,firstname_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(EditProfileActivity.this,HomeActivity.class));
                finish();
            }
        });
        intialize();

    }
    private void intialize(){
        lastEd=findViewById(R.id.lastEd);
        firstEd=findViewById(R.id.firstEd);
        emailEd=findViewById(R.id.emailEd);
        mobileEd=findViewById(R.id.mobileEd);
        email_layout=findViewById(R.id.email_layout);
        last_layout=findViewById(R.id.last_layout);
        firstname_layout=findViewById(R.id.firstname_layout);

        nameEdit=findViewById(R.id.nameEdit);
        nameEdit.setOnClickListener(this);
        submitbtn=findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(this);
        mobileImg=findViewById(R.id.mobileImg);
        mobileImg.setOnClickListener(this);

    }
    @Override
    public void onResume(){
        super.onResume();
        userid=RegPrefManager.getInstance(this).getUserId();
        firstname= RegPrefManager.getInstance(this).getFirstName();
        lastname=RegPrefManager.getInstance(this).getSecondName();
        email=RegPrefManager.getInstance(this).getEmailNumber();
        phoneno=RegPrefManager.getInstance(this).getMobileNumber();

        firstEd.setText(firstname);
        lastEd.setText(lastname);
        mobileEd.setText(phoneno);
        emailEd.setText(email);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitbtn:
              //  lastEd.setEnabled(false);
                //firstEd.setEnabled(false);
                if (validation()) {
                  updateName();
                }


                break;
            case R.id.mobileImg:
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                startActivity(new Intent(EditProfileActivity.this, MobileUpdateActivity.class));
                finish();
                break;
        }
    }
    private void updateName(){
            String id=String.valueOf(userid);
            String fname=firstEd.getText().toString();
            String lname=lastEd.getText().toString();
            String email=emailEd.getText().toString();
        Call<UpdateProfileRespionse> call=apiService.getProfileResponse(
                id,fname,lname,email);
            call.enqueue(new Callback<UpdateProfileRespionse>() {
                @Override
                public void onResponse(Call<UpdateProfileRespionse> call, Response<UpdateProfileRespionse> response) {
                    int id=response.body().getData().getProfile_Updated();
                    if(id==1){
                        Toast.makeText(getApplicationContext(),"Profile Update",Toast.LENGTH_LONG).show();
                        RegPrefManager.getInstance(EditProfileActivity.this).setFirstName(firstEd.getText().toString());
                        RegPrefManager.getInstance(EditProfileActivity.this).setSecondName(lastEd.getText().toString());
                       // RegPrefManager.getInstance(EditProfileActivity.this).setMobileNumber(response.body().getData().getMobile_No());
                        RegPrefManager.getInstance(EditProfileActivity.this).setEmailNumber(emailEd.getText().toString());
                        RegPrefManager.getInstance(EditProfileActivity.this).clearlocation();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                        startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Profile not updated as Email-Id already linked with another account",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UpdateProfileRespionse> call, Throwable t) {
                    Toast.makeText(EditProfileActivity.this, "Some thing went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            });
    }
    private Boolean validation() {

       String firstnametv = firstEd.getText().toString().trim();

        if (firstnametv.isEmpty()) {

            firstname_layout.setError( "Please Enter Firstname");

            return false;
        }
        if (lastEd.getText().toString().trim().isEmpty()) {


            last_layout.setError("Please Enter Lastname");
            return false;

        }


        if (emailEd.getText().toString().trim().isEmpty()) {


            email_layout.setError("Please Enter email");
            return false;

        }

        return true;
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
     //   startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

}
