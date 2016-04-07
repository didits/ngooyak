package com.admintaponkatul.ngooyakk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private EditText editTextEmail;
    private EditText editTextNoHp;
    private String value_gender;

    private Button buttonRegister;


    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String nohp = editTextNoHp.getText().toString().trim().toLowerCase();
        String gender = value_gender.trim().toLowerCase();
        register(username,password,email,nohp, gender);
    }

    private void register( String username, String password, String email, String hp, String gender) {
        String urlSuffix = "?nama="+username+"&email="+email+"&hp="+hp+"&jk="+gender+"&pass="+password;
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
                Toast.makeText(getApplicationContext(), "Register Failed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(ConfigJson.REGISTER_URL+s);
                    Toast.makeText(getApplicationContext(),ConfigJson.REGISTER_URL+s, Toast.LENGTH_SHORT).show();
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
}
