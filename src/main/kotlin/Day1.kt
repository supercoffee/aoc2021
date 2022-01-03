import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import java.io.File

class Day1: Subcommand("day1", "Sonar sweep") {

    private val input by option(ArgType.String, "input", "i").required()

    override fun execute() {
        val lines = File(input).bufferedReader().readLines()
            .map { it.toInt() }

        val noisyCount = lines.windowed(2, 1, true)
            .count {
                it.size > 1 && (it[1] > it[0])
            }

        println("Noisy count $noisyCount")

        val denoisedCount = lines.windowed(3, 1)
            .map{ it.sum() }
            .windowed(2, 1, true)
            .count{
                it.size > 1 && (it[1] > it[0])
            }

        println("Denoised count $denoisedCount")
    }
}