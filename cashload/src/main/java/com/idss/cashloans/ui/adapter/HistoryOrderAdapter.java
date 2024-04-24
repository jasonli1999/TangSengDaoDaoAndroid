package com.idss.cashloans.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.HistoryOrdersModel;
import com.idss.cashloans.ui.activity.aboutbills.OrderDetailActivity;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.ViewHolder> {
    private final Context mContext;
    private List<HistoryOrdersModel.DataDTO.RecordsDTO> records;

    public HistoryOrderAdapter(Context mContext, List<HistoryOrdersModel.DataDTO.RecordsDTO> records) {
        this.mContext = mContext;
        this.records = records;

    }

    @NonNull
    @Override
    public HistoryOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adpter_historyorder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderAdapter.ViewHolder holder, int position) {
        HistoryOrdersModel.DataDTO.RecordsDTO recordsDTO = records.get(position);
        holder.tv_date.setText(recordsDTO.getCreateTime());
        holder.tv_loans.setText(recordsDTO.getApplicationAmount() + "元");
        /**
         * 审核状态（0 未提交 1已提交未审核 2初审通过待复审 3复审中  4黑名单 5复审通过待终审 6终审中 7被拒 8终审通过放款中 9放款成功待还款 10已结清 11放款失败 12已逾期")
         */
        switch (recordsDTO.getAuditStatus()) {
            case 0:
                holder.tv_statue.setText("未提交");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 1:
                holder.tv_statue.setText("已提交未审核");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 2:
                holder.tv_statue.setText("初审通过待复审");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 3:
                holder.tv_statue.setText("复审中");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 4:
                holder.tv_statue.setText("黑名单");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.red_d30700));
                break;
            case 5:
                holder.tv_statue.setText("复审通过待终审");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 6:
                holder.tv_statue.setText("终审中");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 7:
                holder.tv_statue.setText("被拒");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.red_d30700));
                break;
            case 8:
                holder.tv_statue.setText("终审通过放款中");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.green_2d9500));
                break;
            case 9:
                holder.tv_statue.setText("放款成功待还款");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.yellow_e9910d));
                break;
            case 10:
                holder.tv_statue.setText("已结清");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.green_2d9500));
                break;
            case 11:
                holder.tv_statue.setText("放款失败");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.red_d30700));
                break;
            case 12:
                holder.tv_statue.setText("已逾期");
                holder.tv_statue.setTextColor(mContext.getResources().getColor(R.color.red_d30700));
                break;
        }


        holder.rl_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("id", recordsDTO.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return null == records ? 0 : records.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_date;
        private final TextView tv_loans;
        private final TextView tv_statue;
        private final RelativeLayout rl_all;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_loans = itemView.findViewById(R.id.tv_loans);
            tv_statue = itemView.findViewById(R.id.tv_statue);
            rl_all = itemView.findViewById(R.id.rl_all);
        }
    }
}
