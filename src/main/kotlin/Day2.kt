import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import java.io.File

class Day2: Subcommand("day2", "Submarine control") {

    private val input by option(ArgType.String, "input", "i").required()

    override fun execute() {

        val lines = File(input).bufferedReader().readLines().filter { it.isNotEmpty() }

        val movements = lines.map{
            it.split(" ")
        }.map{
            val direction = Direction.valueOf(it[0])
            val amount = it[1].toInt()
            Movement(direction, amount)
        }

        val finalPosition = movements.fold(Position(0, 0)) { acc, next ->
            acc + next
        }

        println("Final Position: h=${finalPosition.horizontal}, d=${finalPosition.depth}, m=${finalPosition.horizontal * finalPosition.depth}")
    }
}

enum class Direction {
    forward,
    up,
    down
}

data class Movement (val direction: Direction, val amount: Int)

data class Position(val horizontal: Int, val depth: Int) {

    operator fun plus(m: Movement) = when(m.direction) {
        Direction.forward -> Position(m.amount + horizontal, depth)
        Direction.up -> Position(horizontal, depth - m.amount)
        Direction.down -> Position(horizontal, depth + m.amount)
    }
}