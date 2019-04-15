package com.msewa.madmovegame.team;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.msewa.madmovegame.R;
import com.msewa.madmovegame.util.AppAnimationUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamPreview.OnTeamPreviewFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamPreview#newInstance} factory method to
 * create an instance of this fragment.
 */

public class TeamPreview extends Fragment {

    private OnTeamPreviewFragmentInteractionListener mListener;
    private Button continue_bt;
    private ImageView closeBt;
    private float height;
    private float width;
    private ImageView p1Img, p2Img, p3Img, p4Img, p5Img, p6Img, p7Img, p8Img, p9Img, p10Img, p11Img, appImg;
    private TextView p1Name, p2Name, p3Name, p4Name, p5Name, p6Name, p7Name, p8Name, p9Name, p10Name, p11Name;


    public TeamPreview() {
        // Required empty public constructor
    }

    public static TeamPreview newInstance() {
        Bundle args = new Bundle();
        TeamPreview fragment = new TeamPreview();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_preview, container, false);
        continue_bt = view.findViewById(R.id.continue_bt);
        appImg = view.findViewById(R.id.app_img);
        p1Img = view.findViewById(R.id.p1_img);
        p2Img = view.findViewById(R.id.p2_img);
        p3Img = view.findViewById(R.id.p3_img);
        p4Img = view.findViewById(R.id.p4_img);
        p5Img = view.findViewById(R.id.p5_img);
        p6Img = view.findViewById(R.id.p6_img);
        p7Img = view.findViewById(R.id.p7_img);
        p8Img = view.findViewById(R.id.p8_img);
        p9Img = view.findViewById(R.id.p9_img);
        p10Img = view.findViewById(R.id.p10_img);
        p11Img = view.findViewById(R.id.p11_img);
        p1Name = view.findViewById(R.id.p1_name);
        p2Name = view.findViewById(R.id.p2_name);
        p3Name = view.findViewById(R.id.p3_name);
        p4Name = view.findViewById(R.id.p4_name);
        p5Name = view.findViewById(R.id.p5_name);
        p6Name = view.findViewById(R.id.p6_name);
        p7Name = view.findViewById(R.id.p7_name);
        p8Name = view.findViewById(R.id.p8_name);
        p9Name = view.findViewById(R.id.p9_name);
        p10Name = view.findViewById(R.id.p10_name);
        p11Name = view.findViewById(R.id.p11_name);
        closeBt = view.findViewById(R.id.close_bt);
        continue_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickContinue();
            }
        });
        closeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, appImg);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, closeBt);

        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p1Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p2Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p3Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p4Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p5Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p6Img);

        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p1Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p2Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p3Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p4Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p5Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.top_to_bottom, p6Name);


        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p7Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p8Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p9Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p10Img);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p11Img);


        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p7Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p8Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p9Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p10Name);
        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, p11Name);


        AppAnimationUtil.setAnimation(getActivity(), R.anim.bottom_to_top, continue_bt);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTeamPreviewFragmentInteractionListener) {
            mListener = (OnTeamPreviewFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void translate(View viewToMove, View target) {
        viewToMove.animate()
                .x(target.getX())
                .y(target.getY())
                .setDuration(3000)
                .start();
    }


    public void moveImage() {

        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;


        ObjectAnimator animTranslateY = ObjectAnimator.ofFloat(p1Img,
                "translationY", p1Img.getY() + getheight(height));
        animTranslateY.setDuration(1000);

        ObjectAnimator animTranslateX = ObjectAnimator.ofFloat(p2Img,
                "translationX", p2Img.getX() + getwidth(width));
        animTranslateX.setDuration(1000);
    }

    private float getheight(float val) {

        return (this.height * val) / 800;
    }


    private float getwidth(float val) {
        return (this.width * val) / 480;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTeamPreviewFragmentInteractionListener {
        void onClickContinue();
    }
}
