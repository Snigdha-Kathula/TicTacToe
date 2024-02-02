package org.example.strategies.WinningStrategy;

import org.example.models.Move;

import java.util.HashMap;

public class DiaWinningStrategy implements WinningStrategy{
    HashMap<Character, Integer> leftmap =new HashMap<>();
    HashMap<Character, Integer> rightmap =new HashMap<>();
    @Override
    public boolean checkWinner(int size, Move finalMove) {
         int i=finalMove.getCell().getRow();
         int j=finalMove.getCell().getCol();
         char symbol = finalMove.getPlayer().getSymbol();
         if(i==j){
             leftmap.put(symbol, leftmap.getOrDefault(symbol, 0)+1);
         }
         if(i+j == size-1){
             rightmap.put(symbol, rightmap.getOrDefault(symbol, 0)+1);
         }
         if(i==j &&  leftmap.get(symbol) == size ) return true;
         if(i+j == size-1 && rightmap.get(symbol) == size) return true ;
         return false;
    }
}
