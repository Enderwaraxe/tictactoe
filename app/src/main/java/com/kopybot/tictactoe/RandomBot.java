package com.kopybot.tictactoe;

public class RandomBot extends BotPlayer{
    public int findMove(TicTacToeGame model){
        int bot1move = (int) (Math.random() * 9) + 1;
        while (model.isTaken(bot1move)) {
            bot1move = (int) (Math.random() * 9) + 1;
        }
        return bot1move;
    }
}
