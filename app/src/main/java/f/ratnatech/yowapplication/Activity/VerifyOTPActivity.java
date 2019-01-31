package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import f.ratnatech.yowapplication.R;

public class VerifyOTPActivity extends AppCompatActivity {
    private TextView verifymobileTv;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerifyOTPActivity.this,RegisterActivity.class));
                finish();
            }
        });
        verifymobileTv=findViewById(R.id.verifymobileTv);
        verifymobileTv.setText("We are unable to auto -verify your mobile number.\n" +
                "please enter the code tested to +91 9178845554");

    }
}
