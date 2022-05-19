package com.dexmp.geoquiz;

import static com.dexmp.geoquiz.data.Consts.EXTRA_ANSWER_IS_TRUE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        initData();

        mShowAnswerButton.setOnClickListener(v -> {
            if (mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_btn_text);
            } else {
                mAnswerTextView.setText(R.string.false_btn_text);
            }
        });

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    }

    public static Intent newIntent (Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    private void initData() {
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.answer_btn);
    }
}