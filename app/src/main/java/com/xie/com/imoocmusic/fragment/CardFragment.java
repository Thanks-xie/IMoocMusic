package com.xie.com.imoocmusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xie.com.imoocmusic.R;
import com.xie.com.imoocmusic.domain.QuestionInfo;

public class CardFragment extends Fragment {
    private View rootView;
    private TextView contentTitle,contentTrue,contentFalse,contentDetail;
    private ImageView imgTrue,imgFalse;
    private RelativeLayout checkMoreLayout,trueLayout,falseLayout;
    private QuestionInfo currentInfo;
    public static CardFragment newInstance(QuestionInfo info) {

        Bundle bundle = new Bundle();

        bundle.putSerializable("info",info);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_pager,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(rootView);
        currentInfo = (QuestionInfo) getArguments().getSerializable("info");
        showData();

    }

    private void showData() {
        contentTitle.setText(currentInfo.title);
    }

    private void initView(View rootView) {
        contentTitle = rootView.findViewById(R.id.contentTitle);
        contentTrue = rootView.findViewById(R.id.content_true);
        contentFalse = rootView.findViewById(R.id.content_false);
        contentDetail = rootView.findViewById(R.id.contentDetail);
        imgTrue = rootView.findViewById(R.id.img_true);
        imgFalse = rootView.findViewById(R.id.img_false);
        trueLayout = rootView.findViewById(R.id.is_true);
        falseLayout = rootView.findViewById(R.id.is_false);
        checkMoreLayout = rootView.findViewById(R.id.check_more_layout);
    }

}
