package com.example.lance.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private TextView questionText;
    private Button trueButton;
    private Button falseButton;
    private ImageButton lastButton;
    private ImageButton nextButton;
    private Button cheatButton;
    private int i = 0;
    private boolean answerResult;
    List<Question> q = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        questionText = (TextView)findViewById(R.id.question_text);
        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        lastButton = (ImageButton) findViewById(R.id.last_button);
        nextButton = (ImageButton) findViewById(R.id.next_button);
        cheatButton = (Button)findViewById(R.id.cheat_button);
        questionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = (i + 1) % q.size();
                updateQuestion();
            }
        });
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i > 0){
                    i = i - 1;
                    updateQuestion();
                }



            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = (i + 1) % q.size();
                answerResult = false;
                updateQuestion();
            }
        });
        if(savedInstanceState != null){
            i = savedInstanceState.getInt(KEY_INDEX,0);
        }
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue = q.get(i).getChooseAnswer();
                Intent intent = new Intent(QuizActivity.this,CheatActivity.class);
                intent.putExtra("answer_is_true",answerIsTrue);
                startActivityForResult(intent,1);
            }
        });


        initQustion();
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    answerResult = data.getBooleanExtra("show_answer",false);

                }
                break;
            default:
        }

    }

    public void checkAnswer(boolean userAnswer) {
        boolean answerIsTrue = q.get(i).getChooseAnswer();
        int resId = 0;
        if(answerResult){
            resId = R.string.cheat_toast;
        }else if (userAnswer == answerIsTrue) {
            resId = R.string.correct_toast;
        } else {
            resId = R.string.incorrect_toast;
        }
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void updateQuestion() {
        int ques = q.get(i).getShowQuestion();
        questionText.setText(ques);
    }

    public void initQustion() {
        Question q1 = new Question(R.string.question_one, true);
        q.add(q1);
        Question q2 = new Question(R.string.question_two, false);
        q.add(q2);
        Question q3 = new Question(R.string.question_three, true);
        q.add(q3);
        Question q4 = new Question(R.string.question_four, false);
        q.add(q4);
        Question q5 = new Question(R.string.question_fif, true);
        q.add(q5);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX,i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
