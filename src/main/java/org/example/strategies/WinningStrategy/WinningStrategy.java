package org.example.strategies.WinningStrategy;

import org.example.models.Move;

public interface WinningStrategy {

    boolean checkWinner(int size, Move finalMove);
}
