package com.idss.cashloans.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.LoanProgressModel;

public class LoanProgressAdapter extends RecyclerView.Adapter<LoanProgressAdapter.ViewHolder> {
    private final Context mContext;
    private final LoanProgressModel loanProgressModel;

    public LoanProgressAdapter(Context mContext, LoanProgressModel loanProgressModel) {
        this.mContext = mContext;
        this.loanProgressModel = loanProgressModel;
    }

    @NonNull
    @Override
    public LoanProgressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adpter_loanprogress, parent, false);
        return new LoanProgressAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanProgressAdapter.ViewHolder holder, int position) {
        holder.tv_1.setText(loanProgressModel.getData().get(position).getName());
        holder.tv_2.setText(loanProgressModel.getData().get(position).getDesc());
        holder.tv_3.setText(loanProgressModel.getData().get(position).getTime());

        if (loanProgressModel.getData().get(position).getIsNow().equals("1")) {
            holder.tv_1.setTextColor(mContext.getColor(R.color.yellow_c68b03));
            holder.tv_2.setTextColor(mContext.getColor(R.color.yellow_c68b03));
            holder.tv_3.setTextColor(mContext.getColor(R.color.yellow_c68b03));
            holder.tv_1.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.mipmap.selected), null, null, null);

        } else {
            holder.tv_1.setTextColor(mContext.getColor(R.color.black_3e3e3e));
            holder.tv_2.setTextColor(mContext.getColor(R.color.black_3e3e3e));
            holder.tv_3.setTextColor(mContext.getColor(R.color.black_3e3e3e));
            holder.tv_1.setCompoundDrawablesWithIntrinsicBounds(mContext.getDrawable(R.mipmap.notselect), null, null, null);
        }
    }

    @Override
    public int getItemCount() {
        return null == loanProgressModel ? 0 : loanProgressModel.getData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_1;
        private final TextView tv_2;
        private final TextView tv_3;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_2 = itemView.findViewById(R.id.tv_2);
            tv_3 = itemView.findViewById(R.id.tv_3);
        }
    }
}
