package backtrack

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import com.xenomachina.argparser.mainBody
import kotlin.system.measureTimeMillis


class NQueenSolverParams(parser: ArgParser) {
    val verbose by parser.flagging(
        "-v", "--verbose",
        help = "print out found solutions"
    ).default(false)
    val n by parser.storing(
        "-n",
        help = "size of the board"
    ) { toInt() }.default(8)
}

class NQueenSolver(private val n: Int = 8, private val verbose: Boolean = false) {
    private val board = Board(n)
    private val mainDiagonalArray = BooleanArray(2 * n - 1)
    private val offDiagonalArray = BooleanArray(2 * n - 1)
    private val result = Result()

    fun solve(): Result {
        result.elapsedMillis = measureTimeMillis {
            backTrack(0, IntArray(n) { it })
        }
        return result
    }

    private fun backTrack(row: Int, columns: IntArray) {
        result.iterations++
        if (row >= n) {
            result.foundSolutions++
            if (verbose) {
                println("Found solution: ${result.foundSolutions}")
                println(board)
            }
            return
        }
        for (col in columns) {
            if (checkMainDiagonal(row, col) || checkOffDiagonal(row, col)) continue
            mainDiagonalArray[mainDiagonal(row, col)] = true
            offDiagonalArray[offDiagonal(row, col)] = true
            board.updateField(row, col, setQueen = true)
            backTrack(row + 1, columns.filter { it != col }.toIntArray())
            mainDiagonalArray[mainDiagonal(row, col)] = false
            offDiagonalArray[offDiagonal(row, col)] = false
            board.updateField(row, col, setQueen = false)
        }
    }

    private fun mainDiagonal(row: Int, col: Int) = row - col + n - 1
    private fun checkMainDiagonal(row: Int, col: Int) = mainDiagonalArray[mainDiagonal(row, col)]
    private fun offDiagonal(row: Int, col: Int) = row + col
    private fun checkOffDiagonal(row: Int, col: Int) = offDiagonalArray[offDiagonal(row, col)]

    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = mainBody {
            val params = ArgParser(args).parseInto(::NQueenSolverParams)
            val result = NQueenSolver(params.n, params.verbose).solve()
            println(result)
        }
    }
}

