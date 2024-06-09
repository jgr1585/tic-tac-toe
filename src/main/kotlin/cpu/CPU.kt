package at.jgr1585.ticTacToe.cpu

import at.jgr1585.ticTacToe.stats.FiledValues
import at.jgr1585.ticTacToe.stats.GameField

interface CPU {
    fun makeMove(board: GameField, cpuSide: FiledValues): Pair<Int, Int>
}