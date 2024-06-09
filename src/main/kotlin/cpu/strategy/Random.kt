package at.jgr1585.ticTacToe.cpu.strategy

import at.jgr1585.ticTacToe.cpu.CPU
import at.jgr1585.ticTacToe.stats.FiledValues
import at.jgr1585.ticTacToe.stats.GameField

class Random: CPU {
    override fun makeMove(board: GameField, cpuSide: FiledValues): Pair<Int, Int> =
        board.getEmptyFields().random()
}
