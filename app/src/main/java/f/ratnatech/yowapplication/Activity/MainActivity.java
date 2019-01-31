package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import f.ratnatech.yowapplication.Driver.DiverLoginActivity;
import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView loginTv,registerTv;
    private int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialize();
    }
    private void intialize(){
        loginTv=findViewById(R.id.loginTv);
        registerTv=findViewById(R.id.registerTv);

        loginTv.setOnClickListener(this);
        registerTv.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginTv:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                MainActivity.this.finish();
                break;
            case R.id.registerTv:
                startActivity(new Intent(MainActivity.this,DiverLoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                MainActivity.this.finish();
                break;
        }
    }
}
