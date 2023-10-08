package backtrack

import java.text.SimpleDateFormat

class Result {
    var elapsedMillis: Long = 0
    var foundSolutions: Int = 0
    var iterations: Int = 0

    override fun toString() =
        "Found $foundSolutions solutions in $iterations iterations, elapsed ${elapsedTime()}."

    private fun elapsedTime() = SimpleDateFormat("mm:ss.SSS").format(elapsedMillis)
}