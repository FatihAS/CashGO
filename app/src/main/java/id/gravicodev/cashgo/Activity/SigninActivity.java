package id.gravicodev.cashgo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import id.gravicodev.cashgo.R;

public class SigninActivity extends BaseActivity {

    private static final String TAG = "SigninActivity";
    private AppCompatEditText email_signin, password_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        email_signin = (AppCompatEditText) findViewById(R.id.signin_email_signin);
        password_signin = (AppCompatEditText) findViewById(R.id.signin_password_signin);
        Button signin_button = (Button) findViewById(R.id.signin_button_signin);
        Button signup_button = (Button) findViewById(R.id.signin_button_signup);
        Button forgot_button = (Button) findViewById(R.id.signin_button_forgot);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinAccount();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });

        forgot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
            }
        });
    }

    private void signinAccount() {
        String email = email_signin.getText().toString().trim();
        String password = password_signin.getText().toString().trim();

        if (!validateForm(email, password)) {
            return;
        }

        showProgressDialog();
        hideProgressDialog();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateForm(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            email_signin.setError(getString(R.string.err_msg_emailempty));
            valid = false;
        } else if (!isValidEmail(email)) {
            email_signin.setError(getString(R.string.err_msg_email));
            valid = false;
        } else {
            email_signin.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            password_signin.setError(getString(R.string.err_msg_password));
            valid = false;
        } else if (password.length() < 8) {
            password_signin.setError(getString(R.string.err_msg_min_password));
            valid = false;
        } else {
            password_signin.setError(null);
        }

        return valid;
    }
}
