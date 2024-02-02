package org.example.strategies.WinningStrategy;

import org.example.models.Move;

import java.util.HashMap;

public class ColWinningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Character, Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(int size, Move finalMove) {
         int col =finalMove.getCell().getCol();
         char symbol =finalMove.getPlayer().getSymbol();
         if(!colMap.containsKey(col)){
             colMap.put(col, new HashMap<>());
         }
         HashMap<Character, Integer> tempCol = colMap.get(col);
         tempCol.put(symbol, tempCol.getOrDefault(symbol,0)+1);
         return tempCol.get(symbol)==size;
    }
}
