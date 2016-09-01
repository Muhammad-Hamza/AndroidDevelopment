package com.example.panacloud.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView ;
    //private TextView mTextclicker;
    private ImageButton mPrevButton;



    private Questions[] mQuestionbank = new Questions[]
            {
            new Questions(R.string.question_africa,false),
                    new Questions(R.string.question_asia,true),
                    new Questions(R.string.question_america,true),
                    new Questions(R.string.question_mideast,false),
                    new Questions(R.string.question_ocean,true),
                    new Questions(R.string.question_pakistan,true)



    };
    private void updateQuestions()
    {
        int question = mQuestionbank[mCurrentIndex].getTextResid();
        mQuestionTextView.setText(question);



    }
    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionbank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if(userPressedTrue=answerIsTrue)
        {
            messageResId=R.string.Correct;
        }
        else
        {
            messageResId=R.string.False;

        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

    }
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mQuestionTextView =(TextView)findViewById(R.id.question_text_view);
        //int question = mQuestionbank[mCurrentIndex].getTextResid();
        //mQuestionTextView.setText(question);
        mTrueButton=(Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
        {
            checkAnswer(false);

        }
        }
        );
        mNextButton=(ImageButton) findViewById(R.id.Next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               mCurrentIndex=(mCurrentIndex+1)% mQuestionbank.length;

                                               updateQuestions();
                                           }
                                       }
        );
        mPrevButton = (ImageButton) findViewById(R.id.Prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionbank.length;

                updateQuestions();
            }
        });
//Text As an onClickListener

     /*   mTextclicker = (TextView)findViewById(R.id.question_text_view);
        mTextclicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionbank.length;
                updateQuestions();
            }
        });*/

        updateQuestions();

    }
}
