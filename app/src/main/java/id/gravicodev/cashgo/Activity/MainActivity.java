package id.gravicodev.cashgo.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import id.gravicodev.cashgo.Adapter.TabFragmentPagerAdapter;
import id.gravicodev.cashgo.Helper.StaticHelper;
import id.gravicodev.cashgo.R;
import id.gravicodev.cashgo.Session.SessionManager;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new SessionManager(getApplicationContext());
//        session.checkLogin();
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar_main);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.main_container_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabs_main);

        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        // Change Icon Tab Layout
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_scan);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_profile);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(ContextCompat.getColor(
                getApplicationContext(), R.color.colorIconUnselected), PorterDuff.Mode.SRC_IN
        );
        tabLayout.getTabAt(2).getIcon().setColorFilter(ContextCompat.getColor(
                getApplicationContext(), R.color.colorIconUnselected), PorterDuff.Mode.SRC_IN
        );

        // Change Selected Color Icon Tab Layout
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                tab.getIcon().setColorFilter(
                        ContextCompat.getColor(getApplicationContext(), android.R.color.white),
                        PorterDuff.Mode.SRC_IN
                );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(
                        ContextCompat.getColor(getApplicationContext(), R.color.colorIconUnselected),
                        PorterDuff.Mode.SRC_IN
                );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Change Title Action Bar in each fragment
                switch (position) {
                    case 0:
                        getSupportActionBar().setTitle("CashGo");
                        break;
                    case 1:
                        getSupportActionBar().setTitle("ScanGo");
                        break;
                    case 2:
                        getSupportActionBar().setTitle("ProfilGo");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        }
        StaticHelper sh = new StaticHelper();
        Log.d("jancok",sh.uid);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    showToast("Permission denied to read your External storage");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            startActivity(new Intent(getApplicationContext(), CartActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
