package at.jgr1585.ticTacToe.cpu.strategy

import at.jgr1585.ticTacToe.cpu.CPU
import at.jgr1585.ticTacToe.stats.FiledValues
import at.jgr1585.ticTacToe.stats.GameField

class AlphaBeta: CPU {
    override fun makeMove(board: GameField, cpuSide: FiledValues): Pair<Int, Int> {
        return alphabeta(board, cpuSide, Int.MIN_VALUE, Int.MAX_VALUE, true).second
    }

    @Suppress("DuplicatedCode")
    private fun alphabeta(board: GameField, cpuSide: FiledValues, _alpha: Int, _beta: Int, maximize: Boolean): Pair<Int, Pair<Int, Int>> {
        var alpha = _alpha
        var beta = _beta
        val emptyFields = board.getEmptyFields()
        val winner = board.getWinner()
        if (winner != null || emptyFields.isEmpty()) {
            val score = when (winner) {
                cpuSide -> 1
                cpuSide.opposite() -> -1
                else -> 0
            }

            return Pair(score, Pair(-1, -1))
        }

        var bestValue = if (maximize) Int.MIN_VALUE else Int.MAX_VALUE
        var bestMove = Pair(-1, -1)

        for (field in emptyFields) {
            val newBoard = board.copy()
            newBoard.setField(field.first, field.second, cpuSide)
            val value = alphabeta(newBoard, cpuSide.opposite(), alpha, beta, !maximize).first

            if (maximize) {
                if (value > bestValue) {
                    bestValue = value
                    bestMove = field
                }
                if (bestValue > alpha) {
                    alpha = bestValue
                }
            } else {
                if (value < bestValue) {
                    bestValue = value
                    bestMove = field
                }
                if (bestValue < beta) {
                    beta = bestValue
                }
            }

            if (alpha >= beta) {
                break
            }
        }

        return Pair(bestValue, bestMove)
    }

}