package id.gravicodev.cashgo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import id.gravicodev.cashgo.Helper.FirebaseUtils;
import id.gravicodev.cashgo.Helper.StaticHelper;
import id.gravicodev.cashgo.R;
import id.gravicodev.cashgo.Session.SessionManager;
import id.gravicodev.cashgo.Model.User;

public class SigninActivity extends BaseActivity {

    private static final String TAG = "SigninActivity";
    private AppCompatEditText email_signin, password_signin;

    private SessionManager sessionManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();

        // Session Manager
        //sessionManager = new SessionManager(this);

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
        final String email = email_signin.getText().toString();
        final String password = password_signin.getText().toString();


        if (!validateForm(email, password)) {
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            Log.d("jancok",user.getUid());
                            StaticHelper sh = new StaticHelper();
                            sh.uid = user.getUid();
                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
//                        if (task.isSuccessful()) {
////                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
////                            startActivity(intent);
////                            finish();
////                            Toast.makeText(SigninActivity.this, "Successfully Signed In", Toast.LENGTH_LONG).show();
//                            //session.createLoginSession(userId);
//                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
//                            finish();
////                            onAuthSuccess(task.getResult().getUser());
//                        }else{
//                            Toast.makeText(SigninActivity.this, "Signin Gagal", Toast.LENGTH_SHORT).show();
//                        }

                    }
                });

        //showProgressDialog();
        //hideProgressDialog();
        //startActivity(new Intent(this, MainActivity.class));
        //finish();
    }

    private void onAuthSuccess(final FirebaseUser user) {
        FirebaseUtils.getBaseRef().child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
//                    User user = dataSnapshot.getValue(User.class);
////                    user.setBalance(dataSnapshot.child("balance").getValue(Integer.class));
//                    hideProgressDialog();
//                    sessionManager.logIn(user);
                    startActivity(new Intent (SigninActivity.this,MainActivity.class));
                    finish();
                }
                else{
                    showToast("User not found");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
