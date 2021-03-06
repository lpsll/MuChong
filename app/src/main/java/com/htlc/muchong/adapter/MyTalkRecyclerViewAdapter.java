package com.htlc.muchong.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.htlc.muchong.R;
import com.htlc.muchong.activity.ProductDetailActivity;
import com.htlc.muchong.util.DateFormat;
import com.htlc.muchong.util.ImageUtil;
import com.htlc.muchong.util.PersonUtil;

import model.PostBean;

/**
 * Created by sks on 2016/5/20.
 */
public class MyTalkRecyclerViewAdapter extends com.htlc.muchong.base.BaseRecyclerViewAdapter<PostBean> {
    public interface OnOperationListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
    private OnOperationListener operationListener;

    public void setOperationListener(OnOperationListener operationListener) {
        this.operationListener = operationListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_fragment_my_post_list_talk, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder,position);
        ViewHolder viewHolder = (ViewHolder) holder;
        PostBean bean = mList.get(position);

        String[] images = bean.forum_imgstr.split(ProductDetailActivity.SPLIT_FLAG);
        if(images.length>=3){
            ImageUtil.setImageByDefault(viewHolder.image3,R.mipmap.default_first_qiang,Uri.parse(images[2]));
            viewHolder.image3.setVisibility(View.VISIBLE);
        }else {
            viewHolder.image3.setVisibility(View.INVISIBLE);
        }
        if(images.length>=2){
            ImageUtil.setImageByDefault(viewHolder.image2,R.mipmap.default_first_qiang,Uri.parse(images[1]));
            viewHolder.image2.setVisibility(View.VISIBLE);
        }else {
            viewHolder.image2.setVisibility(View.INVISIBLE);
        }
        if(images.length>=1){
            ImageUtil.setImageByDefault(viewHolder.image1,R.mipmap.default_first_qiang,Uri.parse(images[0]));
            viewHolder.image1.setVisibility(View.VISIBLE);
        }else {
            viewHolder.image1.setVisibility(View.INVISIBLE);
        }
        viewHolder.textDescription.setText(bean.forum_content);
        viewHolder.textDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operationListener!=null){
                    operationListener.onDeleteClick(position);
                }
            }
        });
        viewHolder.textEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operationListener!=null){
                    operationListener.onEditClick(position);
                }
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image1, image2, image3;
        public TextView textPostTitle, textDescription, textEdit, textDelete;

        public ViewHolder(View view) {
            super(view);
            image1 = (ImageView)  view.findViewById(R.id.image1);
            image2 = (ImageView)  view.findViewById(R.id.image2);
            image3 = (ImageView)  view.findViewById(R.id.image3);
            textPostTitle = (TextView)  view.findViewById(R.id.textPostTitle);
            textDescription = (TextView)  view.findViewById(R.id.textDescription);
            textEdit = (TextView)  view.findViewById(R.id.textEdit);
            textDelete = (TextView)  view.findViewById(R.id.textDelete);
        }
    }
}
