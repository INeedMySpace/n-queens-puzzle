package backtrack

class Result {
    var elapsedMillis: Long = 0
    var foundSolutions: Int = 0
    override fun toString() =
        "Found $foundSolutions solutions in $elapsedMillis ms"
}