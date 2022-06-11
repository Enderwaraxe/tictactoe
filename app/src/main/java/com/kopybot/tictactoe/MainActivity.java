package com.kopybot.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TicTacToeModel model = new TicTacToeModel();
    TextView currentturn;
    Switch bot1;
    Switch bot2;
    BotMiniMax bot = new BotMiniMax();
    RandomBot bot_2 = new RandomBot();
    Spinner xspinner;
    Spinner ospinner;
    String playerx;
    String playero;
    private static final int DELAY = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentturn = (TextView) findViewById(R.id.textViewturn);
        xspinner = (Spinner) findViewById(R.id.spinnerx);
        ospinner = (Spinner) findViewById(R.id.spinnero);
//        Log.d("MainActivity", "Tic Tac Toe");
        bot.start();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.player_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xspinner.setAdapter(adapter);
        xspinner.setOnItemSelectedListener(this);
        ospinner.setAdapter(adapter);
        ospinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> spinnerview, View view, int position, long id)
    {
        Spinner b = (Spinner) spinnerview;

        switch (b.getId()) {
            case R.id.spinnerx:
                playerx = spinnerview.getSelectedItem().toString();
                break;
            case R.id.spinnero:
                playero = spinnerview.getSelectedItem().toString();
                break;
            default:
        }
        reset(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {
        return;
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
//        Log.i("PlayerMove", ""+move);
        model.move(move);
        currentturn.setText((model.nextPlayer()+"").toUpperCase() +"'s Turn");
        if (model.justPlayed()=='o') {
            b.setImageResource(R.drawable.download4);
        } else {
            b.setImageResource(R.drawable.x);
        }
        if (!handleEnd()) {
//            Log.i("BotMove", "Started");
            if (!playerx.equals("Human Player") || !playero.equals("Human Player")) {
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

        if (!playerx.equals("Human Player")) {
            disableall();
            new Handler().postDelayed(() -> botMove(), DELAY);
        }
    }

    public void botMove() {
        //play move
        ImageView currentbutton = null;
        int bot1move = 0;
        if (model.nextPlayer() == 'x') {
            switch(playerx) {
                case "Random Bot" :
                    bot1move = bot_2.findMove(model);
                    break;
                case "MiniMax Bot":
                    bot1move = bot.findMove(model);
                    break;
                default:
            }
        }
        else {
            switch(playero) {
                case "Random Bot" :
                    bot1move = bot_2.findMove(model);
                    break;
                case "MiniMax Bot":
                    bot1move = bot.findMove(model);
                    break;
                default:
            }
        }
//        Log.i("Botmove", "" + bot1move);
        switch (bot1move) {
            case 1:
                model.move(1);
                currentbutton = (ImageView) findViewById(R.id.button1);
                break;
            case 2:
                model.move(2);
                currentbutton = (ImageView) findViewById(R.id.button2);
                break;
            case 3:
                model.move(3);
                currentbutton = (ImageView) findViewById(R.id.button3);
                break;
            case 4:
                model.move(4);
                currentbutton = (ImageView) findViewById(R.id.button4);
                break;
            case 5:
                model.move(5);
                currentbutton = (ImageView) findViewById(R.id.button5);
                break;
            case 6:
                model.move(6);
                currentbutton = (ImageView) findViewById(R.id.button6);
                break;
            case 7:
                model.move(7);
                currentbutton = (ImageView) findViewById(R.id.button7);
                break;
            case 8:
                model.move(8);
                currentbutton = (ImageView) findViewById(R.id.button8);
                break;
            case 9:
                model.move(9);
                currentbutton = (ImageView) findViewById(R.id.button9);
                break;
            default:
        }
        Log.i("bot", "" + bot1move);
        if (model.justPlayed() == 'o') {
            currentbutton.setImageResource(R.drawable.download4);
        } else {
            currentbutton.setImageResource(R.drawable.x);
        }
        currentturn.setText((model.nextPlayer()+"").toUpperCase() +"'s Turn");
        currentbutton.setEnabled(false);
        enableall();

        if (!handleEnd()) {
//            Log.i("BotMove", "Started");
            if (!playerx.equals("Human Player") && !playero.equals("Human Player")) {
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
        if (!model.getStatus().equals("")) {
            TextView xwins = (TextView) findViewById(R.id.xwins);
            TextView ties = (TextView) findViewById(R.id.ties);
            TextView owins = (TextView) findViewById(R.id.owins);
            xwins.setText("X wins: " + model.getXwins());
            owins.setText("O wins: " + model.getOwins());
            ties.setText("Ties: " + model.getTies());
            currentturn.setText(model.getStatus());
            disableButton();
//            Log.i("handleEnd", "Game Finsihed");
            return true;
        }
        return false;
    }
}