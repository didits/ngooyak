package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Administrator on 4/1/2016.
 */
public class PilihBike extends Activity {
    Image pilihRider;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_kendaraan);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    setRideBike("man");
                    startActivity(new Intent(this, MyLocation.class));
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    setRideBike("wowan");
                    startActivity(new Intent(this, MyLocation.class));
                    break;
        }
    }

    public void setRideBike(String gender){
        SharedPreferences sharedPreferences = PilihBike.this.getSharedPreferences(ConfigClass.bikeGender, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConfigClass.bikeGender, gender);
        editor.commit();
    }
}
