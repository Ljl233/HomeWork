package com.example.homeworks.AssignDetail;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homeworks.R;

import java.util.List;

public class DetailAdapter extends BaseAdapter {

    private Context context;
    private List<DetailBean.SubmitAttachmentBean> submitAttachmentBeans;
    public DetailAdapter(Context context, List<DetailBean.SubmitAttachmentBean> submitAttachmentBeans){
        this.context=context;
        this.submitAttachmentBeans=submitAttachmentBeans;
    }
    @Override
    public int getCount(){
        return submitAttachmentBeans.size();
    }

    @Override
    public Object getItem (int position){
        return submitAttachmentBeans.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView (int position,View convertView, ViewGroup parent){
        DetailBean.SubmitAttachmentBean submitAttachment = (DetailBean.SubmitAttachmentBean) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_detail,null);
            viewHolder=new ViewHolder();
            viewHolder.submitName=view.findViewById(R.id.tv_submitAttachmentId);
            viewHolder.submitExt=view.findViewById(R.id.tv_submitAttachmentExt);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.submitName.setText(submitAttachment.getName());
        viewHolder.submitExt.setText(submitAttachment.getExt());
        return view;
    }
    class ViewHolder{
        TextView submitName;
        TextView submitExt;
    }
}
