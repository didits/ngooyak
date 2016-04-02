package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 3/29/2016.
 */
public class Register extends Activity implements View.OnClickListener{
    private TextView signIn;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextPasswordAgain;
    private EditText editTextEmail;

    private Button buttonRegister;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngooyakk_register);
        editTextUsername = (EditText) findViewById(R.id.usernameText);
        editTextPassword = (EditText) findViewById(R.id.passwordText);
        editTextPasswordAgain = (EditText) findViewById(R.id.repasswordText);
        editTextEmail = (EditText) findViewById(R.id.emailText);
        signIn  = (TextView) findViewById(R.id.buttLogin);
        buttonRegister = (Button) findViewById(R.id.buttRegister);

        buttonRegister.setOnClickListener(this);
        signIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }if(v == signIn){
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
            finish();
        }
    }



    private void registerUser() {
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String passwordAgain = editTextPasswordAgain.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();

        register(username,password,passwordAgain,email);
    }

    private void register( String username, String password, String passwd, String email) {
        String urlSuffix = "?username="+username+"&password="+password+"&passwd="+passwd+"&email="+email;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(ConfigJson.REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
