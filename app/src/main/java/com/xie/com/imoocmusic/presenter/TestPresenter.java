package com.xie.com.imoocmusic.presenter;

import android.content.Context;

import com.http.api.ApiListener;
import com.http.api.ApiUtil;
import com.xie.com.imoocmusic.api.GetQuestionInfoApi;
import com.xie.com.imoocmusic.api.HistoryQuestionGetApi;
import com.xie.com.imoocmusic.domain.QuestionInfo;
import com.xie.com.imoocmusic.views.ITextView;

import java.util.List;

import io.vov.vitamio.utils.Log;

public class TestPresenter {
    private ITextView mITextView;
    private Context context;
    private List<QuestionInfo> mHistoryList;
    private QuestionInfo currentInfo;
    public TestPresenter (ITextView iTextView){
        context = (Context) iTextView;
        mITextView = iTextView;
    }
    public void getData(){
        Log.e("xiejinbo","111111getData111111");
        getHistory();
    }
    private void getHistory(){
        new HistoryQuestionGetApi().get(context, new ApiListener() {
            @Override
            public void success(ApiUtil api) {
                HistoryQuestionGetApi apiBase = (HistoryQuestionGetApi) api;
                mHistoryList = apiBase.list;
                getCurrentQuestion();
            }

            @Override
            public void failure(ApiUtil api) {

            }
        });
    }

    private void getCurrentQuestion(){
        new HistoryQuestionGetApi().get(context, new ApiListener() {
            @Override
            public void success(ApiUtil api) {
                GetQuestionInfoApi apiBase = (GetQuestionInfoApi) api;
                currentInfo = apiBase.mInfo;
                mHistoryList.add(0,currentInfo);
                refreshData();
            }

            @Override
            public void failure(ApiUtil api) {

            }
        });
    }

    private void refreshData(){
        if (mITextView!=null){
            mITextView.updateUI(mHistoryList);
        }

    }
}
