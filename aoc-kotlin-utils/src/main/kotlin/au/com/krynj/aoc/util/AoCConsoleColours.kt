package au.com.krynj.aoc.util

object AoCConsoleColours {
    const val RESET = "\u001B[0m"  // Reset all attributes
    const val BLACK = "\u001B[30m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
    const val BLUE = "\u001B[34m"
    const val PURPLE = "\u001B[35m"
    const val CYAN = "\u001B[36m"
    const val WHITE = "\u001B[37m"

    const val BLACK_BOLD = "\u001B[1;30m"
    const val RED_BOLD = "\u001B[1;31m"
    const val GREEN_BOLD = "\u001B[1;32m"
    const val YELLOW_BOLD = "\u001B[1;33m"
    const val BLUE_BOLD = "\u001B[1;34m"
    const val PURPLE_BOLD = "\u001B[1;35m"
    const val CYAN_BOLD = "\u001B[1;36m"
    const val WHITE_BOLD = "\u001B[1;37m"

    fun addColour(string: String, colourCode: String): String {
        return "%s%s%s".format(colourCode, string, RESET)
    }
}