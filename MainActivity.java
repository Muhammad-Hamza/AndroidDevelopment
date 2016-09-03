package com.example.panacloud.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private Button mCheatButton;
    //private TextView mTextclicker;
    private ImageButton mPrevButton;
  private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT=0;
    private boolean mIsCheater;



    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG,"onStart Called");

    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause Called");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG,"onStop Called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume Called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy Called");
    }

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
        if(mIsCheater)
        {
            messageResId=R.string.Judgment_Toast;
        }
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
        Log.d(TAG,"onCreate (Bundle) called");

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

                                               mIsCheater=false;
                                               updateQuestions();
                                           }
                                       }
        );
        mPrevButton = (ImageButton) findViewById(R.id.Prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCurrentIndex==0)
                {
                    mCurrentIndex = mQuestionbank.length;
                }

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




        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean AnswerisTrue = mQuestionbank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(MainActivity.this,AnswerisTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
        if (savedInstanceState != null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestions();


    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(resultCode!= Activity.RESULT_OK)
        {
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT)
        {
            if(data==null){
                return;
            }
            mIsCheater = CheatActivity.wasAnserShown(data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle SavedInstanceState){
        super.onSaveInstanceState(SavedInstanceState);
        Log.i(TAG,"SavedInstanceState");
        SavedInstanceState.putInt(KEY_INDEX,mCurrentIndex);


   }

}