package id.gravicodev.cashgo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import id.gravicodev.cashgo.Activity.MainActivity;
import id.gravicodev.cashgo.Activity.SigninActivity;
import id.gravicodev.cashgo.Activity.TransactionActivity;
import id.gravicodev.cashgo.Helper.FirebaseUtils;
import id.gravicodev.cashgo.Helper.StaticHelper;
import id.gravicodev.cashgo.Model.User;
import id.gravicodev.cashgo.R;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private TextView edit_account, transaction_account, history_account, service_account,
            privacy_account;
    private Button signout_button;
    private TextView name_account,email_account, phone_account;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        name_account = (TextView) rootView.findViewById(R.id.profile_name_account);
        email_account = (TextView) rootView.findViewById(R.id.profile_email_account);
        phone_account = (TextView) rootView.findViewById(R.id.profile_phone_account);
        edit_account = (TextView) rootView.findViewById(R.id.profile_edit_account);
        transaction_account = (TextView) rootView.findViewById(R.id.profile_transaction_account);
        history_account = (TextView) rootView.findViewById(R.id.profile_history_account);
        service_account = (TextView) rootView.findViewById(R.id.profile_service_account);
        privacy_account = (TextView) rootView.findViewById(R.id.profile_privacy_account);
        signout_button = (Button) rootView.findViewById(R.id.profile_signout_account);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Ubah", Toast.LENGTH_SHORT).show();
            }
        });

        transaction_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TransactionActivity.class));
            }
        });

        history_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Riwayat Pembelian", Toast.LENGTH_SHORT).show();
            }
        });

        service_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Ketentuan Layanan", Toast.LENGTH_SHORT).show();
            }
        });

        privacy_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Kebijakan Privasi", Toast.LENGTH_SHORT).show();
            }
        });

        signout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SigninActivity.class));
                getActivity().finish();
            }
        });
    }

    private void firebaseHandler(){
        StaticHelper sh = new StaticHelper();
        final String Uid = sh.uid;
        DatabaseReference dbUsers = FirebaseUtils.getBaseRef().child("users").child(Uid);

        // User information value listener
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                try{
                    name_account.setText(user.nama);
                    email_account.setText("Rp. "+((MainActivity)getActivity()).moneyParserString(String.valueOf(user.saldo)));
                    phone_account.setText(user.nomor_telepon.substring(0, 1).toUpperCase());
//                    new SessionManager(getContext()).renew(user);
                }
                catch (NullPointerException ex){
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbUsers.addValueEventListener(userListener);
        ((MainActivity)getActivity()).addListener(dbUsers,userListener);
    }
}
