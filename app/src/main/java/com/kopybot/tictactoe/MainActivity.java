package com.kopybot.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    TicTacToe model = new TicTacToe();
    TextView currentturn;
    Switch bot1;
    Switch bot2;
    private static final int DELAY = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentturn = (TextView) findViewById(R.id.textViewturn);
        bot1 = (Switch) findViewById(R.id.switchplayer1);
        bot2 = (Switch) findViewById(R.id.switchplayer2);
        Log.d("MainActivity", "Tic Tac Toe");
    }


    public void begin(View view) {
        model.reset();
        model.setzero();
        TextView xwins = (TextView) findViewById(R.id.xwins);
        TextView ties = (TextView) findViewById(R.id.ties);
        TextView owins = (TextView) findViewById(R.id.owins);
        xwins.setText("X wins: " + model.getXwins());
        owins.setText("O wins: " + model.getOwins());
        ties.setText("Ties: " + model.getTies());
        reset(view);
    }

    public void humanMove(View view) {
        ImageView b = (ImageView) view;
        b.setEnabled(false);
        if (model.checkTurn()) {
            currentturn.setText("X's Turn");
        } else {
            currentturn.setText("O's Turn");
        }
        int move = 0;
        switch (b.getId()) {
            case R.id.button1:
                move=1;
                break;
            case R.id.button2:
                move=2;
                break;
            case R.id.button3:
                move=3;
                break;
            case R.id.button4:
                move=4;
                break;
            case R.id.button5:
                move=5;
                break;
            case R.id.button6:
                move=6;
                break;
            case R.id.button7:
                move=7;
                break;
            case R.id.button8:
                move=8;
                break;
            case R.id.button9:
                move=9;
                break;
            default:
        }
        Log.i("PlayerMove", ""+move);
        model.changePos(move);
        if (model.checkTurn()) {
            b.setImageResource(R.drawable.download4);
        } else {
            b.setImageResource(R.drawable.x);
        }
        if (!handleEnd()) {
            model.changeTurn();
            Log.i("BotMove", "Started");
            if (bot1.isChecked() || bot2.isChecked()) {
                disableall();
                new Handler().postDelayed(() -> botMove(), DELAY);
            }
        }
    }

    public void reset(View view) {
        model.reset();
        currentturn.setText("X's Turn");
        ImageView currentbutton = (ImageView) findViewById(R.id.button1);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button2);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button3);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button4);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button5);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button6);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button7);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button8);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);
        currentbutton = (ImageView) findViewById(R.id.button9);
        currentbutton.setEnabled(true);
        currentbutton.setImageDrawable(null);

        if (bot1.isChecked()) {
            disableall();
            new Handler().postDelayed(() -> botMove(), DELAY);
        }
    }

    public void switched(View view) {
//        Switch s = (Switch) view;
//        if (!s.isChecked()) {
        reset(view);
//        }
    }

    public void botMove() {
        //play move
        ImageView currentbutton = null;
        if (model.checkTurn()) {
            currentturn.setText("X's Turn");
        } else {
            currentturn.setText("O's Turn");
        }
        int bot1move = (int) (Math.random() * 9) + 1;
        while (!model.isTaken(bot1move)) {
            bot1move = (int) (Math.random() * 9) + 1;
        }
        Log.i("Botmove", "" + bot1move);
        switch (bot1move) {
            case 1:
                model.changePos(1);
                currentbutton = (ImageView) findViewById(R.id.button1);
                break;
            case 2:
                model.changePos(2);
                currentbutton = (ImageView) findViewById(R.id.button2);
                break;
            case 3:
                model.changePos(3);
                currentbutton = (ImageView) findViewById(R.id.button3);
                break;
            case 4:
                model.changePos(4);
                currentbutton = (ImageView) findViewById(R.id.button4);
                break;
            case 5:
                model.changePos(5);
                currentbutton = (ImageView) findViewById(R.id.button5);
                break;
            case 6:
                model.changePos(6);
                currentbutton = (ImageView) findViewById(R.id.button6);
                break;
            case 7:
                model.changePos(7);
                currentbutton = (ImageView) findViewById(R.id.button7);
                break;
            case 8:
                model.changePos(8);
                currentbutton = (ImageView) findViewById(R.id.button8);
                break;
            case 9:
                model.changePos(9);
                currentbutton = (ImageView) findViewById(R.id.button9);
                break;
            default:
        }
        if (model.checkTurn()) {
            currentbutton.setImageResource(R.drawable.download4);
        } else {
            currentbutton.setImageResource(R.drawable.x);
        }
        currentbutton.setEnabled(false);
        enableall();
        if (!handleEnd()) {
            model.changeTurn();
            Log.i("BotMove", "Started");
            if (bot1.isChecked() && bot2.isChecked()) {
                disableall();
                new Handler().postDelayed(() -> botMove(), DELAY);
            }
        }
    }
    public void disableall() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void disableButton() {
        ImageView currentbutton;
        currentbutton = (ImageView) findViewById(R.id.button1);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button2);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button3);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button4);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button5);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button6);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button7);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button8);
        currentbutton.setEnabled(false);
        currentbutton = (ImageView) findViewById(R.id.button9);
        currentbutton.setEnabled(false);
    }
    public void enableall() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public boolean handleEnd() {
        Log.i("Board",Arrays.deepToString(model.getBoard()));
        Log.i("Turn", model.checkTurn()?"O":"X");
        if (model.isWon() || model.isTie()) {
            TextView xwins = (TextView) findViewById(R.id.xwins);
            TextView ties = (TextView) findViewById(R.id.ties);
            TextView owins = (TextView) findViewById(R.id.owins);
            xwins.setText("X wins: " + model.getXwins());
            owins.setText("O wins: " + model.getOwins());
            ties.setText("Ties: " + model.getTies());

            if (model.isTie()) {
                currentturn.setText("Tie!");
            } else {
                currentturn.setText(model.winner() + " wins!");
            }
            disableButton();
            Log.i("handleEnd", "Game Finsihed");
            return true;
        }
        return false;
    }
}