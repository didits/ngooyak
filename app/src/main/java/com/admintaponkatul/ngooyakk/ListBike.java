package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 4/2/2016.
 */
public class ListBike extends FragmentActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cariBike(View target) {
        startActivity(new Intent(this, PilihBike.class));
    }
}
