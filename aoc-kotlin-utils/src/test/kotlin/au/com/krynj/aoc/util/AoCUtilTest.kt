package au.com.krynj.aoc.util

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AoCUtilTest {

    @Test
    fun readResourceFile() {
        val lines = AoCUtil.readResourceFile("testAoCFile.txt");

        assertEquals(13, lines.size)
    }
}