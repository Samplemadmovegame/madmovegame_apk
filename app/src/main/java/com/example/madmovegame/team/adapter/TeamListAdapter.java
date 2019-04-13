package com.example.madmovegame.team.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madmovegame.constant.Const;
import com.example.madmovegame.R;
import com.example.madmovegame.team.model.Team;
import com.example.madmovegame.util.MockData;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamListViewHolder> {

    private Context mContext;
    private List<Team> paymentList;

    public TeamListAdapter() {

    }

    public TeamListAdapter(Context mContext) {
        this.mContext = mContext;
        this.paymentList = MockData.getTeamInfo();
    }

    @NonNull
    @Override
    public TeamListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_team_info, viewGroup, false);
        return new TeamListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TeamListViewHolder rankListViewHolder, int i) {
        final Team team = paymentList.get(i);
        rankListViewHolder.playerName.setText(team.getPalyerName());
        rankListViewHolder.point.setText(team.getPoint());
        rankListViewHolder.credit.setText(team.getCredits());

        switch (team.getPalyerName()) {

            case Const.DHONI:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.dhoni_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.YADAV:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.yadav_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.YUVRAJ:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.yuvraj_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.POLLARD:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.pollard_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.KARTIK:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.kartik_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.RAINA:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.s_raina_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.ROHIT:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.rohit_sharma_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.QUINTON:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.quinton_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.CHAWLA:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.chawla_img))
                        .into(rankListViewHolder.playerImg);
                break;

            case Const.MALINGA:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.malinga_img))
                        .into(rankListViewHolder.playerImg);
                break;
            default:
                Glide.with(mContext)
                        .load(mContext.getResources().getDrawable(R.drawable.dhoni_img))
                        .into(rankListViewHolder.playerImg);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public class TeamListViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView playerName, point, credit;
        ImageView playerImg, add;

        public TeamListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            playerName = view.findViewById(R.id.player_name);
            point = view.findViewById(R.id.point);
            credit = view.findViewById(R.id.credit);
            playerImg = view.findViewById(R.id.player_img);
            add = view.findViewById(R.id.add_bt);

        }
    }


}
