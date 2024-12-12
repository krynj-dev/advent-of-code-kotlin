package au.com.krynj.aoc.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AoCAlgorithmUtilTest {

    @Test
    fun testCornerDetectionA() {
        val plotMap = listOf(
            "00000",
            "0AAA0",
            "0AAA0",
            "0AAA0",
            "00000",
        ).map { it.toList() }
        assertEquals(1, AoCAlgorithmUtil.countCorners(Pair(1, 1), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(1, 2), plotMap))
        assertEquals(1, AoCAlgorithmUtil.countCorners(Pair(1, 3), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(2, 1), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(2, 2), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(2, 3), plotMap))
        assertEquals(1, AoCAlgorithmUtil.countCorners(Pair(3, 1), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(3, 2), plotMap))
        assertEquals(1, AoCAlgorithmUtil.countCorners(Pair(3, 3), plotMap))
    }

    @Test
    fun testCornerDetectionB() {
        val plotMap = listOf(
            "0000000",
            "000A000",
            "000A00A",
            "0AAAAA0",
            "000A00A",
            "000A000",
            "0000A00",
        ).map { it.toList() }
        assertEquals(2, AoCAlgorithmUtil.countCorners(Pair(1, 3), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(2, 3), plotMap))
        assertEquals(2, AoCAlgorithmUtil.countCorners(Pair(3, 1), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(3, 2), plotMap))
        assertEquals(4, AoCAlgorithmUtil.countCorners(Pair(3, 3), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(3, 4), plotMap))
        assertEquals(2, AoCAlgorithmUtil.countCorners(Pair(3, 5), plotMap))
        assertEquals(0, AoCAlgorithmUtil.countCorners(Pair(4, 3), plotMap))
        assertEquals(2, AoCAlgorithmUtil.countCorners(Pair(5, 3), plotMap))
    }

}