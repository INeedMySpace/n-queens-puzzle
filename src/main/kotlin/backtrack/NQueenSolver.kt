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
    private val columnSet = HashSet<Int>()
    private val mainDiagonalSet = HashSet<Int>()
    private val offDiagonalSet = HashSet<Int>()
    private val result = Result()

    fun solve(): Result {
        result.elapsedMillis = measureTimeMillis {
            backTrack(0)
        }
        return result
    }

    private fun backTrack(row: Int) {
        if (row >= n) {
            result.foundSolutions++
            if (verbose) {
                println("Found solution: ${result.foundSolutions}")
                println(board)
            }
            return
        }
        for (col in 0..<n) {
            if (checkColumn(col) || checkMainDiagonal(row, col) || checkOffDiagonal(row, col)) continue
            columnSet.add(col)
            mainDiagonalSet.add(mainDiagonal(row, col))
            offDiagonalSet.add(offDiagonal(row, col))
            board.updateField(row, col, setQueen = true)
            backTrack(row + 1)
            columnSet.remove(col)
            mainDiagonalSet.remove(mainDiagonal(row, col))
            offDiagonalSet.remove(offDiagonal(row, col))
            board.updateField(row, col, setQueen = false)
        }
    }

    private fun checkColumn(col: Int) = col in columnSet
    private fun mainDiagonal(row: Int, col: Int) = row - col
    private fun checkMainDiagonal(row: Int, col: Int) = mainDiagonal(row, col) in mainDiagonalSet
    private fun offDiagonal(row: Int, col: Int) = row + col
    private fun checkOffDiagonal(row: Int, col: Int) = offDiagonal(row, col) in offDiagonalSet

    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = mainBody {
            val params = ArgParser(args).parseInto(::NQueenSolverParams)
            val result = NQueenSolver(params.n, params.verbose).solve()
            println(result)
        }
    }
}

