package at.jgr1585.ticTacToe.cpu.strategy

import at.jgr1585.ticTacToe.cpu.CPU
import at.jgr1585.ticTacToe.stats.FiledValues
import at.jgr1585.ticTacToe.stats.GameField

class Heuristic : CPU {
    override fun makeMove(board: GameField, cpuSide: FiledValues): Pair<Int, Int> {
        val moves = board.getEmptyFields()
        val scoredMoves = moves.map { move ->
            val newBoard = board.copy()
            newBoard.setField(move.first, move.second, cpuSide)
            val score = evaluateBoard(newBoard, cpuSide)
            Pair(score, move)
        }
        return scoredMoves.maxByOrNull { it.first }!!.second
    }

    private fun evaluateBoard(board: GameField, cpuSides: FiledValues): Int {
        val winner = board.getWinner()
        return when (winner) {
            cpuSides -> 1
            cpuSides.opposite() -> -1
            else -> 0
        }
    }
}