package com.dexmp.geoquiz;

import static com.dexmp.geoquiz.data.Consts.KEY_INDEX;
import static com.dexmp.geoquiz.data.Consts.MAIN_TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dexmp.geoquiz.data.Question;

public class MainActivity extends AppCompatActivity {

    private Button btnTrue, btnFalse, btnRestart;
    private ImageButton btnNext;
    private TextView txtQuestion, txtResult;
    private LinearLayout questionsFrame;
    private FrameLayout resultFrame;
    private Question[] mQuestionsBank = new Question[] {
            new Question(R.string.question_ekaterinburg, false),
            new Question(R.string.question_moscow, false),
            new Question(R.string.question_noginsk, true),
            new Question(R.string.question_electrostal, true),
    };
    private int mCurrentIndex = 0;
    private float countTrueAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MAIN_TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        // update voids
        initData();

        resultFrame.setVisibility(View.GONE);
        questionsFrame.setVisibility(View.VISIBLE);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // main
        int question = mQuestionsBank[mCurrentIndex].getTextResID();
        txtQuestion.setText(question);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // True btn
                checkAnswer(true);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // False btn
                checkAnswer(false);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex < (mQuestionsBank.length - 1)){
                    mCurrentIndex = (mCurrentIndex + 1);
                    updateQuestion();
                } else {
                    resultUI();
                }
            }
        });

        /*
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = mQuestionsBank.length - 1;
                } else {
                    mCurrentIndex = mCurrentIndex - 1;
                }
                updateQuestion();
            }
        });
        */

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MAIN_TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(MAIN_TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MAIN_TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(MAIN_TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MAIN_TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MAIN_TAG, "onDestroy() called");
    }

    private void initData() {
        Log.d(MAIN_TAG, "initialized data");

        // Buttons
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnRestart = findViewById(R.id.restart_btn);

        // ImageButtons
        btnNext = findViewById(R.id.btn_next);

        // Text
        txtQuestion = findViewById(R.id.txt_question);
        txtResult = findViewById(R.id.result_text);

        // Layouts
        questionsFrame = findViewById(R.id.questions_frame);
        resultFrame = findViewById(R.id.result_frame);
    }

    private void updateQuestion(){
        int question = mQuestionsBank[mCurrentIndex].getTextResID();
        txtQuestion.setText(question);
        btnFalse.setClickable(true);
        btnTrue.setClickable(true);
    }

    private void resultUI() {
        questionsFrame.setVisibility(View.GONE);
        resultFrame.setVisibility(View.VISIBLE);
        txtResult.setText("Верно на " + countTrueAnswer / mQuestionsBank.length * 100 + "%");
    }

    private void checkAnswer(boolean userPressedTrue) {
        btnTrue.setClickable(false);
        btnFalse.setClickable(false);
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            countTrueAnswer++;
        } else  {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}