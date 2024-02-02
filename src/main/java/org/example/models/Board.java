package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        this.board = new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                row.add(new Cell(i, j));
            }
            board.add(row);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void displayBoard() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                Cell cell =board.get(i).get(j);
                if(cell.getCellState().equals(CellState.EMPTY))
                    System.out.print("| |");
                else
                    System.out.print("|"+cell.getPlayer().getSymbol()+"|");
            }
            System.out.println();
        }
    }
}
