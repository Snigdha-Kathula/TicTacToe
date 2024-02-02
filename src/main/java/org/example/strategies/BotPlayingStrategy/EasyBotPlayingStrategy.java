package org.example.strategies.BotPlayingStrategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.CellState;
import org.example.models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(int i=0; i< board.getSize(); i++){
            for (int j=0; j< board.getSize(); j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(null, new Cell(i, j));
                }
            }
        }
        return null;
    }
}
