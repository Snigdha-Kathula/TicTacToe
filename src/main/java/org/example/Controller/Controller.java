package org.example.Controller;

import org.example.exceptions.*;
import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Move;
import org.example.models.Player;
import org.example.strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class Controller {
    // start Game
    // Display board
    // Make a move
    // checkWinner
    public Game startGame(int size, List<Player> players, List<WinningStrategy> winningStrategies) throws ZeroWinningStrategyException, OnlyOneBotException, SizeOfBoardGreaterThanTwoException, NoOfPlayersException, SameSymbolException {
        return Game.getBuilder().setSize(size).setPlayers(players).setWinningStrategy(winningStrategies).build();
    }
    public void displayBoard(Game game){
       game.displayBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public GameState getGameState(Game game) {
        return game.getGameState();
    }

}
