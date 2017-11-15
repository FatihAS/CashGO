package id.gravicodev.cashgo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.gravicodev.cashgo.R;

public class TransactionDoneFragment extends Fragment {

    private static final String TAG = "TransactionDoneFragment";

    public TransactionDoneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_transaction_done, container, false);

        return rootView;
    }
}
