package com.idss.cashloans.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.BankcardsModel;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.ui.dialog.ChangeBankcardsDialog;

import java.util.List;

public class BankCardsAdapter extends RecyclerView.Adapter<BankCardsAdapter.ViewHolder> {
    private final Context mContext;
    private final List<BankcardsModel.DataDTO.RecordsDTO> records;
    private FragmentManager fragmentManager;


    public BankCardsAdapter(Context mContext, List<BankcardsModel.DataDTO.RecordsDTO> records, FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.records = records;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public BankCardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adpter_bankcard, parent, false);
        return new BankCardsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardsAdapter.ViewHolder holder, int position) {
        holder.tv_bankname.setText(records.get(position).bankCardName);
        holder.tv_banknum.setText(records.get(position).bankCardNo);
        LogUtil.e("position:"+position);
        if (records.get(position).getIsDefault()==1) {
            holder.tv_default.setVisibility(View.VISIBLE);
        } else {
            holder.tv_default.setVisibility(View.GONE);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e(String.valueOf(position));
                if (records.get(position).getIsDefault()!=1){
                    ChangeBankcardsDialog changeBankcardsDialog = new ChangeBankcardsDialog(mContext,records.get(position).getId());
                    changeBankcardsDialog.show(fragmentManager,"changeBankcardsDialog");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return null == records ? 0 : records.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_bankname;
        private final TextView tv_default;
        private final TextView tv_banknum;
        private final LinearLayout linearLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_bankname = itemView.findViewById(R.id.tv_bankname);
            tv_default = itemView.findViewById(R.id.tv_default);
            tv_banknum = itemView.findViewById(R.id.tv_banknum);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
