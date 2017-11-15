package id.gravicodev.cashgo.Activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import id.gravicodev.cashgo.Adapter.TransactionPagerAdapter;
import id.gravicodev.cashgo.R;

public class TransactionActivity extends BaseActivity {

    private static final String TAG = "TransactionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Toolbar toolbar = (Toolbar) findViewById(R.id.transcation_toolbar_transaction);
        toolbar.setNavigationIcon(R.drawable.ic_previous);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.transaction_container_transaction);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.transaction_tabs_transaction);

        viewPager.setAdapter(new TransactionPagerAdapter(getSupportFragmentManager()));

        tabLayout.setTabTextColors(
                ContextCompat.getColor(getApplicationContext(), R.color.colorIconUnselected),
                ContextCompat.getColor(getApplicationContext(), android.R.color.white)
        );

        tabLayout.setupWithViewPager(viewPager);
    }
}
