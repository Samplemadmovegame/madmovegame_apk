package com.example.madmovegame.contest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madmovegame.R;
import com.example.madmovegame.contest.model.Contest;
import com.example.madmovegame.contest.model.RankInfo;
import com.example.madmovegame.util.MockData;

import java.util.List;

public class RankListAdapter extends RecyclerView.Adapter<RankListAdapter.RankListViewHolder> {

    private Context mContext;
    private List<RankInfo> contestList;
    private RankListAdapterListener mListener;

    public RankListAdapter() {

    }

    public RankListAdapter(Context mContext, RankListAdapterListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.contestList = MockData.getRankInfo();
    }

    @NonNull
    @Override
    public RankListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_rank_info, viewGroup, false);
        return new RankListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RankListViewHolder rankListViewHolder, int i) {
        final RankInfo rankInfo = contestList.get(i);
        rankListViewHolder.rankNo.setText(rankInfo.getRankNo());
        rankListViewHolder.rankName.setText(rankInfo.getName());
        rankListViewHolder.price.setText(mContext.getResources().getString(R.string.Rs)+" "+rankInfo.getPrize());

    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class RankListViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView rankNo,rankName,price;

        public RankListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            rankNo=view.findViewById(R.id.rank_no);
            rankName=view.findViewById(R.id.name);
            price=view.findViewById(R.id.price);

        }
    }

    public interface RankListAdapterListener {
        void onItemClick(Contest contest);
    }

}
