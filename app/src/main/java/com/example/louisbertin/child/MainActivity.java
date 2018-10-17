package com.example.louisbertin.child;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.louisbertin.child.helpers.StringHelper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    protected TextView drawMe, scoreTextView, timer, drawValue;
    protected Button go, replay;
    protected String randomLetter, randomLetter1;
    protected int score;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init text to draw
        drawMe = (TextView) findViewById(R.id.drawMe);
        scoreTextView = (TextView) findViewById(R.id.scoreValue);
        timer = (TextView) findViewById(R.id.timer);
        drawValue = (EditText) findViewById(R.id.draw);

        go = (Button) findViewById(R.id.button);
        replay = (Button) findViewById(R.id.replay);

        initDrawMe();
        timer();
    }

    /**
     * initialisation
     */
    protected void initDrawMe() {
        randomLetter = StringHelper.generateRandomCharacter();

        drawMe.setText(randomLetter);
        drawMe.setTextColor(StringHelper.generateRandomColor());
        drawMe.animate().alpha(1).rotation(360).setDuration(2000);
    }

    public void drawValue(View view) {
        String value = drawValue.getText().toString();

        System.out.println(value);
        System.out.println(randomLetter);

        if (value.equals(randomLetter.toLowerCase())) {

            score++;
            String scoreString = Integer.toString(score);
            scoreTextView.setText(scoreString);

            randomLetter = StringHelper.generateRandomCharacter();
            drawMe.setTextColor(StringHelper.generateRandomColor());

            if (score > 4) {
                randomLetter1 = StringHelper.generateRandomCharacter();

                drawMe.setText(randomLetter + randomLetter1);
            } else {
                drawMe.setText(randomLetter);
            }

            drawMe.clearAnimation();
            drawMe.animate().rotation(-360).setDuration(2000);
            Toast.makeText(this, "Excellent!", Toast.LENGTH_SHORT).show();
            this.countDownTimer.start();

        } else {
            Toast.makeText(this, "Essaye encore!", Toast.LENGTH_SHORT).show();

        }

    }

    public void timer() {
         this.countDownTimer = new CountDownTimer(10 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                drawValue.setAlpha(0);
                go.setAlpha(0);
                replay.setAlpha(1);
                Toast.makeText(MainActivity.this, "Dommage! :( ton score est : " + score, Toast.LENGTH_SHORT).show();
            }

        }.start();
    }

    public void replay (View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.paint:
                Intent launchNewIntent = new Intent(MainActivity.this, PaintActivity.class);
                startActivity(launchNewIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
