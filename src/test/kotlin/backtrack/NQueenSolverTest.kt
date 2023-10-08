package backtrack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class NQueenSolverTest {
    @ParameterizedTest(name = "There is/are should be {1} solution(s) for {0} queen")
    @CsvSource(
        "1, 1",
        "2, 0",
        "3, 0",
        "4, 2",
        "5, 10",
        "6, 4",
        "7, 40",
        "8, 92",
        "9, 352",
        "10, 724",
        "11, 2_680",
        "12, 14_200",
        "13, 73_712",
        "14, 365_596",
        "15, 2_279_184",
        "16, 14_772_512"
    )
    fun `Validate solutions for input 'n'`(input: Int, expected: Int) {
        val s = NQueenSolver(input).solve()
        println(s)
        assertThat(s.foundSolutions).isEqualTo(expected)
    }

    @Test
    fun `Should print out solution with verbose`() {
        val content = ByteArrayOutputStream()
        System.setOut(PrintStream(content))
        val s = NQueenSolver(4, verbose = true).solve()
        assertThat(content.toString()).isEqualToIgnoringNewLines("""
            Found solution: 1
            _ W _ _
            _ _ _ W
            W _ _ _
            _ _ W _
            Found solution: 2
            _ _ W _
            W _ _ _
            _ _ _ W
            _ W _ _
        """.trimIndent())
        System.setOut(System.out)
    }
}