package au.com.krynj.aoc.util

import kotlin.math.max
import kotlin.math.min

object AoCAlgorithmUtil {

    /**
     * A method that takes in a grid of char (AKA list of equal-length strings) and looks for all
     * instances of a target character within the given distance.
     *
     * @param y the vertical coordinate of the central char to search around
     * @param x the horizontal coordinate of the central char to search around
     * @param puzzle a list of equal length strings to search in
     * @param target the char to search for
     * @range the maximum manhattan distance search space
     *
     * @return a list of coordinates where the matching char was found in the search space
     */
    fun findAround(y: Int, x: Int, puzzle: List<String>, target: Char, range: Int): List<Pair<Int, Int>> {
        assert(puzzle.all { it.length == puzzle[0].length })
        val locations: MutableList<Pair<Int, Int>> = mutableListOf()
        for (m in max(0, y - range)..min(y + range, puzzle.size - 1)) {
            for (l in max(0, x - range)..min(x + range, puzzle.first().length - 1)) {
                if (puzzle[m][l] == target) locations.add(Pair(m, l))
            }
        }
        return locations
    }

}