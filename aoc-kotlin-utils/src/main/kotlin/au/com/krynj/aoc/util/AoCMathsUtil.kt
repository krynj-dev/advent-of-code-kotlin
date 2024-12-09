package au.com.krynj.aoc.util

import kotlin.math.abs

object AoCMathsUtil {

    fun gcd(a: Int, b: Int): Int {
        var x = abs(a)
        var y = abs(b)
        while (x != y) {
            if (x > y) {
                x -= y
            } else {
                y -= x
            }
        }
        assert(x == y)
        return x
    }
}