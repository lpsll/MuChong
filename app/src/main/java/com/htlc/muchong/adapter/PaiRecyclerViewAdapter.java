package com.htlc.muchong.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htlc.muchong.R;
import com.htlc.muchong.base.BaseRecyclerViewAdapter;
import com.htlc.muchong.util.GoodsUtil;
import com.squareup.picasso.Picasso;

import model.GoodsBean;
import model.PaiGoodsBean;

/**
 * Created by sks on 2016/5/20.
 */
public class PaiRecyclerViewAdapter extends BaseRecyclerViewAdapter<PaiGoodsBean> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_pai_list, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ViewHolder currentHolder = (ViewHolder) holder;
        PaiGoodsBean goodsBean = mList.get(position);

        Picasso.with(currentHolder.imageView.getContext()).load(Uri.parse(goodsBean.commodity_coverimg)).placeholder(R.mipmap.default_first_pai).error(R.mipmap.default_first_pai).into(currentHolder.imageView);
        currentHolder.textName.setText(goodsBean.commodity_name);
        GoodsUtil.setImageByPaiType(currentHolder.imageType, goodsBean.commodity_type);
        GoodsUtil.setPriceBySymbol(currentHolder.textPrice, goodsBean.commodity_panicprice);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textPrice;
        public ImageView imageView, imageType;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imagePai1);
            imageType = (ImageView) view.findViewById(R.id.imageTypePai1);
            textName = (TextView) view.findViewById(R.id.textPaiName1);
            textPrice = (TextView) view.findViewById(R.id.textPaiPrice1);

        }
    }
}
