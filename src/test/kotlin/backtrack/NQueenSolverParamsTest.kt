package backtrack

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.InvalidArgumentException
import com.xenomachina.argparser.OptionMissingRequiredArgumentException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NQueenSolverParamsTest {
    @Test
    fun `Should parse command line parameters`() {
        val args = arrayOf("-n", "16", "-v")
        val params = ArgParser(args).parseInto(::NQueenSolverParams)
        assertThat(params.n).isEqualTo(16)
        assertThat(params.verbose).isTrue()
    }

    @Test
    fun `Should throw an exception on missing required argument`() {
        val args = arrayOf("-n")
        val exception = assertThrows<OptionMissingRequiredArgumentException> { ArgParser(args).parseInto(::NQueenSolverParams) }
        assertThat(exception.message).isEqualTo("option '-n' is missing a required argument")
    }

    @Test
    fun `Should throw an exception on incorrect board size`() {
        assertThrows<InvalidArgumentException> { ArgParser(arrayOf("-n", "-1")).parseInto(::NQueenSolverParams) }
        assertThrows<InvalidArgumentException> { ArgParser(arrayOf("-n", "0")).parseInto(::NQueenSolverParams) }
        val exception = assertThrows<InvalidArgumentException> { ArgParser(arrayOf("-n", "17")).parseInto(::NQueenSolverParams) }
        assertThat(exception.message).isEqualTo("Board size should be in range [1, 16]")
    }

    @Test
    fun `Should use default value`() {
        val args = arrayOf<String>()
        val params = ArgParser(args).parseInto(::NQueenSolverParams)
        assertThat(params.n).isEqualTo(8)
        assertThat(params.verbose).isFalse()
    }
}