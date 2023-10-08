package backtrack

const val QUEEN_CHAR = 'W'
const val EMPTY_FIELD = '_'

class Board(private val n: Int, private val fields: Array<CharArray> = Array(n) { CharArray(n) { EMPTY_FIELD } }) {


    fun updateField(row: Int, col: Int, setQueen: Boolean) {
        require(row in 0..< n && col in 0..< n) { "Row and Column must be in [0..n) range" }
        fields[row][col] = if (setQueen) QUEEN_CHAR else EMPTY_FIELD
    }

    override fun toString() = fields.joinToString(separator = System.lineSeparator()) { it.joinToString(separator = " ") }
}