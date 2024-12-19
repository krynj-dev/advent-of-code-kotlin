package au.com.krynj.aoc.util

import kotlin.math.abs

object AoCMathsUtil {

    fun gcd(a: Long, b: Long): Long {
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

    fun gcd(a: Int, b: Int): Int {
        return gcd(a.toLong(), b.toLong()).toInt()
    }

    fun lcm(a: Long, b:Long): Long {
        val x = abs(a)
        val y = abs(b)
        val d = gcd(a, b)
        return (y / d) * a
    }

    fun lcm(a: Int, b: Int): Int {
        return lcm(a.toLong(), b.toLong()).toInt()
    }
}