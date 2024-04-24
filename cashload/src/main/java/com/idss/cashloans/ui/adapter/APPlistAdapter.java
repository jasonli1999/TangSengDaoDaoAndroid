package com.idss.cashloans.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.PaopaoModel;
import com.idss.cashloans.api.utils.LogUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class APPlistAdapter extends RecyclerView.Adapter<APPlistAdapter.ViewHolder> {
    private final Context mContext;
    private final PaopaoModel paopaoModel;
    List<PaopaoModel.DataDTO.RecordsDTO> getRecords = new ArrayList<>();

    public APPlistAdapter(Context mContext, PaopaoModel paopaoModel) {
        this.mContext = mContext;
        this.paopaoModel = paopaoModel;
    }

    @NonNull
    @Override
    public APPlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_applist, parent, false);
        return new APPlistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull APPlistAdapter.ViewHolder holder, int position) {
        //app根据sort排序展示
        int size = paopaoModel.getData().getRecords().size();
        if (size == 0) {
            return;
        }
        for (int i = 1; i <= size; i++) {
            for (PaopaoModel.DataDTO.RecordsDTO recordsDTO : paopaoModel.getData().getRecords()) {
                if (recordsDTO.getSort() == i)
                    getRecords.add(recordsDTO);
            }
        }

        holder.tv_appname.setText(getRecords.get(position).getAppName());
        LogUtil.e("position:" + position);
        Picasso.get().load(getRecords.get(position).getAppLogo()).into(holder.iv_app);
        holder.app_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appdownloadUrl = getRecords.get(position).getDownloadUrl();
                if (!appdownloadUrl.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(appdownloadUrl));
                    mContext.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return null == paopaoModel ? 0 : paopaoModel.getData().getRecords().size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_app;
        private final TextView tv_appname;
        private final Button app_download;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_app = itemView.findViewById(R.id.iv_app);
            tv_appname = itemView.findViewById(R.id.tv_appname);
            app_download = itemView.findViewById(R.id.app_download);
        }
    }
}
