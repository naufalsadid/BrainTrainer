package com.example.braintrainer;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int locationCorrectAnswer,wrongAnswer,point, number,a,b;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    TextView sum,score,result,count,title,subtitle;
    GridLayout gridLayout;
    Button button0,button1,button2,button3,again,go;
    ConstraintLayout main;

    public void generateSoal()
    {
        Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);

        sum.setText(Integer.toString(a)+" + "+Integer.toString(b)+" = ?");
        locationCorrectAnswer = rand.nextInt(4);
        answer.clear();
        for (int i = 0;i<4;i++)
        {
            if(i == locationCorrectAnswer)
            {
                answer.add(a+b);
            } else {
                wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }

    public void countDown()
    {
        new CountDownTimer(31000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                count.setText("Waktu: "+String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {

                gridLayout.setVisibility(View.INVISIBLE);
                again.setVisibility(View.VISIBLE);
                count.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                sum.setVisibility(View.INVISIBLE);
                count.setText("");
                score.setText("");
                sum.setText("");
                result.setText("Jawaban benar: "+ Integer.toString(point) + "/"+Integer.toString(number));

            }
        }.start();
    }

    public void answer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationCorrectAnswer)))
        {
            result.setText("Jawaban benar!");
            point++;
        } else {
            result.setText("Jawaban salah!");
        }

        number++;

        score.setText(Integer.toString(point)+" / "+Integer.toString(number));
        generateSoal();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.btnAnswer0);
        button1 = findViewById(R.id.btnAnswer1);
        button2 = findViewById(R.id.btnAnswer2);
        button3 = findViewById(R.id.btnAnswer3);
        score = findViewById(R.id.score);
        again = findViewById(R.id.btnPlayAgain);
        sum = findViewById(R.id.sum);
        result = findViewById(R.id.result);
        count = findViewById(R.id.count);
        gridLayout = findViewById(R.id.gridLayout);
        go = findViewById(R.id.btnStart);
        main = findViewById(R.id.main);
        title = findViewById(R.id.textTitle);
        subtitle = findViewById(R.id.textSubtitle);
        generateSoal();
        countDown();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(v.VISIBLE);
                go.setVisibility(View.INVISIBLE);
                title.setVisibility(View.INVISIBLE);
                subtitle.setVisibility(View.INVISIBLE);
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                generateSoal();

                gridLayout.setVisibility(View.VISIBLE);
                count.setVisibility(View.VISIBLE);
                score.setVisibility(View.VISIBLE);
                sum.setVisibility(View.VISIBLE);
                again.setVisibility(View.INVISIBLE);
                score.setText("0 / 0");
                result.setText("");
                point = 0;
                number = 0;
                count.setText("30s");

                new CountDownTimer(31000,1000)
                {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        count.setText("Waktu: "+String.valueOf(millisUntilFinished / 1000)+"s");
                    }

                    @Override
                    public void onFinish() {

                        gridLayout.setVisibility(View.INVISIBLE);
                        again.setVisibility(View.VISIBLE);
                        count.setVisibility(View.INVISIBLE);
                        score.setVisibility(View.INVISIBLE);
                        sum.setVisibility(View.INVISIBLE);
                        result.setText("Jawaban benar: "+ Integer.toString(point) + "/"+Integer.toString(number));

                    }
                }.start();
            }
        });
    }
}