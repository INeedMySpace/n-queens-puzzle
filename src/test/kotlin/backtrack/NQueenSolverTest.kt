package backtrack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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

//    @Test
//    fun `Should solve for 4 queen`() {
//        val s = NQueenSolver(4).solve()
//        assertThat(s.foundSolutions).isEqualTo(2)
//    }
//
//    @Test
//    fun `Should solve for 15 queen`() {
//        val s = NQueenSolver(15).solve()
//        assertThat(s.foundSolutions).isEqualTo(2_279_184)
//    }
//
//    @Test
//    fun `Should solve for 16 queen`() {
//        val s = NQueenSolver(16).solve()
//        assertThat(s.foundSolutions).isEqualTo(14_772_512)
//    }
}