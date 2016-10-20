package com.example.alsaint.examplemvp.ui.adatpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.alsaint.examplemvp.R;
import com.example.alsaint.examplemvp.server.models.CakeObj;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.ViewHolderItem> {

    private final Context context;
    private final List<CakeObj.Cake> cakes;
    private final LayoutInflater inflater;

    public CakeAdapter(Context context, List<CakeObj.Cake> cakes, LayoutInflater inflater) {
        this.context = context;
        this.cakes = cakes;
        this.inflater = inflater;
    }

    @Override
    public CakeAdapter.ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(context, inflater.inflate(R.layout.item_cake, parent, false));
    }

    @Override
    public void onBindViewHolder(CakeAdapter.ViewHolderItem holder, int position) {
        holder.bind(cakes.get(position));
    }

    @Override
    public int getItemCount() {
        return cakes != null ? cakes.size() : 0;
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder {
        @BindView(R.id.cake_icon)
        ImageView cakeIV;
        @BindView(R.id.textview_title)
        TextView titleTV;
        @BindView(R.id.textview_preview_description)
        TextView descTV;

        private final Context context;

        ViewHolderItem(Context context, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.context = context;
        }

        void bind(CakeObj.Cake cake) {
            titleTV.setText(cake.getTitle());
            descTV.setText(cake.getPreviewDescription());

            Glide.with(context).load(cake.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(cakeIV);
        }
    }
}