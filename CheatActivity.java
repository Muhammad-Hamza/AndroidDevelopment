package com.example.panacloud.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_VALUE_IS_TRUE = "com.example.panacloud.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.panacloud.geoquiz.answer_shown";
    private boolean mAnswerisTrue;
    private Button mShowAnswer;
    private TextView mAnswerTextView;






    public static Intent newIntent(Context PackageContext,boolean AnswerisTrue)

    {
        Intent i = new Intent(PackageContext,CheatActivity.class);
        i.putExtra(EXTRA_VALUE_IS_TRUE,AnswerisTrue);
        return i;
    }
    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    public static boolean wasAnserShown(Intent result)
    {
return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
//OncreateSTARTS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerisTrue = getIntent().getBooleanExtra(EXTRA_VALUE_IS_TRUE,false);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerisTrue)
                {
                    mAnswerTextView.setText(R.string.Correct);
                }
                else
                {
                    mAnswerTextView.setText(R.string.False);
                }
                setAnswerShownResult(true);

            }
        });


    }
   //OncreateEnds
}
