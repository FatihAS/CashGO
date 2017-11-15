package id.gravicodev.cashgo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.gravicodev.cashgo.Fragment.TransactionDoneFragment;
import id.gravicodev.cashgo.Fragment.TransactionProcessFragment;
import id.gravicodev.cashgo.R;

public class TransactionPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "TransactionPagerAdapter";

    private String[] title = new String[]{
            "Dalam Process", "Selesai"
    };

    public TransactionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TransactionProcessFragment();
                break;
            case 1:
                fragment = new TransactionDoneFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
