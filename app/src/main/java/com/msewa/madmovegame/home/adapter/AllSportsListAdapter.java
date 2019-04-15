package com.msewa.madmovegame.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.msewa.madmovegame.constant.Const;
import com.msewa.madmovegame.R;
import com.msewa.madmovegame.home.model.AllSports;
import com.msewa.madmovegame.home.model.Sport;
import com.msewa.madmovegame.util.AppAnimationUtil;

import java.util.List;

public class AllSportsListAdapter extends RecyclerView.Adapter<AllSportsListAdapter.SportsViewHolder> {

    private Context mContext;
    private List<AllSports> sportList;
    private SportsListAdapterListener mListener;
    private String selectedSport;

    public AllSportsListAdapter() {

    }

    public AllSportsListAdapter(Context mContext, List<AllSports> sportList, String selectedSport, SportsListAdapterListener mListener) {
        this.mContext = mContext;
        this.sportList = sportList;
        this.mListener = mListener;
        this.selectedSport = selectedSport;
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_allsports, viewGroup, false);
        return new SportsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder sportsViewHolder, int i) {
        final AllSports sport = sportList.get(i);


        sportsViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(sport);
            }
        });

        sportsViewHolder.team1Name.setText(sport.getTeam1());
        sportsViewHolder.team2Name.setText(sport.getTeam2());
        //sportsViewHolder.timeleft.setText(sport.getTimeleft() + "");
        sportsViewHolder.leagueName.setText(sport.getLeagueName());


        switch (selectedSport) {
            case Const.CRICKET:
                setTeamImgAndNameForCricket(sportsViewHolder, sport);
                break;
            case Const.BADMINTON:
                break;
            case Const.BASEBALL:
                break;
            case Const.HOCKEY:
                break;
            case Const.BASKETBALL:
                break;
            case Const.FOOTBALL:
                setTeamImgAndNameForFootball(sportsViewHolder, sport);
                break;

        }

        AppAnimationUtil.setAnimation(mContext, R.anim.left_to_right, sportsViewHolder.team2Img);
        AppAnimationUtil.setAnimation(mContext, R.anim.left_to_right, sportsViewHolder.team2Name);
        AppAnimationUtil.setAnimation(mContext, R.anim.right_to_left, sportsViewHolder.tema1Img);
        AppAnimationUtil.setAnimation(mContext, R.anim.right_to_left, sportsViewHolder.team1Name);

    }

    @Override
    public int getItemCount() {
        return sportList.size();
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView tema1Img, team2Img;
        TextView team1Name, team2Name, timeleft, leagueName;

        public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tema1Img = view.findViewById(R.id.team1_image);
            team2Img = view.findViewById(R.id.team2_image);
            team1Name = view.findViewById(R.id.team1_name);
            team2Name = view.findViewById(R.id.team2_name);
            timeleft = view.findViewById(R.id.time_left);
            leagueName = view.findViewById(R.id.league_name);

        }
    }

    public interface SportsListAdapterListener {

        void onItemClick(AllSports allSports);
    }

    public void setSelectedSport(String selectedSport) {
        this.selectedSport = selectedSport;
    }

    public void setSportList(List<AllSports> sportList) {
        this.sportList = sportList;
    }


    private void setTeamImgAndNameForCricket(SportsViewHolder sportsViewHolder, AllSports sport) {

        switch (sport.getTeam1()) {

            case Const.CSK:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.csk_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sportsViewHolder.tema1Img);
                break;


            case Const.MI:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.mi_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sportsViewHolder.tema1Img);
                break;


            case Const.KKR:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.kkr_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sportsViewHolder.tema1Img);
                break;


            case Const.PUNE:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.pune_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sportsViewHolder.tema1Img);

                break;


            case Const.HYD:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.hyd_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(sportsViewHolder.tema1Img);

                break;


            case Const.RCB:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.rcb_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);

                break;


        }

        switch (sport.getTeam2()) {

            case Const
                    .CSK:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.csk_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .MI:
                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.mi_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .KKR:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.kkr_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .PUNE:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.pune_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .HYD:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.hyd_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .RCB:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.rcb_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


        }

    }

    private void setTeamImgAndNameForFootball(SportsViewHolder sportsViewHolder, AllSports sport) {

        switch (sport.getTeam1()) {

            case Const.DEA:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.dea_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);
                break;


            case Const.MFC:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.mfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);
                break;


            case Const.LFC:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.lfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);
                break;


            case Const.BFC:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.bfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);

                break;


            case Const.SFC:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.sfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);

                break;


            case Const.CDF:

                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.cdf_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.tema1Img);

                break;


        }

        switch (sport.getTeam2()) {

            case Const
                    .DEA:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.dea_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .MFC:
                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.mfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .SFC:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.sfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .LFC:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.lfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .CDF:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.cdf_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


            case Const
                    .BFC:

                Glide
                        .with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.bfc_t_football_icon))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        .into(sportsViewHolder.team2Img);
                break;


        }

    }
}
