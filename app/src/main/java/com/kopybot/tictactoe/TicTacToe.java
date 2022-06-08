package com.kopybot.tictactoe;
import android.widget.TextView;

import java.util.*;
public class TicTacToe {
    private int[][] map = {{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}};
    private char[][] board = new char[3][3];
    private boolean iso;
    private boolean iswon;
    private String winner;
    private int xwins = 0;
    private int ties = 0;
    private int owins = 0;
    public TicTacToe() {
        for (int i = 0; i < 3;i++){
            for (int j = 0; j<3;j++){
                board[i][j] = ' ';
            }
        }
        iswon = false;
        iso = false;
        winner = "";
    }
    public boolean isWon() {
        if (!iso) {
            if (board[1][1] == 'o'){
                if ((board[1][2] == 'o' && board[1][0] == 'o')||(board[0][1] == 'o'&& board[2][1] == 'o')||(board[2][2] == 'o' && board[0][0] == 'o')||(board[0][2] == 'o' && board[2][0] == 'o')) {
                    winner = "O";
                    iswon = true;
                }
            }
            if ((board[0][1] == 'o' && board[0][0] == 'o' && board[0][2] == 'o') || (board[1][0] == 'o' && board[0][0] == 'o' && board[2][0] == 'o')|| (board[2][0] == 'o' && board[2][1] == 'o' && board[2][2] == 'o') || (board[0][2] == 'o' && board[1][2] == 'o' && board[2][2] == 'o')) {
                winner = "O";
                iswon = true;
            }
        }
        else {
            if (board[1][1] == 'x'){
                if ((board[1][2] == 'x' && board[1][0] == 'x')||(board[0][1] == 'x'&& board[2][1] == 'x')||(board[2][2] == 'x' && board[0][0] == 'x')||(board[0][2] == 'x' && board[2][0] == 'x')) {
                    winner = "X";
                    iswon = true;
                }
            }
            if ((board[0][1] == 'x' && board[0][0] == 'x' && board[0][2] == 'x') || (board[1][0] == 'x' && board[0][0] == 'x' && board[2][0] == 'x')|| (board[2][0] == 'x' && board[2][1] == 'x' && board[2][2] == 'x') || (board[0][2] == 'x' && board[1][2] == 'x' && board[2][2] == 'x')) {
                winner = "X";
                iswon = true;
            }
        }
        if (winner.equals("X")) {
            xwins++;
        }
        else if(winner.equals("O")) {
            owins++;
        }
        return iswon;
    }
    public boolean isTie() {
        boolean istie = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (iswon || board[row][col] == ' ') {
                    istie = false;
                    break;
                }
            }
            if (iswon || !istie) {
                break;
            }
        }
        if (istie) {
            ties++;
        }
        return istie;
    }
    public String winner() {
        if (isTie()) {
            iswon = true;
            winner = "tie";
        }
        return winner;
    }
    public void changePos(int x) {
        if (iso){
            board[map[x-1][0]][map[x-1][1]] = 'o';
        }
        else {
            board[map[x-1][0]][map[x-1][1]] = 'x';
        }
    }
    public boolean checkTurn() {
        return iso;
    }
    public void changeTurn() {
        iso = !iso;
    }
    public void reset() {
        for (int i = 0; i < 3;i++){
            for (int j = 0; j<3;j++){
                board[i][j] = ' ';
            }
        }
        iswon = false;
        iso = false;
        winner = "";
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
        if (board[map[x-1][0]][map[x-1][1]]!=' ') {
            return false;
        }
        return true;
    }
}
