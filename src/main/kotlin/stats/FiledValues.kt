package at.jgr1585.ticTacToe.stats

enum class FiledValues {
    X, O, EMPTY;
    fun toChar() = when (this) {
        X -> 'X'
        O -> 'O'
        EMPTY -> ' '
    }

    fun getValues() = when (this) {
        X -> 1
        O -> -1
        EMPTY -> 0
    }

    fun opposite() = when (this) {
        X -> O
        O -> X
        EMPTY -> EMPTY
    }

    companion object {
        fun fromValues(v: Int) = when {
            v >= 1 -> X
            v <= -1 -> O
            else -> EMPTY
        }

        fun fromChar(c: Char) = when (c) {
            'X' -> X
            'O' -> O
            else -> EMPTY
        }
    }
}