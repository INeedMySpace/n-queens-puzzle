package backtrack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    @Test
    fun `Should create and print empty board of size 4`() {
        val b = Board(4)
        assertThat(b.toString()).isEqualToIgnoringNewLines("""
            _ _ _ _
            _ _ _ _
            _ _ _ _
            _ _ _ _
        """.trimIndent())
    }

    @Test
    fun `Should throw exceptions if tries to update out of bounds field`() {
        val n = 4
        val b = Board(n)
        assertThrows<IllegalArgumentException> { b.updateField(-1, 0, setQueen = true) }
        assertThrows<IllegalArgumentException> { b.updateField(0, -1, setQueen = true) }
        assertThrows<IllegalArgumentException> { b.updateField(n, 0, setQueen = true) }
        val exception = assertThrows<IllegalArgumentException> { b.updateField(0, n, setQueen = true) }
        assertThat(exception.message).isEqualTo("Row and Column must be in [0..n) range")
    }
}