package org.example.strategies.WinningStrategy;

import org.example.models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Character, Integer>> rowMap =new HashMap<>();
    @Override
    public boolean checkWinner(int size, Move finalMove) {
        int row = finalMove.getCell().getRow();
        char symbol =finalMove.getPlayer().getSymbol();
        if(!rowMap.containsKey(row)){
            rowMap.put(row, new HashMap<>());
        }
        HashMap<Character, Integer> tempRow =rowMap.get(row);
        tempRow.put(symbol, tempRow.getOrDefault(symbol, 0)+1);
        return tempRow.size() == size;
    }
}
