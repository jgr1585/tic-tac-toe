package at.jgr1585.ticTacToe.stats

import de.m3y.kformat.Table
import de.m3y.kformat.table
import kotlin.math.abs

class GameField {

    private val _field = MutableList(3) { MutableList(3) { FiledValues.EMPTY } }

    var field: List<List<FiledValues>> = _field
        private set

    val rowNames = listOf("A", "B", "C")

    fun printField() {
        table {
            header("", "1", "2", "3")

            hints {
                borderStyle = Table.BorderStyle.SINGLE_LINE
                defaultAlignment = Table.Hints.Alignment.CENTER
            }

            field.forEachIndexed { i, row ->
                row(rowNames[i], row[0].toChar(), row[1].toChar(), row[2].toChar())
            }
        }.render(StringBuilder()).toString().let(::println)
    }

    fun getEmptyFields(): List<Pair<Int, Int>> {
        val emptyFields = mutableListOf<Pair<Int, Int>>()
        field.forEachIndexed { i, row ->
            row.forEachIndexed { j, value ->
                if (value == FiledValues.EMPTY) {
                    emptyFields.add(i to j)
                }
            }
        }
        return emptyFields
    }

    fun getWinner(): FiledValues? {
        for (row in field) {
            val rowValue = row.sumOf { it.getValues() }

            if (abs(rowValue) == 3) {
                return FiledValues.fromValues(rowValue)
            }
        }

        for (i in 0..2) {
            val colValue = field
                .map { it[i] }
                .sumOf { it.getValues() }

            if (abs(colValue) == 3) {
                return FiledValues.fromValues(colValue)
            }
        }

        return null
    }

    fun setField(row: Int, col: Int, value: FiledValues) {
        _field[row][col] = value
    }

    fun copy(): GameField {
        val gameField =  GameField()
        _field.forEachIndexed { i, row ->
            row.forEachIndexed { j, value ->
                gameField._field[i][j] = value
            }
        }

        return gameField
    }
}

