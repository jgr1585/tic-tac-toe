package at.jgr1585.ticTacToe.cpu

import at.jgr1585.ticTacToe.cpu.strategy.AlphaBeta
import at.jgr1585.ticTacToe.cpu.strategy.MiniMax
import at.jgr1585.ticTacToe.cpu.strategy.Random
import at.jgr1585.ticTacToe.cpu.strategy.Heuristic

enum class Strategies {
    RANDOM, MINIMAX, ALPHA_BETA, Heuristic;

    fun getStrategy(): CPU {
        return when (this) {
            RANDOM -> Random()
            MINIMAX -> MiniMax()
            ALPHA_BETA -> AlphaBeta()
            Heuristic -> Heuristic()
        }
    }
}