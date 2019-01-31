package f.ratnatech.yowapplication.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import f.ratnatech.yowapplication.Helper.RegPrefManager;
import f.ratnatech.yowapplication.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private int userid;
    private String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
               // userid= RegPrefManager.getInstance(SplashActivity.this).getUserId();
                mobile=RegPrefManager.getInstance(SplashActivity.this).getMobileNumber();

                if(mobile!=null){
                    RegPrefManager.getInstance(SplashActivity.this).clearlocation();
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                    SplashActivity.this.finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // for fwd
                    SplashActivity.this.finish();
                }



                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
