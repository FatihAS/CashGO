package id.gravicodev.cashgo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.gravicodev.cashgo.Adapter.PromoCardAdapter;
import id.gravicodev.cashgo.Model.Promo;
import id.gravicodev.cashgo.R;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private PromoCardAdapter adapter;
    private List<Promo> promoList;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.home_recyclerview);

        promoList = new ArrayList<>();
        adapter = new PromoCardAdapter(getContext(), promoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        preparePromo();

        return rootView;
    }

    private void preparePromo() {
        int picture = R.drawable.promo_pict;
        Promo promo;

        // Dummies data
        for (int i = 0; i < 10; i++) {
            promo = new Promo(String.valueOf(i), getString(R.string.promo_name) + " " + (i + 1),
                    getString(R.string.promo_store) + " " + (i + 1), picture);
            promoList.add(promo);
        }

        adapter.notifyDataSetChanged();
    }
}
