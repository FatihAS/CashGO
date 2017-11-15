package id.gravicodev.cashgo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.gravicodev.cashgo.Activity.ScanResultActivity;
import id.gravicodev.cashgo.R;

public class ScanFragment extends Fragment {

    private static final String TAG = "ScanFragment";

    public ScanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);

        Button scan_test = (Button) rootView.findViewById(R.id.scan_test);
        scan_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScanResultActivity.class));
            }
        });

        return rootView;
    }
}
