package com.msewa.madmovegame.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.msewa.madmovegame.constant.Const;
import com.msewa.madmovegame.R;
import com.msewa.madmovegame.home.model.Sport;
import com.msewa.madmovegame.util.HomeUtil;

import java.util.List;

public class SportsListAdapter extends RecyclerView.Adapter<SportsListAdapter.SportsViewHolder> {

    private Context mContext;
    private List<Sport> sportList;
    private SportsListAdapterListener mListener;
    private static int seletedRow = 0;

    public SportsListAdapter() {

    }

    public SportsListAdapter(Context mContext, SportsListAdapterListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.sportList = HomeUtil.getSortsList();
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sport, viewGroup, false);
        return new SportsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder sportsViewHolder, final int i) {
        final Sport sport = sportList.get(i);
        sportsViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seletedRow != i) {
                    seletedRow = i;
                    mListener.onItemClick(sport.getName());
                }
            }
        });

        sportsViewHolder.sportsName.setText(sport.getName());

        if (seletedRow == i) {
            sportsViewHolder.imgBackView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            sportsViewHolder.sportsName.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));

        } else {
            sportsViewHolder.imgBackView.setCardBackgroundColor(mContext.getResources().getColor(R.color.dark_gray));
            sportsViewHolder.sportsName.setTextColor(mContext.getResources().getColor(R.color.dark_gray));
        }

        switch (sport.getName()) {

            case Const.CRICKET:
                sportsViewHolder.sportsImage.setBackground(mContext.getResources().getDrawable(R.drawable.cricket_icon_fill));
                break;

            case Const.HOCKEY:
                sportsViewHolder.sportsImage.setBackground(mContext.getResources().getDrawable(R.drawable.hockey_fill_icon));
                break;

            case Const.BASEBALL:
                sportsViewHolder.sportsImage.setBackground(mContext.getResources().getDrawable(R.drawable.baseball_fill_icon));
                break;

            case Const.FOOTBALL:
                sportsViewHolder.sportsImage.setBackground(mContext.getResources().getDrawable(R.drawable.foodball_icon_fill));
                break;

            case Const.BADMINTON:
                sportsViewHolder.sportsImage.setBackground(mContext.getResources().getDrawable(R.drawable.badmintion_fill_icon));
                break;

            case Const.BASKETBALL:
                sportsViewHolder.sportsImage.setBackground(mContext.getResources().getDrawable(R.drawable.basketball_fill_icon));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return sportList.size();
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView sportsImage;
        TextView sportsName;
        CardView imgBackView;

        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            sportsImage = view.findViewById(R.id.s_image);
            sportsName = view.findViewById(R.id.name);
            imgBackView = view.findViewById(R.id.img_back_view);
        }
    }

    public interface SportsListAdapterListener {

        void onItemClick(String sportName);
    }

}
