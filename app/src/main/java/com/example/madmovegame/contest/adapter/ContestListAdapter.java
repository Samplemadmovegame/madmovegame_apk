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
import com.example.madmovegame.util.MockData;

import java.util.List;

public class ContestListAdapter extends RecyclerView.Adapter<ContestListAdapter.ContestViewHolder> {

    private Context mContext;
    private List<Contest> contestList;
    private ContestListAdapterListener mListener;

    public ContestListAdapter() {

    }

    public ContestListAdapter(Context mContext, ContestListAdapterListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.contestList = MockData.getContestList();
    }

    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_contest, viewGroup, false);
        return new ContestViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ContestViewHolder contestViewHolder, int i) {
        final Contest contest = contestList.get(i);
      contestViewHolder.entryPrice.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mListener.onItemClick(contest);
          }
      });

        contestViewHolder.contestName.setText(contest.getContestName());
        contestViewHolder.contestDes.setText(contest.getDescription());
        contestViewHolder.spotsLeft.setText(contest.getLeftspot()+" Spots Lefts");
        contestViewHolder.totalSpots.setText(contest.getTotalSpot()+" Spots");
        contestViewHolder.entryPrice.setText(mContext.getResources().getString(R.string.Rs)+" "+contest.getEntryPrice());
      //  contestViewHolder.winnigPrice.setText(contest.getWinnigPrice());

        Log.v("*1--",contest.getContestName());

    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ContestViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView contestName,contestDes,winnigPrice,entryPrice,spotsLeft,totalSpots;

        public ContestViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            contestName=view.findViewById(R.id.contest_name);
            contestDes=view.findViewById(R.id.contest_des);
            winnigPrice=view.findViewById(R.id.winning_price);
            entryPrice=view.findViewById(R.id.entry_price);
            spotsLeft=view.findViewById(R.id.spots_left);
            totalSpots=view.findViewById(R.id.total_spots);

        }
    }

    public interface ContestListAdapterListener {

        void onItemClick(Contest contest);
    }

}
