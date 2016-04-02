package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 3/29/2016.
 */
public class SplashScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        /****** Create Thread that will sleep for 5 seconds *************/
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    //After 5 seconds redirect to another intent
                    //Intent i=new Intent(getBaseContext(),MainActivity.class);
                    Intent i=new Intent(getBaseContext(),Login.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Intent intent = new Intent(this, Login.class);
        //startActivity(intent);
    }
}
