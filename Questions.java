package com.example.panacloud.geoquiz;

/**
 * Created by Panacloud on 8/29/2016.
 */
public class Questions {
    private int mTextResid;
    private boolean mAnswerTrue;


    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResid() {
        return mTextResid;
    }

    public void setTextResid(int textResid) {
        mTextResid = textResid;
    }

    public Questions(int textRid, boolean answerTrue)
    {
        mTextResid = textRid ;
        mAnswerTrue = answerTrue;


    }
}
