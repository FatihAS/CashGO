package id.gravicodev.cashgo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import id.gravicodev.cashgo.R;

public class SignupActivity extends BaseActivity {

    private static final String TAG = "SignupActivity";
    private AppCompatEditText name_signup, email_signup, password_signup, phone_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signup_toolbar_signup);
        toolbar.setNavigationIcon(R.drawable.ic_previous);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name_signup = (AppCompatEditText) findViewById(R.id.signup_name_signup);
        email_signup = (AppCompatEditText) findViewById(R.id.signup_email_signup);
        password_signup = (AppCompatEditText) findViewById(R.id.signup_password_signup);
        phone_signup = (AppCompatEditText) findViewById(R.id.signup_phone_signup);
        Button signup_button = (Button) findViewById(R.id.signup_button_signup);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupAccount();
            }
        });
    }

    private void signupAccount() {
        String name = name_signup.getText().toString().trim();
        String email = email_signup.getText().toString().trim();
        String password = password_signup.getText().toString().trim();
        String phone = phone_signup.getText().toString().trim();

        if (!validateForm(name, email, password, phone)) {
            return;
        }

        showProgressDialog();
        hideProgressDialog();
        startActivity(new Intent(getApplicationContext(), SigninActivity.class));
        finish();
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateForm(String name, String email, String password, String phone) {
        boolean valid = true;

        if (TextUtils.isEmpty(name)) {
            name_signup.setError(getString(R.string.err_msg_nameempty));
            valid = false;
        } else {
            email_signup.setError(null);
        }

        if (TextUtils.isEmpty(email)) {
            email_signup.setError(getString(R.string.err_msg_emailempty));
            valid = false;
        } else if (!isValidEmail(email)) {
            email_signup.setError(getString(R.string.err_msg_email));
            valid = false;
        } else {
            email_signup.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            password_signup.setError(getString(R.string.err_msg_password));
            valid = false;
        } else if (password.length() < 8) {
            password_signup.setError(getString(R.string.err_msg_min_password));
            valid = false;
        } else {
            password_signup.setError(null);
        }

        if (TextUtils.isEmpty(phone)) {
            phone_signup.setError(getString(R.string.err_msg_phoneempty));
            valid = false;
        } else {
            phone_signup.setError(null);
        }

        return valid;
    }
}
