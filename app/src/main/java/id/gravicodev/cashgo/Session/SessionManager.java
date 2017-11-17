package id.gravicodev.cashgo.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.HashMap;

import id.gravicodev.cashgo.Activity.SigninActivity;
import id.gravicodev.cashgo.Activity.MainActivity;
import id.gravicodev.cashgo.Helper.FirebaseUtils;
import id.gravicodev.cashgo.Model.User;
import id.gravicodev.cashgo.R;

/**
 * Created by Julio Alfian on 17/11/2017.
 */

public class SessionManager {
    private final String PREF_NAME;
    private final String PREF_USER_ID;
    private final String PREF_USER_EMAIL;
    private final String PREF_USER_FULLNAME;
    private final String PREF_ACCOUNT_BALANCE;

    private final String PREF_DEVICE_ID;

    private final String IS_LOGIN;

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context){
        this.context = context;

        PREF_NAME = context.getString(R.string.app_name);

        PREF_USER_ID = context.getString(R.string.id);
        PREF_USER_EMAIL = context.getString(R.string.email);
        PREF_USER_FULLNAME = context.getString(R.string.fullname);
        PREF_ACCOUNT_BALANCE = context.getString(R.string.balanceacc);
        PREF_DEVICE_ID = "Device ID";
        IS_LOGIN = "isLoggedIn";


        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void logIn(User user){
        editor.putString(PREF_USER_ID, FirebaseAuth.getInstance().getCurrentUser().getUid());
        editor.putString(PREF_USER_EMAIL, user.email);
        editor.putString(PREF_USER_FULLNAME, user.fullname);
        editor.putInt(PREF_ACCOUNT_BALANCE, user.getBalance());
        editor.putString(PREF_DEVICE_ID, FirebaseInstanceId.getInstance().getId());

//        FirebaseUtils.getBaseRef().child("userDevice")
//                .child(user.accountNumber)
//                .child(FirebaseInstanceId.getInstance().getId())
//                .setValue(true);

        editor.putBoolean(IS_LOGIN,true);
        editor.commit();
    }

    public User getUser(){
        String fullname = preferences.getString(PREF_USER_FULLNAME,null);
        String email = preferences.getString(PREF_USER_EMAIL,null);
        String userid = preferences.getString(PREF_USER_ID,null);
        int balanceacc = preferences.getInt(PREF_ACCOUNT_BALANCE, 123);

        User user = new User(userid,fullname,email);
        user.setBalance(balanceacc);
        user.setUserid(userid);

        return user;
    }

    public void logOut(){
        try {
            String token = this.getDeviceId();
            FirebaseUtils.getBaseRef().child("userDevice")
                    .child(token).removeValue();
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, SigninActivity.class);
        context.startActivity(intent);
        ((MainActivity)context).finish();

    }

    public void renew(User user){
        editor.putString(PREF_USER_ID, FirebaseAuth.getInstance().getCurrentUser().getUid());
        editor.putString(PREF_USER_EMAIL, user.email);
        editor.putString(PREF_USER_FULLNAME, user.fullname);
        editor.putInt(PREF_ACCOUNT_BALANCE, user.getBalance());

        editor.commit();
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){
            this.logOut();
        }
    }
    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN,false);
    }

    public String getDeviceId(){
        return preferences.getString(PREF_DEVICE_ID,null);
    }

    public void resetDeviceId(String key){
        editor.putString(PREF_DEVICE_ID, key);
        editor.commit();
    }
}
