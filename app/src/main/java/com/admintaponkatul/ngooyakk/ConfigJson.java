package com.admintaponkatul.ngooyakk;

/**
 * Created by Administrator on 3/29/2016.
 */
public class ConfigJson {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://198.71.80.189:8029/user/login/";
    public static final String REGISTER_URL = "http://198.71.80.189:8029/user/register";
    public static final String ORDER_URL = "http://198.71.80.189:8029/order";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "pass";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_HP = "hp";
    public static final String KEY_JK = "jk";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String SHARED_ID= "id";
    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    //GEO JSON
    public static final String SHARED_PREF_GEO = "mygeo";
    public static final String SHARED_MY_LOCATION = "0,0";
    public static final String SHARED_TO_LOCATION = "0,0";
    public static final String KEY_GEO1 = "asal";
    public static final String KEY_GEO2 = "tujuan";
    public static final String KEY_ID_USER = "id";

}
