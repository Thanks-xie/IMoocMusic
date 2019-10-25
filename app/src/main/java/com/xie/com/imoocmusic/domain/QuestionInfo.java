package com.xie.com.imoocmusic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionInfo implements Serializable {

    public String question_id;

    public String title;

    public String answer;

    public List<String> options = new ArrayList<String>();

    public int type;

    public String option;

    public String explain;

}
