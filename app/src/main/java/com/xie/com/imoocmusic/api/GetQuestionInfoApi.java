package com.xie.com.imoocmusic.api;

import com.google.gson.Gson;
import com.http.api.ApiUtil;
import com.xie.com.imoocmusic.CardContants;
import com.xie.com.imoocmusic.domain.QuestionInfo;

import org.json.JSONObject;

public class GetQuestionInfoApi extends ApiUtil {
    public QuestionInfo mInfo;
    @Override
    protected String getUrl() {
        return CardContants.URL+"/getQuestion";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {

        try {
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject info = data.optJSONObject("info");

            mInfo = new Gson().fromJson(info.toString(),QuestionInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
