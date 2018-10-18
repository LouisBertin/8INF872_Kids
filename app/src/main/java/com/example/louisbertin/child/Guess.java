package com.example.louisbertin.child;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.louisbertin.child.helpers.StringHelper;

public class Guess {

    protected TextView drawMe, scoreTextView, timer, drawValue;
    protected Button go, replay;
    protected String stringToDisplay;
    protected int score;
    CountDownTimer countDownTimer;
    protected Activity currentActivity;

    public Guess(MainActivity activity) {
        // init text to draw
        drawMe = (TextView) activity.findViewById(R.id.drawMe);
        scoreTextView = (TextView) activity.findViewById(R.id.scoreValue);
        timer = (TextView) activity.findViewById(R.id.timer);
        drawValue = (EditText) activity.findViewById(R.id.draw);

        go = (Button) activity.findViewById(R.id.button);
        replay = (Button) activity.findViewById(R.id.replay);

        currentActivity = activity;

        initDrawMe();
        timer();
    }

    /**
     * initialisation
     */
    private void initDrawMe() {
        stringToDisplay = StringHelper.generateRandomCharacter();

        drawMe.setText(stringToDisplay);
        drawMe.setTextColor(StringHelper.generateRandomColor());
        drawMe.animate().alpha(1).rotation(360).setDuration(2000);
    }

    /**
     * manage timer
     */
    public void timer() {
        this.countDownTimer = new CountDownTimer(10 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                drawValue.setAlpha(0);
                go.setAlpha(0);
                replay.setAlpha(1);
                Toast.makeText(currentActivity, "Dommage! :( ton score est : " + score, Toast.LENGTH_SHORT).show();
            }

        }.start();
    }

    protected void checkIfEquals() {
        String value = drawValue.getText().toString();

        if (value.equals(stringToDisplay.toLowerCase())) {
            score++;
            String scoreString = Integer.toString(score);
            scoreTextView.setText(scoreString);

            if (score > 4) {
                String randomLetter = StringHelper.generateRandomCharacter();
                String randomLetter2 = StringHelper.generateRandomCharacter();
                stringToDisplay = randomLetter + randomLetter2;
                drawMe.setText(stringToDisplay);
                drawMe.setTextColor(StringHelper.generateRandomColor());
            } else {
                stringToDisplay = StringHelper.generateRandomCharacter();
                drawMe.setText(stringToDisplay);
                drawMe.setTextColor(StringHelper.generateRandomColor());
            }

            drawMe.clearAnimation();
            drawMe.animate().rotation(-360).setDuration(2000);
            Toast.makeText(currentActivity, "Excellent!", Toast.LENGTH_SHORT).show();
            drawValue.setText(null);
            this.countDownTimer.start();
        } else {
            drawValue.setText(null);
            Toast.makeText(currentActivity, "Essaye encore!", Toast.LENGTH_SHORT).show();
        }
    }

}
