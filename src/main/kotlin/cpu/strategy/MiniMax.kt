package at.jgr1585.ticTacToe.cpu.strategy

import at.jgr1585.ticTacToe.cpu.CPU
import at.jgr1585.ticTacToe.stats.FiledValues
import at.jgr1585.ticTacToe.stats.GameField

class MiniMax: CPU {
    override fun makeMove(board: GameField, cpuSide: FiledValues): Pair<Int, Int> {
        return minimax(board, cpuSide, true).second
    }

    @Suppress("DuplicatedCode")
    private fun minimax(board: GameField, cpuSide: FiledValues, maximize: Boolean): Pair<Int, Pair<Int, Int>> {
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
            val value = minimax(newBoard, cpuSide.opposite(), !maximize).first

            if (maximize) {
                if (value > bestValue) {
                    bestValue = value
                    bestMove = field
                }
            } else {
                if (value < bestValue) {
                    bestValue = value
                    bestMove = field
                }
            }
        }

        return Pair(bestValue, bestMove)
    }
}



