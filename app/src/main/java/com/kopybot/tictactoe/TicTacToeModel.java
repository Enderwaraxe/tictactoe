package com.kopybot.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeModel {
    private char[] board = new char[9];
    private ArrayList<Integer> trace = new ArrayList<>();
    private String status = "";
    private int xwins = 0;
    private int ties = 0;
    private int owins = 0;

    public TicTacToeModel() {
        reset();
    }

    public boolean isWon() {
        if ((board[0] == board[1] && board[1] == board[2] && board[0] != ' ')
                || (board[3] == board[4] && board[4] == board[5] && board[3] != ' ')
                || (board[6] == board[7] && board[7] == board[8] && board[6] != ' ')
                || (board[0] == board[3] && board[3] == board[6] && board[0] != ' ')
                || (board[1] == board[4] && board[4] == board[7] && board[1] != ' ')
                || (board[2] == board[5] && board[5] == board[8] && board[2] != ' ')
                || (board[0] == board[4] && board[4] == board[8] && board[0] != ' ')
                || (board[2] == board[4] && board[4] == board[6] && board[2] != ' ')) {
            return true;
        }
        return false;
    }

    public char justPlayed() {
        return trace.size()%2 == 1?'x':'o';
    }
    public char nextPlayer() {
        return trace.size()%2 == 0?'x':'o';
    }

    public ArrayList<Integer> getTrace() {
        return trace;
    }

    public String getStatus() {
        return status;
    }

    public void move(int x) {
        trace.add(x);
        board[x-1] = justPlayed();
        if (isWon()) {
            status = (""+justPlayed()).toUpperCase() + "'s Wins!";
            if (justPlayed() =='x') {
                xwins++;
            }
            else {
                owins++;
            }
        }
        else if(trace.size() >=9) {
            status = "Tie!";
            ties++;
        }
    }


    public void reset() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        status = "";
        trace.clear();
    }

    public int getXwins() {
        return xwins;
    }

    public int getOwins() {
        return owins;
    }

    public int getTies() {
        return ties;
    }

    public void setzero() {
        ties = 0;
        xwins = 0;
        owins = 0;
    }
    public boolean isTaken(int x) {
        if (board[x-1] == ' ') {
            return false;
        }
        return true;
    }
    public char[] getBoard() {
        return board;
    }

    public void undoLatest() {
        int x = trace.remove(trace.size()-1);
        board[x-1] = ' ';
    }

    public void setBoard(char[] board) {
        this.board = Arrays.copyOf(board, board.length);
    }

    public void setTrace(ArrayList<Integer> trace) {
        this.trace.addAll(trace);
    }

    public TicTacToeModel deepCopy() {
        TicTacToeModel copy = new TicTacToeModel();
        copy.setBoard(board);
        copy.setTrace(trace);
        return copy;
    }
}
