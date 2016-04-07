package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by Administrator on 3/29/2016.
 */
public class Login extends Activity implements View.OnClickListener{
    //Defining views
    private TextView signUp;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngooyakk_login);

        //Initializing views
        editTextUsername = (EditText) findViewById(R.id.usernameText);
        editTextPassword = (EditText) findViewById(R.id.passwordText);
        signUp  = (TextView) findViewById(R.id.buttRegister);
        buttonLogin = (Button) findViewById(R.id.buttLogin);

        //Adding click listener
        buttonLogin.setOnClickListener(this);
        signUp.setOnClickListener(this);
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
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login(){
        //Getting values from edit texts
        final String email = editTextUsername.getText().toString().trim();
        final String pass = editTextPassword.getText().toString().trim();


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigJson.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.trim().equals("1"))
                            Toast.makeText(Login.this, "Email belum terdaftar", Toast.LENGTH_LONG).show();
                        else if(response.trim().equals("2"))
                            Toast.makeText(Login.this, "Password salah", Toast.LENGTH_LONG).show();
                        else if(response.trim().equals("3")){

                            //Creating a shared preference
                            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(ConfigJson.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(ConfigJson.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(ConfigJson.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(Login.this, Login.class);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(Login.this, "Operasi gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(ConfigJson.KEY_EMAIL, email);
                params.put(ConfigJson.KEY_PASSWORD, pass);

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
        if(v == signUp){
            Intent intent = new Intent(Login.this, RegisterPost.class);
            startActivity(intent);
            finish();
        }else
            login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
