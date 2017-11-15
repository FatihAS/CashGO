package id.gravicodev.cashgo.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.gravicodev.cashgo.R;

public class ScanResultActivity extends BaseActivity {

    private static final String TAG = "ScanResultActivity";
    private TextView name_result, price_result, other_result;
    private EditText amount_result;
    private ImageView pict_result;
    private Button addcart_button;
    private Spinner spinnerSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.scanresult_toolbar_scanresult);
        toolbar.setNavigationIcon(R.drawable.ic_previous);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name_result = (TextView) findViewById(R.id.scanresult_name);
        price_result = (TextView) findViewById(R.id.scanresult_price);
        other_result = (TextView) findViewById(R.id.scanresult_other);
        amount_result = (EditText) findViewById(R.id.scanresult_amount);
        pict_result = (ImageView) findViewById(R.id.scanresult_picture);
        addcart_button = (Button) findViewById(R.id.scanresult_button_addcart);
        spinnerSize = (Spinner) findViewById(R.id.scanresult_spinner_size);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.size_result, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(arrayAdapter);

        // Dummy data
        String[] barang = new String[]{
                "Barang 1", "Rp 100.000"
        };
        int picture = R.drawable.promo_pict;

        name_result.setText(barang[0]);
        price_result.setText(barang[1]);
        Glide.with(getApplicationContext()).load(picture).into(pict_result);

        addcart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }

    private void addToCart() {
        String amount = amount_result.getText().toString().trim();

        showToast(name_result.getText().toString().trim() + " sejumlah " + amount +
                "berhasil ditambahkan pada keranjang");
    }
}
