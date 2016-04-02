package com.admintaponkatul.ngooyakk;

/**
 * Created by Administrator on 3/29/2016.
 */
public class ConfigJson {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://198.71.80.189:8029/user/login";
    public static final String REGISTER_URL = "http://owline.org:19000/register.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "pass";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "Login Berhasil";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String SHARED_ID= "id";
    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

}
