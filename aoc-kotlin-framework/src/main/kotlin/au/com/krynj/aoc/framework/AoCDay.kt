package au.com.krynj.aoc.framework

import java.math.BigInteger

interface AoCDay<T> {
    fun run()
    fun getDay(): Int
    fun partOne(inputLines: T): BigInteger
    fun partTwo(inputLines: T): BigInteger
}