package com.kopybot.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    TicTacToe model = new TicTacToe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Tic Tac Toe");
        ImageView currentbutton = (ImageView) findViewById(R.id.button1);
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

    public void place(View view) {
        Switch bot1 = (Switch) findViewById(R.id.switchplayer1);
        Switch bot2 = (Switch) findViewById(R.id.switchplayer2);
        ImageView b = (ImageView) view;
        b.setEnabled(false);
        TextView currentturn = (TextView) findViewById(R.id.textViewturn);
        if (model.checkTurn()) {
            currentturn.setText("X's Turn");
        } else {
            currentturn.setText("O's Turn");
        }
        switch (b.getId()) {
            case R.id.button1:
                model.changePos(1);
                break;
            case R.id.button2:
                model.changePos(2);
                break;
            case R.id.button3:
                model.changePos(3);
                break;
            case R.id.button4:
                model.changePos(4);
                break;
            case R.id.button5:
                model.changePos(5);
                break;
            case R.id.button6:
                model.changePos(6);
                break;
            case R.id.button7:
                model.changePos(7);
                break;
            case R.id.button8:
                model.changePos(8);
                break;
            case R.id.button9:
                model.changePos(9);
                break;
            default:
        }
        if (model.checkTurn()) {
            b.setImageResource(R.drawable.download4);
        } else {
            b.setImageResource(R.drawable.x);
        }
        model.changeTurn();
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
            ImageView currentbutton = (ImageView) findViewById(R.id.button1);
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
        if ((bot1.isChecked() || bot2.isChecked()) && (!model.isWon() && !model.isTie())) {
            ImageView currentbutton;
            if (model.checkTurn()) {
                currentturn.setText("X's Turn");
            } else {
                currentturn.setText("O's Turn");
            }
            botMove();
            model.changeTurn();

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
            Log.i("MainActivity", "placed");
        }
    }

    public void reset(View view) {
        Switch bot1 = (Switch) findViewById(R.id.switchplayer1);
        Switch bot2 = (Switch) findViewById(R.id.switchplayer2);
        model.reset();
        TextView currentturn = (TextView) findViewById(R.id.textViewturn);
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
        if (bot1.isChecked() && bot2.isChecked()) {
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
            while (!model.isWon() && !model.isTie()) {
                if (model.checkTurn()) {
                    currentturn.setText("X's Turn");
                } else {
                    currentturn.setText("O's Turn");
                }
                botMove();
                model.changeTurn();
            }
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
        } else if (bot1.isChecked()) {
            botMove();
            model.changeTurn();
            currentturn.setText("O's Turn");
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
        ImageView currentbutton;
        int bot1move = (int) (Math.random() * 9) + 1;
        while (!model.isTaken(bot1move)) {
            bot1move = (int) (Math.random() * 9) + 1;
        }
        switch (bot1move) {
            case 1:
                model.changePos(1);
                currentbutton = (ImageView) findViewById(R.id.button1);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 2:
                model.changePos(2);
                currentbutton = (ImageView) findViewById(R.id.button2);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 3:
                model.changePos(3);
                currentbutton = (ImageView) findViewById(R.id.button3);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 4:
                model.changePos(4);
                currentbutton = (ImageView) findViewById(R.id.button4);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 5:
                model.changePos(5);
                currentbutton = (ImageView) findViewById(R.id.button5);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 6:
                model.changePos(6);
                currentbutton = (ImageView) findViewById(R.id.button6);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 7:
                model.changePos(7);
                currentbutton = (ImageView) findViewById(R.id.button7);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 8:
                model.changePos(8);
                currentbutton = (ImageView) findViewById(R.id.button8);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            case 9:
                model.changePos(9);
                currentbutton = (ImageView) findViewById(R.id.button9);
                if (model.checkTurn()) {
                    currentbutton.setImageResource(R.drawable.download4);
                } else {
                    currentbutton.setImageResource(R.drawable.x);
                }
                currentbutton.setEnabled(false);
                break;
            default:
        }
    }
}