package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import f.ratnatech.yowapplication.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button nextBt;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                finish();
            }
        });

        nextBt=findViewById(R.id.nextBt);
        nextBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextBt:
                startActivity(new Intent(RegisterActivity.this,VerifyOTPActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                RegisterActivity.this.finish();
                break;
        }
    }
}
