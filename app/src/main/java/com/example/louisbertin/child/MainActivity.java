package com.example.louisbertin.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    protected Guess guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         guess = new Guess(MainActivity.this);
    }

    public void drawValue(View view) {
        guess.checkIfEquals();
    }

    /**
     * relaunch intent
     * @param view
     */
    public void replay (View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /**
     * manage option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * manage option menu items
     * @param item
     * @return
     */
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
