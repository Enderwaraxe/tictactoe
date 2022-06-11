package com.kopybot.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int DELAY = 500;
    private TicTacToeGame model = new TicTacToeGame();
    private BotPlayer playerx;
    private BotPlayer playero;
    private BotMiniMax minimaxBot = new BotMiniMax();
    private RandomBot randomBot = new RandomBot();
    TextView xwins;
    TextView ties;
    TextView owins;
    private TextView currentturn;
    private Spinner xspinner;
    private Spinner ospinner;
    private ConstraintLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentturn = (TextView) findViewById(R.id.textViewturn);
        xspinner = (Spinner) findViewById(R.id.spinnerx);
        ospinner = (Spinner) findViewById(R.id.spinnero);
        board = (ConstraintLayout) findViewById(R.id.board);
        xwins = (TextView) findViewById(R.id.xwins);
        ties = (TextView) findViewById(R.id.ties);
        owins = (TextView) findViewById(R.id.owins);
        minimaxBot.boot();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.player_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xspinner.setAdapter(adapter);
        xspinner.setOnItemSelectedListener(this);
        ospinner.setAdapter(adapter);
        ospinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> spinnerview, View view, int position, long id) {
        Spinner b = (Spinner) spinnerview;
        switch (b.getId()) {
            case R.id.spinnerx:
                switch (spinnerview.getSelectedItem().toString()) {
                    case "Random Bot":
                        playerx = randomBot;
                        break;
                    case "MiniMax Bot":
                        playerx = minimaxBot;
                        break;
                    default:
                        playerx = null;
                }
                break;
            case R.id.spinnero:
                switch (spinnerview.getSelectedItem().toString()) {
                    case "Random Bot":
                        playero = randomBot;
                        break;
                    case "MiniMax Bot":
                        playero = minimaxBot;
                        break;
                    default:
                        playero = null;
                }
                break;
            default:
        }
        reset(view);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        return;
    }

    public void reset(View view) {
        model.reset();
        currentturn.setText("X's Turn");
        for (int i = 0; i < 9; i++) {
            ImageView currentbutton = (ImageView) board.getChildAt(i);
            currentbutton.setEnabled(true);
            currentbutton.setImageDrawable(null);
        }
        if (playerx != null) {
            disableTouch();
            new Handler().postDelayed(() -> botMove(), DELAY);
        }
    }

    public void disableTouch() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void enableTouch() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void disableButton() {
        for (int i = 0; i < 9; i++) {
            ImageView currentbutton = (ImageView) board.getChildAt(i);
            currentbutton.setEnabled(false);
        }
    }

    public void startNewPressed(View view) {
        model.reset();
        model.setzero();
        xwins.setText("X wins: " + model.getXwins());
        owins.setText("O wins: " + model.getOwins());
        ties.setText("Ties: " + model.getTies());
        reset(view);
    }

    public void humanMove(View view) {
        int move = board.indexOfChild(view) + 1;
        handleMove(move, (ImageView) view);
    }

    private void botMove() {
        int move = -1;
        if (model.nextPlayer() == 'x') {
            move = playerx.findMove(model);
        } else {
            move = playero.findMove(model);
        }
        ImageView currentbutton = (ImageView) board.getChildAt(move - 1);
        enableTouch();
        handleMove(move, currentbutton);

    }

    private void handleMove(int move, ImageView currentbutton) {
        model.move(move);
        if (model.justPlayed() == 'o') {
            currentbutton.setImageResource(R.drawable.o);
        } else {
            currentbutton.setImageResource(R.drawable.x);
        }
        currentbutton.setEnabled(false);
        currentturn.setText((model.nextPlayer() + "").toUpperCase() + "'s Turn");
        if (model.getStatus() != Status.Incomplete) {
            xwins.setText("X wins: " + model.getXwins());
            owins.setText("O wins: " + model.getOwins());
            ties.setText("Ties: " + model.getTies());
            switch (model.getStatus()) {
                case Owins:
                    currentturn.setText("O Wins!");
                    break;
                case Xwins:
                    currentturn.setText("X Wins!");
                    break;
                case Tie:
                    currentturn.setText("Tie!");
                    break;
                default:
            }
            disableButton();
//            Log.i("handleEnd", "Game Finsihed");
            return;
        }
        boolean isbot = false;
        switch (model.nextPlayer()) {
            case 'x':
                if (playerx!=null) {
                    isbot = true;
                }
                break;
            case 'o':
                if (playero!=null) {
                    isbot = true;
                }
                break;
            default:
        }
//            Log.i("BotMove", "Started");
        if (isbot) {
            disableTouch();
            new Handler().postDelayed(() -> botMove(), DELAY);
        }
    }
}