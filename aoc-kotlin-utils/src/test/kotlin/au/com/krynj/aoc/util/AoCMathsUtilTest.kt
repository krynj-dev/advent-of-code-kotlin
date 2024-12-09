package au.com.krynj.aoc.util

import au.com.krynj.aoc.util.AoCMathsUtil.gcd
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AoCMathsUtilTest {

    @Test
    fun testGcd() {
        // Wikipedia example
        assertEquals(21, gcd(252, 105))
        assertEquals(21, gcd(105, 252))
        // Primes
        assertEquals(1, gcd(7, 13))
        assertEquals(1, gcd(13, 7))
        // Negatives
        assertEquals(21, gcd(252, -105))
        assertEquals(21, gcd(105, -252))
        assertEquals(21, gcd(-252, 105))
        assertEquals(21, gcd(-105, 252))
    }

}