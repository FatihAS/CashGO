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

import id.gravicodev.cashgo.Activity.SigninActivity;
import id.gravicodev.cashgo.Activity.TransactionActivity;
import id.gravicodev.cashgo.R;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private TextView edit_account, transaction_account, history_account, service_account,
            privacy_account;
    private Button signout_button;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name_account = (TextView) rootView.findViewById(R.id.profile_name_account);
        TextView email_account = (TextView) rootView.findViewById(R.id.profile_email_account);
        TextView phone_account = (TextView) rootView.findViewById(R.id.profile_phone_account);
        edit_account = (TextView) rootView.findViewById(R.id.profile_edit_account);
        transaction_account = (TextView) rootView.findViewById(R.id.profile_transaction_account);
        history_account = (TextView) rootView.findViewById(R.id.profile_history_account);
        service_account = (TextView) rootView.findViewById(R.id.profile_service_account);
        privacy_account = (TextView) rootView.findViewById(R.id.profile_privacy_account);
        signout_button = (Button) rootView.findViewById(R.id.profile_signout_account);

        // Dummie Data User
        String[] dummie_user = new String[]{
                "Gravicodev", "gravicodev@gmail.com", "+62811234567890"
        };

        name_account.setText(dummie_user[0]);
        email_account.setText(dummie_user[1]);
        phone_account.setText(dummie_user[2]);

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
}
