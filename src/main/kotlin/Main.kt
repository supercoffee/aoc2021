import kotlinx.cli.ArgParser

fun main(args: Array<String>) {

    val parser = ArgParser("aoc", strictSubcommandOptionsOrder = true)
    parser.subcommands(Day1())
    parser.parse(args)

}