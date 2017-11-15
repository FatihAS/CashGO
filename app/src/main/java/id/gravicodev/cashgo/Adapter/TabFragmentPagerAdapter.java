package id.gravicodev.cashgo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.gravicodev.cashgo.Fragment.HomeFragment;
import id.gravicodev.cashgo.Fragment.ProfileFragment;
import id.gravicodev.cashgo.Fragment.ScanFragment;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "TabFragmentPagerAdapter";

    private String[] title = new String[]{
            "CashGo", "ScanGo", "ProfilGo"
    };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new ScanFragment();
                break;
            case 2:
                fragment = new ProfileFragment();
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
