package au.com.krynj.aoc.util

import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object AoCAlgorithmUtil {

    /**
     * A method that takes in a grid of char (AKA list of equal-length strings) and looks for all
     * instances of a target character within the given distance.
     *
     * @param pos the coordinates of the central char to search around
     * @param puzzle a list of equal length strings to search in
     * @param target the char to search for
     * @param range the maximum distance search space
     *
     * @return a list of coordinates where the matching char was found in the search space
     */
    fun <T> findAround(pos: Pair<Int, Int>, puzzle: List<List<T>>, target: T, range: Int = 1, includeDiagonals: Boolean = true): List<Pair<Int, Int>> {
        assert(puzzle.all { it.size == puzzle[0].size })
        val locations: MutableList<Pair<Int, Int>> = mutableListOf()
        for (m in max(0, pos.first - range)..min(pos.first + range, puzzle.size - 1)) {
            for (l in max(0, pos.second - range)..min(pos.second + range, puzzle.first().size - 1)) {
                if (puzzle[m][l] == target && (includeDiagonals || (m == pos.first || l == pos.second))) locations.add(Pair(m, l))
            }
        }
        return locations
    }

    fun <T> findAround(pos: Pair<Int, Int>, puzzle: List<List<T>>, targetHeuristic: (T) -> Boolean, range: Int = 1, includeDiagonals: Boolean = true): List<Pair<Int, Int>> {
        assert(puzzle.all { it.size == puzzle[0].size })
        val locations: MutableList<Pair<Int, Int>> = mutableListOf()
        for (m in max(0, pos.first - range)..min(pos.first + range, puzzle.size - 1)) {
            for (l in max(0, pos.second - range)..min(pos.second + range, puzzle.first().size - 1)) {
                if (targetHeuristic(puzzle[m][l]) && (includeDiagonals || (m == pos.first || l == pos.second))) locations.add(Pair(m, l))
            }
        }
        return locations
    }

    /**
     * A method that takes in a grid of char (AKA list of equal-length strings) and counts
     * how many corners (or edges, I guess) are present surrounding the given pos.
     * A corner here is defined as any directly diagonal cell in the grid that:
     * if matches the char @ pos, has two non-matching chars in the two cells adjacent to it and pos
     * OR
     * if doesn't match, both cells that are adjacent to it and pos are either both matching or both non-matching
     *
     * @param pos the coordinates of the central char to search around
     * @param puzzle a list of equal length strings to search in
     * @param target the char to search for
     * @param range the maximum distance search space
     *
     * @return the number of corners at the position
     */
    fun <T> countCorners(pos: Pair<Int, Int>, puzzle: List<List<T>>): Int {
        assert(puzzle.all { it.size == puzzle[0].size })
        val target = puzzle[pos.first][pos.second]
        val matching: MutableSet<Pair<Int, Int>> = mutableSetOf()
        val nonMatching: MutableSet<Pair<Int, Int>> = mutableSetOf()
        val locations: MutableSet<Pair<Int, Int>> = mutableSetOf()
        for (m in pos.first - 1..pos.first + 1) {
            for (l in pos.second - 1..pos.second + 1) {
                locations.add(Pair(m, l))
                if ((m >= 0 && m < puzzle.size) && (l >= 0 && l < puzzle.first().size) && puzzle[m][l] == target) matching.add(Pair(m, l)) else nonMatching.add(Pair(m, l))
            }
        }
        val cornerNodes = locations.filter {
            it.first != pos.first && it.second != pos.second
        }.filter { nm ->
            (nm !in matching && (matching.count {
                val yDiff = abs(nm.first - it.first)
                val xDiff = abs(nm.second - it.second)
                (yDiff == 1 && xDiff == 0) || (yDiff == 0 && xDiff == 1)
            } == 2)) || nonMatching.count {
                val yDiff = abs(nm.first - it.first)
                val xDiff = abs(nm.second - it.second)
                (yDiff == 1 && xDiff == 0) || (yDiff == 0 && xDiff == 1)
            } == 2
        }
        return cornerNodes.size
    }

    fun reconstructPath(cameFrom: Map<Pair<Int, Int>, Pair<Int, Int>>, current: Pair<Int, Int>): List<Pair<Int, Int>> {
        val path: MutableList<Pair<Int, Int>> = mutableListOf(current)
        var cur = current
        while (cur in cameFrom.keys) {
            cur = cameFrom[cur]!!
            path.add(cur)
        }
        return path
    }

    fun <T> shortestPath(from: Pair<Int, Int>, to: Pair<Int, Int>, puzzle: List<List<T>>,
                         heuristic: (Pair<Int, Int>, Pair<Int, Int>, List<List<T>>) -> Int,
                         dist: (Pair<Int, Int>, Pair<Int, Int>, List<List<T>>) -> Int): List<Pair<Int, Int>> {
        val openSet: PriorityQueue<Pair<Pair<Int, Int>, Int>> = PriorityQueue { a, b ->
            a.second.compareTo(b.second)
        }

        val cameFrom: MutableMap<Pair<Int, Int>, Pair<Int, Int>> = mutableMapOf()
        val gScore: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        gScore[from] = 0

        val fScore: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
        fScore[from] = heuristic(from, to, puzzle)
        openSet.add(from to fScore[from]!!)
        while (openSet.isNotEmpty()) {
            val cur = openSet.poll()
            val curNode = cur.first
            if (curNode == to) {
                return reconstructPath(cameFrom, curNode)
            }
            val neighbours = findAround(curNode, puzzle, { _: T -> true }, includeDiagonals = false)
            for (n in neighbours.filter { it != curNode }) {
                val currentScore = gScore.getOrDefault(curNode) {Int.MAX_VALUE}
                val distance = dist(curNode, n, puzzle)
                if (currentScore != Int.MAX_VALUE && distance != Int.MAX_VALUE) {
                    val tentativeScore = gScore[curNode]!! + distance
                    if (tentativeScore < gScore.getOrDefault(n, Int.MAX_VALUE)) {
                        cameFrom[n] = curNode
                        gScore[n] = tentativeScore
                        fScore[n] = tentativeScore + heuristic(n, to, puzzle)
                        if (n !in openSet.map { it.first }) {
                            openSet.add(n to fScore[n]!!)
                        }
                    }
                }
            }
        }
        return emptyList()
    }

}
