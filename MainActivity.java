package com.example.panacloud.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView ;



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
        mNextButton=(Button) findViewById(R.id.Next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               mCurrentIndex=(mCurrentIndex+1)% mQuestionbank.length;

                                               updateQuestions();
                                           }
                                       }
        );
        updateQuestions();

    }
}
