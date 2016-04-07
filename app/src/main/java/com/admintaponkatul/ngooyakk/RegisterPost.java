package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 4/3/2016.
 */
public class RegisterPost extends Activity implements View.OnClickListener{
    //Defining views
    private TextView signIn;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextNoHp;
    private String value_gender="laki-laki";

    private Button buttonRegister;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngooyakk_register);

        editTextUsername = (EditText) findViewById(R.id.usernameText);
        editTextPassword = (EditText) findViewById(R.id.passwordText);
        editTextEmail = (EditText) findViewById(R.id.emailText);
        editTextNoHp = (EditText) findViewById(R.id.hpText);
        signIn  = (TextView) findViewById(R.id.buttLogin);
        buttonRegister = (Button) findViewById(R.id.buttRegister);

        buttonRegister.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(ConfigJson.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(ConfigJson.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(RegisterPost.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void register(){
        //Getting values from edit texts
        final String nama = editTextUsername.getText().toString().trim().toLowerCase();
        final String pass = editTextPassword.getText().toString().trim().toLowerCase();
        final String email = editTextEmail.getText().toString().trim().toLowerCase();
        final String hp = editTextNoHp.getText().toString().trim().toLowerCase();
        final String jk = value_gender.trim().toLowerCase();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigJson.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.trim().equals("1"))
                            Toast.makeText(RegisterPost.this, "Email telah digunakan!", Toast.LENGTH_LONG).show();
                        else if(response.trim().equals("2")){
                            Intent intent = new Intent(RegisterPost.this, Login.class);
                            startActivity(intent);
                        }else if(response.trim().equals("3")){
                            //Displaying an error message on toast
                            Toast.makeText(RegisterPost.this, "Operasi gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        //Toast.makeText(RegisterPost.this, "Operasi gagal", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(ConfigJson.KEY_EMAIL, email);
                params.put(ConfigJson.KEY_PASSWORD, pass);
                params.put(ConfigJson.KEY_NAMA, nama);
                params.put(ConfigJson.KEY_HP, hp);
                params.put(ConfigJson.KEY_JK, jk);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            register();
        }if(v == signIn){
            Intent intent = new Intent(RegisterPost.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_man:
                if (checked)
                    value_gender = "Laki-laki";
                break;
            case R.id.radio_woman:
                if (checked)
                    value_gender = "Perempuan";
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
