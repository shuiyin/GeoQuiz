package com.example.lance.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private TextView answerText;
    private Button showAnswerButton;
    private boolean answerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        answerText = (TextView)findViewById(R.id.answer_text);
        showAnswerButton = (Button)findViewById(R.id.show_answer_button);

        answerResult = getIntent().getBooleanExtra("answer_is_true",false);


        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerResult){
                    answerText.setText(R.string.true_button);
                }else {
                    answerText.setText(R.string.false_button);
                }
                setShowAnswer(true);
            }
        });
    }
    public void setShowAnswer(boolean showAnswer){
        Intent intent = new Intent();
        intent.putExtra("show_answer",showAnswer);
        setResult(RESULT_OK,intent);
        finish();
    }
}
