package org.example.models;

import org.example.strategies.BotPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, char symbol, BotPlayingStrategy botPlayingStrategy) {
        super(name, symbol, PlayerType.BOT);
        this.botPlayingStrategy =botPlayingStrategy;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
    public Move makeMove(Board board){
        return botPlayingStrategy.makeMove(board);
    }
}
