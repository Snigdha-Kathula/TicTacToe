package org.example.models;

import org.example.exceptions.*;
import org.example.strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private int size;
    private List<Player> players;
    private List<WinningStrategy> winningStrategy;
    private List<Move> moves;
    private int nextPlayerIndex;
    private Board board;
    private Player winner;
    private GameState gameState;
    public static Builder getBuilder(){
        return new Builder();
    }

    public Game(Builder builder) {
        this.size = builder.size;
        this.players = builder.players;
        this.winningStrategy = builder.winningStrategy;
        this.moves =new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.board = new Board(size);
        this.gameState = GameState.IN_PROGRESS;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<WinningStrategy> getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(List<WinningStrategy> winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void displayBoard() {
        board.displayBoard();
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("Its "+currentPlayer.getName()+"'s Move.");
        Move move = currentPlayer.makeMove(board);
        if(!valid(move)){
            return;
        }
        int i=move.getCell().getRow();
        int j=move.getCell().getCol();
        Cell finalcell =board.getBoard().get(i).get(j);
        finalcell.setCellState(CellState.FILLED);
        finalcell.setPlayer(currentPlayer);
        Move finalMove = new Move(currentPlayer, finalcell);
        moves.add(finalMove);
        // Check Winner
        checkWinner(finalMove);
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();

    }

    private void checkWinner(Move finalMove) {
        Player player =finalMove.getPlayer();
        for(WinningStrategy ws:winningStrategy){
            if(ws.checkWinner(size, finalMove)){
                setGameState(GameState.WIN);
                winner= player;
                System.out.println("Winner is "+winner.getName());
            }
        }
        if(moves.size() == size*size){
            setGameState(GameState.DRAW);
            System.out.println("Game was Draw || No Win");
        }
    }

    private boolean valid(Move move) {
        int row =move.getCell().getRow();
        int col =move.getCell().getCol();
        return row>=0 || row<size || col>=0 || col<size || move.getCell().getCellState().equals(CellState.EMPTY);
    }

    public static class Builder{
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategy;

        public int getSize() {
            return size;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategy() {
            return winningStrategy;
        }

        public Builder setWinningStrategy(List<WinningStrategy> winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }
        private void validate() throws SizeOfBoardGreaterThanTwoException, ZeroWinningStrategyException, OnlyOneBotException, NoOfPlayersException, SameSymbolException {
            if(size<3) throw new SizeOfBoardGreaterThanTwoException();
            if(winningStrategy.size() < 1) throw new ZeroWinningStrategyException();
            if(players.size() != size-1) throw new NoOfPlayersException();
            int botCnt = 0;
            HashSet<Character> sym =new HashSet<>();
            for(int i=0;i<players.size();i++){
                if(players.get(i).getPlayerType().equals(PlayerType.BOT)) botCnt++;
                sym.add(players.get(i).getSymbol());
            }
            if(botCnt>1) throw new OnlyOneBotException();
            if(sym.size()!=players.size()) throw new SameSymbolException();

        }
        public Game build() throws ZeroWinningStrategyException, OnlyOneBotException, SizeOfBoardGreaterThanTwoException, NoOfPlayersException, SameSymbolException {
            validate();
            return new Game(this);
        }
    }
}
