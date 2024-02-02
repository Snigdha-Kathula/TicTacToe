package org.example;

import org.example.Controller.Controller;
import org.example.exceptions.*;
import org.example.models.*;
import org.example.strategies.BotPlayingStrategy.EasyBotPlayingStrategy;
import org.example.strategies.WinningStrategy.ColWinningStrategy;
import org.example.strategies.WinningStrategy.DiaWinningStrategy;
import org.example.strategies.WinningStrategy.RowWinningStrategy;
import org.example.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;

public class TTTMain {
    public static void main(String[] args) throws ZeroWinningStrategyException, OnlyOneBotException, SizeOfBoardGreaterThanTwoException, NoOfPlayersException, SameSymbolException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Size Of the Board :");
//        int n=sc.nextInt();
        int n=3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Siddu", 'S', PlayerType.HUMAN));
        players.add(new Bot("Robot", 'R', new EasyBotPlayingStrategy()));
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiaWinningStrategy());
        Controller controller = new Controller();
        Game game =controller.startGame(n, players, winningStrategies);
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            controller.displayBoard(game);
            controller.makeMove(game);
        }
        // start Game
        // Display board
        // Make a move
        // checkWinner
    }
}
