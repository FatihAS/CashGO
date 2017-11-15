package id.gravicodev.cashgo.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import id.gravicodev.cashgo.R;

public class ForgotPasswordActivity extends BaseActivity {

    private static final String TAG = "ForgotPasswordActivity";
    private AppCompatEditText email_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.forgot_toolbar_forgot);
        toolbar.setNavigationIcon(R.drawable.ic_previous);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        email_forgot = (AppCompatEditText) findViewById(R.id.forgot_email_forgot);
        Button forgot_button = (Button) findViewById(R.id.forgot_button_forgot);

        forgot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotAccount();
            }
        });
    }

    private void forgotAccount() {
        String email = email_forgot.getText().toString().trim();

        if (!validateForm(email)) {
            return;
        }

        showProgressDialog();
        hideProgressDialog();
        forgotDialog();
    }

    private void forgotDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)getApplication()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_forgot_password, null);
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateForm(String email) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            email_forgot.setError(getString(R.string.err_msg_emailempty));
            valid = false;
        } else if (!isValidEmail(email)) {
            email_forgot.setError(getString(R.string.err_msg_email));
            valid = false;
        } else {
            email_forgot.setError(null);
        }

        return valid;
    }
}
