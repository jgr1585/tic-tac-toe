package at.jgr1585.ticTacToe

import at.jgr1585.ticTacToe.cpu.Strategies
import at.jgr1585.ticTacToe.stats.FiledValues
import at.jgr1585.ticTacToe.stats.GameField


fun main() {
    val gameField = GameField()
    val strategies = Strategies.Heuristic

    println("On which side the CPU should play? (X/O)")
    print("> ")

    val cpuPlayer = readlnOrNull()?.let { if (it == "X") 'O' else 'X' } ?: 'X'
    println("You are playing as ${if (cpuPlayer == 'X') "O" else "X"}")

    println("Who shoud start? ((C)PU/(Y)ou)")
    print("> ")

    val startPlayer = readlnOrNull()?.let { if (it == "C") "CPU" else "You" } ?: "You"
    println("Starting player is $startPlayer")

    while (true) {
        gameField.printField()
        println("Enter your move (e.g. A1)")
        print("> ")

        val move = readlnOrNull()
        if (move == null || move.length != 2) {
            println("Invalid move")
            continue
        }

        val row = gameField.rowNames.indexOf(move[0].toString())
        val col = move[1].toString().toIntOrNull()?.minus(1)
        if (row == -1 || col == null) {
            println("Invalid move")
            continue
        }

        if (gameField.field[row][col] != FiledValues.EMPTY) {
            println("Field is already taken")
            continue
        }

        gameField.setField(row, col, FiledValues.fromChar(cpuPlayer).opposite())

        if (gameField.getWinner() != null) {
            gameField.printField()
            println("You won!")
            break
        }

        strategies.getStrategy().makeMove(gameField, FiledValues.fromChar(cpuPlayer)).let { (row, col) ->
            gameField.setField(row, col, FiledValues.fromChar(cpuPlayer))
        }

        if (gameField.getWinner() != null) {
            gameField.printField()
            println("CPU won!")
            break
        }
    }
}