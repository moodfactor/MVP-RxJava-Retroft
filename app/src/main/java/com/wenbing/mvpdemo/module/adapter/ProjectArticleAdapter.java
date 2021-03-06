package com.wenbing.mvpdemo.module.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.adapter.base.CommonViewHolder;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:54
 */
public class ProjectArticleAdapter extends BaseRVAdapter<ArticleBean.DataBean> {
    public ProjectArticleAdapter(Context context, List<ArticleBean.DataBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_project_article;
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, ArticleBean.DataBean bean, int position) {
        holder.setText(R.id.tv_author, TextUtils.isEmpty(bean.getAuthor()) ? bean.getShareUser() : bean.getAuthor());
        holder.setText(R.id.tv_time, bean.getNiceDate());
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setText(R.id.tv_content, bean.getDesc());
        holder.setText(R.id.tv_chapter, bean.getSuperChapterName() + "-" + bean.getChapterName());
        ImageView iv = holder.$(R.id.iv_icon);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_project_img)//图片加载出来前，显示的图片
                .fallback( R.drawable.default_project_img) //url为空的时候,显示的图片
                .error(R.drawable.default_project_img);//图片加载失败后，显示的图片

        Glide.with(mContext).load(bean.getEnvelopePic()).apply(options).into(iv);
        if(bean.isCollect()){
            holder.setImageResource(R.id.iv_state,R.drawable.collected);
        }else{
            holder.setImageResource(R.id.iv_state,R.drawable.un_collected);
        }
    }
}
