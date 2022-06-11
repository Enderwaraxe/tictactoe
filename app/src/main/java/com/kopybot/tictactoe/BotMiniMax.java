package com.kopybot.tictactoe;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BotMiniMax extends BotPlayer{
    private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int findMove(TicTacToeGame model) {
        int[] options = new int[9];
        if (model.nextPlayer()== 'x') {
            for (int i = 0; i < 9; i++) {
                options[i] = Integer.MIN_VALUE;
            }
        }
        else {
            for (int i = 0; i < 9; i++) {
                options[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!model.isTaken(i + 1)) {
                TicTacToeGame x = model.deepCopy();
                x.move(i + 1);
                options[i] = winlosscount(x);
                }
            }
        ArrayList<Integer> indexofbest = new ArrayList<>();
        double best = options[0];
        indexofbest.add(0);
        if (model.nextPlayer() == 'x') {
            for (int i = 0; i < 9; i++) {
                if (options[i] > best) {
                    best = options[i];
                    indexofbest.clear();
                    indexofbest.add(i);
                } else if (options[i] == best) {
                    indexofbest.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < 9; i++) {
                if (options[i] < best) {
                    best = options[i];
                    indexofbest.clear();
                    indexofbest.add(i);
                } else if (options[i] == best) {
                    indexofbest.add(i);
                }
            }
        }
        Log.i("hash", "" + Arrays.toString(options) + " " + model.nextPlayer());
        return indexofbest.get((int) (Math.random()*indexofbest.size()))+1;
    }
    public int winlosscount(TicTacToeGame model) {
        String b = Arrays.toString(model.getBoard());
        if (map.containsKey(b.hashCode())) {
            return map.get(b.hashCode());
        }
        if(model.isWon()) {
            if (model.justPlayed() == 'x') {
                map.put(b.hashCode(), 10 - model.getTrace().size());
                return 10 - model.getTrace().size();
            }
            else if (model.justPlayed() == 'o') {
                map.put(b.hashCode(),-10 + model.getTrace().size());
                return -10 + model.getTrace().size();
            }
        }
        else {
            if (model.getStatus()==Status.Tie) {
                return 0;
            }
        }
        int val;
        if (model.nextPlayer() == 'x') {
            val = Integer.MIN_VALUE;
        }
        else {
            val = Integer.MAX_VALUE;
        }
        for (int i = 0; i < 9; i++) {
            if (!model.isTaken(i+1)) {
                TicTacToeGame x = model.deepCopy();
                x.move(i+1);
                if (model.nextPlayer() == 'x') {
                    val = Math.max(winlosscount(x), val);
                }
                else {
                    val = Math.min(winlosscount(x), val);
                }
            }
        }
        String a = Arrays.toString(model.getBoard());
//        Log.i("hash", ""+a);
        map.put(a.hashCode(), val);
        return val;
    }
    
    public void boot() {
        TicTacToeGame empty = new TicTacToeGame();
        findMove(empty);
//        Log.i("hash", ""+map.size());
    }

}
