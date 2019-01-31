package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;
import f.ratnatech.yowapplication.Response.WalletResponse;
import f.ratnatech.yowapplication.client.ApiClient;
import f.ratnatech.yowapplication.client.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletActivity extends AppCompatActivity {
    private TextView balanceTv,balanceprice;
    ApiInterface apiService;
    private int userid;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this,HomeActivity.class));
                finish();
            }
        });

        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        intialize();
    }
    private void intialize(){
        balanceprice=findViewById(R.id.balanceprice);
        balanceTv=findViewById(R.id.balanceTv);

   //     balanceprice.setText("\u20B9 "+"30");


    }
    @Override
    protected void onResume() {
        super.onResume();
        userid= RegPrefManager.getInstance(this).getUserId();
        getBalanceResponse();
    }

    private void getBalanceResponse(){
        Call<WalletResponse> call=apiService.getWalletResponse(String.valueOf(userid));
        call.enqueue(new Callback<WalletResponse>() {
            @Override
            public void onResponse(Call<WalletResponse> call, Response<WalletResponse> response) {
                int success=response.body().getSuccess();
                if(success==0){
                    Toast.makeText(getApplicationContext(),"Wallet amount not found",Toast.LENGTH_LONG).show();
                }
                else {
                    String balance=response.body().getData().getMy_Cash();
                    balanceprice.setText("\u20B9 "+balance);
                }
            }

            @Override
            public void onFailure(Call<WalletResponse> call, Throwable t) {
                Toast.makeText(WalletActivity.this, "Some thing went wrong",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(WalletActivity.this,HomeActivity.class));
        finish();
    }
}
