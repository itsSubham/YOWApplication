package f.ratnatech.yowapplication.Driver;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import f.ratnatech.yowapplication.Activity.LoginActivity;
import f.ratnatech.yowapplication.Activity.MainActivity;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.DriverRegister;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiverLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextInputLayout reg_layout;
    private EditText regno;
    private Button loginbtn;
    ApiInterface apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diver_login);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiverLoginActivity.this,MainActivity.class));
                finish();
            }
        });
        apiService =
                ApiClient.getClient().create(ApiInterface.class);
        intialize();
    }

    private void intialize(){
        reg_layout=findViewById(R.id.reg_layout);
        loginbtn=findViewById(R.id.loginbtn);
        regno=findViewById(R.id.regno);
        loginbtn=findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
                registerLogin();
                break;
        }
    }
    private void registerLogin(){
        Call<DriverRegister> call=apiService.getDriverRegisterResponse(regno.getText().toString().trim());
        call.enqueue(new Callback<DriverRegister>() {
            @Override
            public void onResponse(Call<DriverRegister> call, Response<DriverRegister> response) {
                int id=response.body().getData().getId();
                if(id==1){
                    Toast.makeText(getApplicationContext(),"Register ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"not Register ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DriverRegister> call, Throwable t) {

                Toast.makeText(DiverLoginActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
