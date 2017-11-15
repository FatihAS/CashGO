package id.gravicodev.cashgo.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import id.gravicodev.cashgo.Model.Promo;
import id.gravicodev.cashgo.R;

public class PromoCardAdapter extends RecyclerView.Adapter<PromoCardAdapter.PromoViewHolder> {

    private Context context;
    private List<Promo> promoList;

    public PromoCardAdapter(Context context, List<Promo> promoList) {
        this.context = context;
        this.promoList = promoList;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_promo,
                parent, false);
        return new PromoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, int position) {
        Promo promo = promoList.get(position);

        holder.name.setText(promo.getName());
        holder.store.setText(promo.getStore());
        Glide.with(context).load(promo.getPicture()).into(holder.picture);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)
                holder.view.getLayoutParams();

        if (position == 0){
            layoutParams.setMargins(8, 16, 8, 8);

        }
        else if (position == promoList.size() - 1){
            layoutParams.setMargins(8, 8, 8, 16);
        }
        else {
            layoutParams.setMargins(8, 8, 8, 8);
        }

        holder.view.requestLayout();
    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    class PromoViewHolder extends RecyclerView.ViewHolder {

        TextView name, store;
        ImageView picture;
        public CardView view;

        PromoViewHolder(View itemView) {
            super(itemView);
            view = (CardView) itemView.findViewById(R.id.cardpromo_card);
            name = (TextView) itemView.findViewById(R.id.cardpromo_name);
            store = (TextView) itemView.findViewById(R.id.cardpromo_store);
            picture = (ImageView) itemView.findViewById(R.id.cardpromo_picture);
        }
    }

    public void setFilter(List<Promo> list){
        promoList = new ArrayList<>();
        promoList.addAll(list);
        notifyDataSetChanged();
    }
}
