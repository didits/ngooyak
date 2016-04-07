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
import android.widget.TextView;

/**
 * Created by Administrator on 4/1/2016.
 */
public class PilihBike extends Activity {
    Image pilihRider;
    TextView nama_user;
    private String loggedIn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_kendaraan);
        nama_user = (TextView) findViewById(R.id.textViewNama);
        SharedPreferences sharedPreferences = getSharedPreferences(ConfigJson.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getString(ConfigJson.EMAIL_SHARED_PREF, "NULL");
        String nama;
        nama = "Hai "+ loggedIn;
        nama_user.setText(nama);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_man:
                if (checked)
                    setRideBike("man");
                    startActivity(new Intent(this, MyLocation.class));
                    break;
            case R.id.radio_woman:
                if (checked)
                    setRideBike("wowan");
                    startActivity(new Intent(this, MyLocation.class));
                    break;
            case R.id.radio_all:
                if (checked)
                    setRideBike("all");
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
